package by.epam.webpharmacy.dao.user;

import by.epam.webpharmacy.dao.DaoException;
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

    /**
     * Add a new user to the storage, e.g. database with
     * @param user is user bean that should be stored in database
     * @return true if user was added and false if user with such parameters already exists
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    boolean insertUser(User user) throws DaoException;

    /**
     * Updates user's banned status
     * @param userId id of the user
     * @param banStatus {@code true} to ban, or {@code false} to unban
     * @return {@code true} if item has been successfully updated, {@code false} if update failed
     * @throws DaoException if failed to update data from the storage due to technical problems
     */
    boolean updateUserBannedStatus(long userId, boolean banStatus) throws DaoException;

}
