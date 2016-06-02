package by.epam.webpharmacy.service.user;

import by.epam.webpharmacy.service.user.impl.UserServiceImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible user services on demand.
 */
public class UserServiceFactory {
    private static HashMap<UserServiceName, UserService> userServices = new HashMap<>();
    private static final UserServiceFactory instance = new UserServiceFactory();

    private UserServiceFactory() {
        userServices.put(UserServiceName.USER_SERVICE, new UserServiceImpl());
    }
    public static UserServiceFactory getInstance() {
        return instance;
    }
    public UserService getService(UserServiceName userServiceName) {
        return userServices.get(userServiceName);
    }
}