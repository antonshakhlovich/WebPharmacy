package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.UserDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.util.Parameter;

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
    private final static String REGISTER_USER = "INSERT INTO users (id, login, password_md5, role" +
            ", salt, email, ban_status, first_name, last_name, phone_number, city, address)" +
            "  VALUES (0 ,?, ?, 'user',?, ?, 0, ?, ?, ? , ?, ?);";

    private static UserDao instance = new UserDaoSQLImpl();

    public static UserDao getInstance() {
        return instance;
    }

    private UserDaoSQLImpl() {
    }

    @Override
    public User selectUserByLogin(String login) throws DaoException {
        User user = new User();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            }
            resultSet.next();
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
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from Connection Pool", e);
        } catch (SQLException e) {
            throw new DaoException("Can't make prepared statement", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Can't close preparedStatement", e);
            }
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

    @Override
    public boolean registerUser(User user) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(REGISTER_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHashedPassword());
            preparedStatement.setString(3,user.getSalt());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getFirstName());
            preparedStatement.setString(6,user.getLastName());
            preparedStatement.setString(7,user.getPhoneNumber());
            preparedStatement.setString(8, user.getCity());
            preparedStatement.setString(9, user.getAddress());
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (ConnectionPoolException e) {
            throw new DaoException("Can't get connection from Connection Pool", e);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new DaoException("Can't close preparedStatement", e);
            }
            if (cn != null) {
                try {
                    ConnectionPool.getInstance().releaseConnection(cn);
                } catch (ConnectionPoolException e) {
                    throw new DaoException("Can't release connection to connection pool", e);
                }
            }
        }
    }
}
