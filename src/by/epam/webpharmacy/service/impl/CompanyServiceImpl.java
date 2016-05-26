package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.dao.CompanyDao;
import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.impl.CompanyDaoSQLImpl;
import by.epam.webpharmacy.entity.Company;
import by.epam.webpharmacy.service.CompanyService;
import by.epam.webpharmacy.service.ServiceException;

import java.util.Set;

/**
 * Created by antons on 26.05.2016.
 */

public class CompanyServiceImpl implements CompanyService {

    private static final CompanyDao companyDao = CompanyDaoSQLImpl.getInstance();

    private static CompanyServiceImpl ourInstance = new CompanyServiceImpl();

    public static CompanyServiceImpl getInstance() {
        return ourInstance;
    }

    private CompanyServiceImpl() {
    }

    @Override
    public Set<Company> getCompanySet() throws ServiceException {
        try {
            return companyDao.getCompanySet();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
