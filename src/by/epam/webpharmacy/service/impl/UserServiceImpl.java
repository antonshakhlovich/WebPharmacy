package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.impl.UserDaoSQLImpl;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.util.Hasher;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a class implementation of a {@see UserService} interface.
 */
public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private static AtomicBoolean isNull = new AtomicBoolean(true);
    private static ReentrantLock lock = new ReentrantLock();

    private UserServiceImpl () {

    }

    public static UserService getInstance() {
        if (isNull.get()) {
            lock.lock();
            if (isNull.get()) {
                instance = new UserServiceImpl();
                isNull.set(false);
            }
            lock.unlock();
        }
        return instance;
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user;
        UserDao userDao = UserDaoSQLImpl.getInstance();
        try {
            user = userDao.selectUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Can't get data from DAO layer", e);
        }
        String saltedPassword = user.getSalt() + password;
        try {
            if (Hasher.md5Hash(saltedPassword).equals(user.getHashedPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException("Can't get md5 hash");
        }
    }
}
