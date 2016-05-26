package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.CompanyDao;
import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.dao.util.ConnectionPoolException;
import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.util.Parameter;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is an implementation of the {@see CompanyDao} interface.
 */
public class CompanyDaoSQLImpl implements CompanyDao{

    private static final String SELECT_COMPANIES = "SELECT id, type, name, short_name, country, website FROM companies";
    private static final String INSERT_COMPANY = "INSERT INTO companies(id, type, name, short_name, country, website) " +
            "VALUES(0 ,? ,? ,? ,? ,?)";

    private static CompanyDaoSQLImpl instance = new CompanyDaoSQLImpl();

    public static CompanyDaoSQLImpl getInstance() {
        return instance;
    }

    private CompanyDaoSQLImpl() {
    }


    @Override
    public Set<Company> getCompanySet() throws DaoException {
        Set<Company> companies = new TreeSet<>();
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(SELECT_COMPANIES);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    Company company = new Company();
                    company.setId(resultSet.getLong(Parameter.ID.getName()));
                    company.setType(resultSet.getString(Parameter.TYPE.getName()));
                    company.setFullName(resultSet.getString(Parameter.NAME.getName()));
                    company.setShortName(resultSet.getString(Parameter.SHORT_NAME.getName()));
                    company.setCountry(resultSet.getString(Parameter.COUNTRY.getName()));
                    company.setWebsite(resultSet.getString(Parameter.WEBSITE.getName()));
                    companies.add(company);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            closeResources(cn, preparedStatement, resultSet);
        }
        return companies;
    }

    @Override
    public boolean insertCompany(Company company) throws DaoException {
        Connection cn = null;
        PreparedStatement preparedStatement = null;
        try {
            cn = ConnectionPool.getInstance().getConnection();
            preparedStatement = cn.prepareStatement(INSERT_COMPANY);
            preparedStatement.setString(1,company.getType());
            preparedStatement.setString(2,company.getFullName());
            preparedStatement.setString(3,company.getShortName());
            preparedStatement.setString(4,company.getCountry());
            preparedStatement.setString(5,company.getWebsite());
            return preparedStatement.executeUpdate() > 0;
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
