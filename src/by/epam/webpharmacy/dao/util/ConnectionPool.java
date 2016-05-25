package by.epam.webpharmacy.dao.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static final SQLSource SQL_SOURCE = SQLSource.MYSQL_LOCAL;

    private static ConnectionPool instance = new ConnectionPool();
    private static AtomicBoolean isEmpty = new AtomicBoolean(true);
    private static ReentrantLock lock = new ReentrantLock();
    private static int poolSize = 5;
    private static BlockingQueue<Connection> connections;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {

        if (isEmpty.get()) {
            ConnectionPool.initialize();
        }
        return instance;
    }

    private static void initialize() throws ConnectionPoolException {
        lock.lock();
        if (isEmpty.get()) {
            connections = new ArrayBlockingQueue<Connection>(poolSize);
            try {
                Class.forName(SQL_SOURCE.getClassDriver());
                int currentConnectionSize = connections.size();
                for (int i = 0; i < poolSize - currentConnectionSize; i++) {
                    connections.add(DriverManager.getConnection(SQL_SOURCE.getConnectionURI(),
                            SQL_SOURCE.getUsername(), SQL_SOURCE.getPassword()));
                }
                isEmpty.set(false);
            } catch (ClassNotFoundException | SQLException e) {
                throw new ConnectionPoolException("Initialize error", e);
            }
        }
        lock.unlock();
    }

    public int getPoolSize() {
        return poolSize;
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection con;
        try {
            con = connections.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage());
        }
        return con;
    }

    public boolean releaseConnection(Connection con) throws ConnectionPoolException {
        boolean b = connections.add(con);
        if (!b){
            throw new ConnectionPoolException("Can't release connection");
        }
        return b;
    }

    public boolean closePool() throws ConnectionPoolException {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
        isEmpty.set(true);
        return true;
    }
}



