package by.epam.webpharmacy.dao.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {

    public static ConnectionPool instance;

    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();


    private int poolSize = 5;
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {

        if (!isInitialized.get()) {
            instance.initialize();
        }
        return instance;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public Connection getConnection() throws ConnectionPoolException {
        LOG.debug("size before getting connection" + connections.size());
        Connection con;
        try {
            con = connections.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage());
        }
        if (con != null) {
            LOG.debug("Connection was received.");
        } else {
            LOG.debug("Connection receiving timeout");
        }
        LOG.debug("size after getting connection" + connections.size());
        return con;
    }

    public boolean releaseConnection(Connection con) {
        LOG.debug("size before returning connection" + connections.size());
        boolean b = connections.add(con);
        if (b) {
            LOG.debug("Connection was returned.");
        } else {
            LOG.debug("Connection wasn't returned.");
        }
        LOG.debug("size after getting connection" + connections.size());
        return b;
    }

    private void initialize() throws ConnectionPoolException {
        lock.lock();
        if (!isInitialized.get()) {
            connections = new ArrayBlockingQueue<Connection>(poolSize);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                makeConnections();
                isInitialized.set(true);
            } catch (ClassNotFoundException | SQLException e) {
                throw new ConnectionPoolException("Initialize error", e);
            }
            LOG.debug("Connection pool - ready.");
        }
        lock.unlock();
    }

    private void makeConnections() throws SQLException {
        int currentConnectionSize = connections.size();
        for (int i = 0; i < poolSize - currentConnectionSize; i++) {
            connections.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/webpharmacy","root","123456"));
        }
    }
}



