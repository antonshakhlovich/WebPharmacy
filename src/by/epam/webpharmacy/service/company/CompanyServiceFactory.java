package by.epam.webpharmacy.service.company;

import by.epam.webpharmacy.service.company.impl.CompanyServiceImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible Company services on demand.
 */
public class CompanyServiceFactory {
    private static HashMap<CompanyServiceName, CompanyService> companyServiceMap = new HashMap<>();
    private static final CompanyServiceFactory instance = new CompanyServiceFactory();

    private CompanyServiceFactory() {
        companyServiceMap.put(CompanyServiceName.COMPANY_SERVICE, new CompanyServiceImpl());
    }

    public static CompanyServiceFactory getInstance() {
        return instance;
    }

    public CompanyService getService(CompanyServiceName CompanyServiceName) {
        return companyServiceMap.get(CompanyServiceName);
    }
}