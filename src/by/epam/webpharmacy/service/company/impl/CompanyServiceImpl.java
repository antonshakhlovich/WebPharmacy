package by.epam.webpharmacy.service.company.impl;

import by.epam.webpharmacy.dao.company.CompanyDao;
import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.company.CompanyDaoFactory;
import by.epam.webpharmacy.dao.company.CompanyDaoName;
import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.service.company.CompanyService;
import by.epam.webpharmacy.service.ServiceException;

import java.util.List;

/**
 * An implementation of the {@see CompanyService} interface
 */

public class CompanyServiceImpl implements CompanyService {

    private static final CompanyDao companyDao = CompanyDaoFactory.getInstance().getDao(CompanyDaoName.COMPANY_DAO);

    @Override
    public List<Company> getCompanyList() throws ServiceException {
        try {
            return companyDao.getCompanyList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
