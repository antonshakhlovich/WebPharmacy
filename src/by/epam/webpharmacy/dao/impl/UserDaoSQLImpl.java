package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.entity.User;

/**
 * This is a singleton implementation of the {@see UserDao} interface.
 */
public class UserDaoSQLImpl implements UserDao{

    @Override
    public User selectUserByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public User selectUserById(long id) throws DaoException {
        return null;
    }

    @Override
    public User selectUserByEmail(String email) throws DaoException {
        return null;
    }
}
