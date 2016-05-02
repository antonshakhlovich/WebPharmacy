package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.DaoName;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is an implementation of the {@see UserDao} interface.
 */
public class UserDaoSQLImpl implements UserDao {
    private final static String SELECT_USER_BY_LOGIN = "SELECT id, login, password_md5, role, salt, " +
            "email, ban_status, first_name, last_name, phone_number, city, address" +
            " FROM users WHERE login = ?";

    private DaoName daoName;

    public UserDaoSQLImpl(DaoName daoName) {
        this.daoName = daoName;
    }

    @Override
    public User selectUserByLogin(String login) throws DaoException {
        User user = new User();
        Connection cn = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = cn.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setHashedPassword(resultSet.getString("password_md5"));
            user.setRole(UserRole.valueOf(resultSet.getString("role").toUpperCase()));
            user.setSalt(resultSet.getString("salt"));
            user.setBanned(resultSet.getBoolean("ban_status"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setCity(resultSet.getString("city"));
            user.setAddress(resultSet.getString("address"));
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from Connection Pool", e);
        } catch (SQLException e) {
            throw new DaoException("Can't make prepared statement", e);
        } finally {
            if (cn != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(cn);
                } catch (ConnectionPoolException e) {
                    throw new DaoException("Can't release connection to connection pool", e);
                }
            }
        }
        return user;
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
