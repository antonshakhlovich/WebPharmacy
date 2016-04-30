package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.UserService;
import by.epam.webpharmacy.dao.util.Hasher;

/**
 * Represents a singleton class implementation of a {@see UserService} interface,
 * using UserDaoSQLImpl as an underlying level
 */
public class UserServiceImpl implements UserService {

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user = null;


        return user;
    }
}
