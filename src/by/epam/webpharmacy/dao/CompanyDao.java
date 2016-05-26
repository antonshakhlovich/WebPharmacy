package by.epam.webpharmacy.dao;

import by.epam.webpharmacy.entity.Company;

import java.util.Set;

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
    Set<Company> getCompanySet() throws DaoException;

    /**
     * Insert company to the storage, e.g. database
     * @param company
     * @return true if insert to the storage was successful, and false if storage already contains such value
     * @throws DaoException
     */
    boolean insertCompany(Company company) throws DaoException;



}
