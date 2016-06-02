package by.epam.webpharmacy.dao.user;

import by.epam.webpharmacy.dao.user.impl.UserDaoSQLImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible User Daos on demand.
 */
public class UserDaoFactory {
    private static HashMap<UserDaoName, UserDao> userDaos = new HashMap<>();
    private static final UserDaoFactory instance = new UserDaoFactory();

    private UserDaoFactory() {
        userDaos.put(UserDaoName.USER_DAO, new UserDaoSQLImpl());
    }

    public static UserDaoFactory getInstance() {
        return instance;
    }

    public UserDao getDao(UserDaoName UserDaoName) {
        return userDaos.get(UserDaoName);
    }
}

