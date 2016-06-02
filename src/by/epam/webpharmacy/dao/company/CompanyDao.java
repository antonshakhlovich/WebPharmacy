package by.epam.webpharmacy.dao.company;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.entity.Company;

import java.util.List;

/**
 * Represents an interface for retrieving company-related data. Contains all methods, required for getting such
 * data from the database
 */
public interface CompanyDao {

    /**
     * Retrieves all possible companies from database
     * @return list of companies
     * @throws DaoException
     */
    List<Company> getCompanyList() throws DaoException;

    /**
     * Insert company to the storage, e.g. database
     * @param company
     * @return true if insert to the storage was successful, and false if storage already contains such value
     * @throws DaoException
     */
    boolean insertCompany(Company company) throws DaoException;



}
