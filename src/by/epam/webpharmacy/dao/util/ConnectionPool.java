package by.epam.webpharmacy.dao.util;



import by.epam.webpharmacy.dao.DaoName;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private static final DaoName DAO_NAME = DaoName.MYSQL;

    private static ConnectionPool instance;
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private static ReentrantLock lock = new ReentrantLock();
    private static int poolSize = 5;
    private static BlockingQueue<Connection> connections;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {

        if (isNull.get()) {
            ConnectionPool.initialize();
        }
        return instance;
    }

    private static void initialize() throws ConnectionPoolException {
        lock.lock();
        if (isNull.get()) {
            instance = new ConnectionPool();
            connections = new ArrayBlockingQueue<Connection>(poolSize);
            try {
                Class.forName(DAO_NAME.getClassDriver());
                makeConnections();
                isNull.set(false);
            } catch (ClassNotFoundException | SQLException e) {
                throw new ConnectionPoolException("Initialize error", e);
            }
            LOG.debug("Connection pool - ready.");
        }
        lock.unlock();
    }

    private static void makeConnections() throws SQLException {
        int currentConnectionSize = connections.size();
        for (int i = 0; i < poolSize - currentConnectionSize; i++) {
            connections.add(DriverManager.getConnection(DAO_NAME.getConnectionURI(),
                    DAO_NAME.getUsername(),DAO_NAME.getPassword()));
        }
    }

    public int getPoolSize() {
        return poolSize;
    }

    public Connection getConnection() throws ConnectionPoolException {
        LOG.debug("size before getting connection = " + connections.size());
        Connection con;
        try {
            con = connections.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage());
        }
        if (con != null) {
            LOG.debug("Connection was received.");
        } else {
            LOG.debug("Connection receiving timeout");
        }
        LOG.debug("size after getting connection = " + connections.size());
        return con;
    }

    public boolean releaseConnection(Connection con) {
        LOG.debug("size before returning connection = " + connections.size());
        boolean b = connections.add(con);
        if (b) {
            LOG.debug("Connection was returned.");
        } else {
            LOG.debug("Connection wasn't returned.");
        }
        LOG.debug("size after returning connection = " + connections.size());
        return b;
    }

}



