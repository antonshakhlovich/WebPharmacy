package by.epam.webpharmacy.dao.company;

import by.epam.webpharmacy.dao.company.impl.CompanyDaoSQLImpl;

import java.util.HashMap;


/**
 * This class based on factory design pattern and provides all possible Company Daos on demand.
 */
public class CompanyDaoFactory {
    private static HashMap<CompanyDaoName, CompanyDao> companyDaoMap = new HashMap<>();
    private static final CompanyDaoFactory instance = new CompanyDaoFactory();

    private CompanyDaoFactory() {
        companyDaoMap.put(CompanyDaoName.COMPANY_DAO, new CompanyDaoSQLImpl());
    }

    public static CompanyDaoFactory getInstance() {
        return instance;
    }

    public CompanyDao getDao(CompanyDaoName CompanyDaoName) {
        return companyDaoMap.get(CompanyDaoName);
    }
}