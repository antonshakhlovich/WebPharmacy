package by.epam.webpharmacy.dao;

import by.epam.webpharmacy.entity.User;

/**
 * Represents an interface for retrieving user-related data from data storage, such as database.
 */
public interface UserDao {
    /**
     * Retrieves a user with given login.
     * @param login user's login
     * @return User with corresponding login and other parameters or {@code null} if such user doesn't exist
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    User selectUserByLogin(String login) throws DaoException;
    /**
     * Retrieves a user with given id.
     * @param id user's id
     * @return User with corresponding id and other parameters or {@code null} if such user doesn't exist
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    User selectUserById(long id) throws DaoException;
    /**
     * Retrieves a user with given e-mail.
     * @param email user's login
     * @return User with corresponding e-mail and other parameters or {@code null} if such user doesn't exist
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    User selectUserByEmail(String email) throws DaoException;
}
