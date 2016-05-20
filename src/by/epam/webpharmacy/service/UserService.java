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


    /**
     * Attempts to register a new user with given personal info. Login should be unique in the system
     * @param login user's login
     * @param password user's password
     * @param email user's email
     * @return true if registration succeeded, false if user with such login or email already exists
     * @throws ServiceException if DaoException occurred
     */
    boolean registerUser(String login, String password, String email, String firstName, String lastName,
                         String phoneNumber, String city, String address) throws ServiceException;
}
