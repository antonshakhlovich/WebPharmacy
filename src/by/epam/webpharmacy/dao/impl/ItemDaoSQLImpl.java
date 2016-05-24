package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.DosageForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an implementation of the {@see ItemDao} interface.
 */
public class ItemDaoSQLImpl implements ItemDao {

    private static final String SELECT_DOSAGE_FORMS = "SELECT id, name FROM drugs_dosage_forms";

    private static ItemDao instance = new ItemDaoSQLImpl();

    public static ItemDao getInstance() {
        return instance;
    }

    private ItemDaoSQLImpl() {
    }


    @Override
    public List<DosageForm> getDosageForms() throws DaoException {
        List<DosageForm> dosageForms = new ArrayList<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_DOSAGE_FORMS);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    DosageForm dosageForm = new DosageForm();
                    dosageForm.setId(resultSet.getLong(1));
                    dosageForm.setName(resultSet.getString(2));
                    dosageForms.add(dosageForm);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
        return dosageForms;
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
            throw new DaoException("Can't close prepared statement or result set", e);
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
