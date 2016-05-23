package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.impl.UserDaoSQLImpl;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.util.Hasher;
import by.epam.webpharmacy.service.util.SaltGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents a class implementation of a {@see UserService} interface.
 */
public class UserServiceImpl implements UserService {

    private static UserDao userDao = UserDaoSQLImpl.getInstance();
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return instance;
    }

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.selectUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Can't get data from DAO layer", e);
        }
        if (user != null) {
            String saltedPassword = user.getSalt() + password;
            try {
                if (!Hasher.md5Hash(saltedPassword).equals(user.getHashedPassword())) {
                    return null;
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                throw new ServiceException("Can't get md5 hash");
            }
        }
        return user;
    }


    @Override
    public boolean registerUser(String login, String password, String email, String firstName, String lastName,
                                String phoneNumber, String city, String address) throws ServiceException {

        boolean result;
        try {
            User user = new User();
            user.setSalt(SaltGenerator.getInstance().getSalt());
            String hashedPassword = Hasher.md5Hash(user.getSalt() + password);
            user.setHashedPassword(hashedPassword);
            user.setLogin(login);
            user.setEmail(email);
            user.setRole(UserRole.USER);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setCity(city);
            user.setAddress(address);
            result = userDao.registerUser(user);
        } catch (DaoException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public boolean changeUserBanStatus(long userId, boolean banned) throws ServiceException {
        try {
            return userDao.updateUserBannedStatus(userId, banned);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
