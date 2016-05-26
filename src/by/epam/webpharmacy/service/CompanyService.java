package by.epam.webpharmacy.service;


import by.epam.webpharmacy.entity.Company;

import java.util.Set;

/**
 * Represents an interface of a service for company-related actions
 */

public interface CompanyService {

    /**
     * Retrieves all possible dosage companies from dao layer
     */
    Set<Company> getCompanySet() throws ServiceException;
}
