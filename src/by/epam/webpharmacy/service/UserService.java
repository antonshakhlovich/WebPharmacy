package by.epam.webpharmacy.service;

import by.epam.webpharmacy.entity.User;

/**
 * Represents an interface of a service for user-related actions
 */
public interface UserService {

    /**
     * Attempts to authenticate and authorize a user with a given login and password
     * @param login user's login
     * @param password user's password
     * @return {@see User} object with id and role or null, if credentials are invalid
     * @throws ServiceException if DaoException occurred
     */
    User loginUser(String login, String password) throws ServiceException;
}
