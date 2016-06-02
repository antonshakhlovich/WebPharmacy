package by.epam.webpharmacy.service.user.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.user.UserDao;
import by.epam.webpharmacy.dao.user.UserDaoFactory;
import by.epam.webpharmacy.dao.user.UserDaoName;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.user.UserService;
import by.epam.webpharmacy.service.util.Hasher;
import by.epam.webpharmacy.service.util.SaltGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Represents a class implementation of a {@see UserService} interface.
 */
public class UserServiceImpl implements UserService {

    private static UserDao userDao = UserDaoFactory.getInstance().getDao(UserDaoName.USER_DAO);

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
                throw new ServiceException("Can't get md5 hash",e);
            }
        }
        return user;
    }


    @Override
    public boolean registerUser(User user) throws ServiceException {

        boolean result;
        boolean isNotExist = false;
        try {
            if (userDao.selectUserByEmail(user.getEmail()) == null) {
                if (userDao.selectUserByLogin(user.getLogin()) == null) {
                    isNotExist = true;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (isNotExist) {
            try {
                user.setSalt(SaltGenerator.getInstance().getSalt());
                String hashedPassword = Hasher.md5Hash(user.getSalt() + user.getPassword());
                user.setHashedPassword(hashedPassword);
                user.setRole(UserRole.USER);
                result = userDao.insertUser(user);
            } catch (DaoException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
                throw new ServiceException(e);
            }
            return result;
        } else {
            return false;
        }

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
