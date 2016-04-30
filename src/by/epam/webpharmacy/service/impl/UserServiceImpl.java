package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.DaoName;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.UserDaoFactory;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.service.util.Hasher;

/**
 * Represents a class implementation of a {@see UserService} interface.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user;
        UserDao userDao = UserDaoFactory.getInstance().getDao(DaoName.MYSQL);
        try {
            user = userDao.selectUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Can't get data from DAO layer", e);
        }
        String saltedPassword = user.getSalt() + password;
        if (Hasher.md5Hash(saltedPassword).equals(user.getHashedPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
