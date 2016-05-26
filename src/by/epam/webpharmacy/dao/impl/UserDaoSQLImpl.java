package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.util.Parameter;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This is an implementation of the {@see UserDao} interface.
 */
public class UserDaoSQLImpl implements UserDao {

    private final static String SELECT_USER_BY_LOGIN = "SELECT id, login, password_md5, role, salt, " +
            "email, ban_status, first_name, last_name, phone_number, city, address" +
            " FROM users WHERE login = ?";
    private final static String SELECT_USER_BY_EMAIL = "SELECT id, login, password_md5, role, salt, " +
            "email, ban_status, first_name, last_name, phone_number, city, address" +
            " FROM users WHERE email = ?";
    private final static String SELECT_USER_BY_ID = "SELECT id, login, password_md5, role, salt, " +
            "email, ban_status, first_name, last_name, phone_number, city, address" +
            " FROM users WHERE id = ?";
    private final static String INSERT_USER = "INSERT INTO users (id, login, password_md5, role" +
            ", salt, email, ban_status, first_name, last_name, phone_number, city, address)" +
            "  VALUES (0 ,?, ?, 'user',?, ?, 0, ?, ?, ? , ?, ?);";
    private final static String UPDATE_USER_STATUS = "UPDATE users SET ban_status=? WHERE id=?";

    private static UserDao instance = new UserDaoSQLImpl();

    public static UserDao getInstance() {
        return instance;
    }

    private UserDaoSQLImpl() {
    }

    @Override
    public User selectUserByLogin(String login) throws DaoException {
        return selectUserBy(SELECT_USER_BY_LOGIN, login);
    }

    @Override
    public User selectUserById(long id) throws DaoException {
        return selectUserBy(SELECT_USER_BY_ID, String.valueOf(id));
    }

    @Override
    public User selectUserByEmail(String email) throws DaoException {
        return selectUserBy(SELECT_USER_BY_EMAIL, email);
    }

    @Override
    public boolean insertUser(User user) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getFirstName());
            preparedStatement.setString(6, user.getLastName());
            preparedStatement.setString(7, user.getPhoneNumber());
            preparedStatement.setString(8, user.getCity());
            preparedStatement.setString(9, user.getAddress());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from Connection Pool", e);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            closeResources(cn,preparedStatement);
        }
    }

    @Override
    public boolean updateUserBannedStatus(long userId, boolean banStatus) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(UPDATE_USER_STATUS);
            preparedStatement.setBoolean(1, banStatus);
            preparedStatement.setLong(2, userId);
            if (preparedStatement.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Request to database failed", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from connection pool",e);
        } finally {
            closeResources(cn, preparedStatement);
        }
        return true;
    }

    private User selectUserBy(String queryString, String parameter) throws DaoException {
        User user = new User();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(queryString);
            preparedStatement.setString(1, parameter);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();
            setUserParameters(user, resultSet);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from Connection Pool", e);
        } catch (SQLException e) {
            throw new DaoException("Can't make prepared statement", e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
        return user;
    }

    private void setUserParameters(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getLong(Parameter.ID.getName()));
        user.setLogin(resultSet.getString(Parameter.LOGIN.getName()));
        user.setHashedPassword(resultSet.getString(Parameter.PASSWORD_MD5.getName()));
        user.setRole(UserRole.valueOf(resultSet.getString(Parameter.ROLE.getName()).toUpperCase()));
        user.setSalt(resultSet.getString(Parameter.SALT.getName()));
        user.setBanned(resultSet.getBoolean(Parameter.SALT.getName()));
        user.setEmail(resultSet.getString(Parameter.EMAIL.getName()));
        user.setFirstName(resultSet.getString(Parameter.FIRST_NAME.getName()));
        user.setLastName(resultSet.getString(Parameter.LAST_NAME.getName()));
        user.setPhoneNumber(resultSet.getString(Parameter.PHONE_NUMBER.getName()));
        user.setCity(resultSet.getString(Parameter.CITY.getName()));
        user.setAddress(resultSet.getString(Parameter.ADDRESS.getName()));
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement) throws DaoException {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't close prepared statement", e);
        }
        if (connection != null) {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DaoException("Can't release connection to connection pool", e);
            }
        }
    }

    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws DaoException {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't close prepared statement", e);
        }
        if (connection != null) {
            try {
                ConnectionPool.getInstance().releaseConnection(connection);
            } catch (ConnectionPoolException e) {
                throw new DaoException("Can't release connection to connection pool", e);
            }
        }
    }
}
