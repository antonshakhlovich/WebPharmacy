package by.epam.webpharmacy.service.company;


import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.service.ServiceException;

import java.util.List;

/**
 * Represents an interface of a service for company-related actions
 */

public interface CompanyService {

    /**
     * Retrieves all possible dosage companies from dao layer
     */
    List<Company> getCompanyList() throws ServiceException;
}
