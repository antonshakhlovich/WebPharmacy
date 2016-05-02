package by.epam.webpharmacy.dao;

import by.epam.webpharmacy.dao.impl.UserDaoSQLImpl;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class UserDaoFactory {
    private static UserDaoFactory instance;
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private static ReentrantLock lock = new ReentrantLock();
    private static HashMap<DaoName, UserDao> userDaoHashMap = new HashMap<>();


    private UserDaoFactory() {
        userDaoHashMap.put(DaoName.MYSQL, new UserDaoSQLImpl(DaoName.MYSQL));
        userDaoHashMap.put(DaoName.MYSQL_LOCAL, new UserDaoSQLImpl(DaoName.MYSQL_LOCAL));
    }

    public static UserDaoFactory getInstance() {
        if (isNull.get()) {
            lock.lock();
            if (isNull.get()) {
                instance = new UserDaoFactory();
                isNull.set(false);
            }
            lock.unlock();
        }
        return instance;
    }

    public UserDao getDao(DaoName daoName) {
        return userDaoHashMap.get(daoName);
    }
}
