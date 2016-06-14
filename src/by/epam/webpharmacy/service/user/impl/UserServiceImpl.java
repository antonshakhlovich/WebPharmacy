package by.epam.webpharmacy.service.user.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.user.UserDao;
import by.epam.webpharmacy.dao.user.UserDaoFactory;
import by.epam.webpharmacy.dao.user.UserDaoName;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.entity.UserRole;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.user.UserService;
import by.epam.webpharmacy.service.util.Hasher;
import by.epam.webpharmacy.service.util.SaltGenerator;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Represents a class implementation of a {@see UserService} interface.
 */
public class UserServiceImpl implements UserService {

    private static UserDao userDao = UserDaoFactory.getInstance().getDao(UserDaoName.USER_DAO);

    @Override
    public User loginUser(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDao.selectUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("Can't get data from DAO layer", e);
        }
        if (user != null) {
            if (user.isBanned()) {
                return user;
            }
            String saltedPassword = user.getSalt() + password;
            try {
                if (!Hasher.md5Hash(saltedPassword).equals(user.getHashedPassword())) {
                    return null;
                }
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                throw new ServiceException("Can't get md5 hash",e);
            }
        }
        return user;
    }


    @Override
    public boolean registerUser(User user) throws ServiceException {

        boolean result;
        boolean isNotExist = false;
        try {
            if (userDao.selectUserByEmail(user.getEmail()) == null) {
                if (userDao.selectUserByLogin(user.getLogin()) == null) {
                    isNotExist = true;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (isNotExist) {
            try {
                user.setSalt(SaltGenerator.getInstance().getSalt());
                String hashedPassword = Hasher.md5Hash(user.getSalt() + user.getPassword());
                user.setHashedPassword(hashedPassword);
                user.setRole(UserRole.USER);
                result = userDao.insertUser(user);
            } catch (DaoException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
                throw new ServiceException(e);
            }
            return result;
        } else {
            return false;
        }

    }

    @Override
    public boolean changeUserBanStatus(long userId, boolean banned) throws ServiceException {
        try {
            return userDao.updateUserBannedStatus(userId, banned);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        try {
            if (user.getPassword() != null) {
                String hashedPassword = Hasher.md5Hash(user.getSalt() + user.getPassword());
                user.setHashedPassword(hashedPassword);
            }
            return userDao.updateUser(user);
        } catch (DaoException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> selectAllUsers(int offset, int limit) throws ServiceException {
        try {
            return userDao.selectAllUsers(offset, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllUsers() throws ServiceException {
        try {
            return userDao.countAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User selectUserById(User user, long userId) throws ServiceException {
        if (user.getId() == userId) {
            return user;
        } else {
            if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER) {
                try {
                    return userDao.selectUserById(userId);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            } else {
                return null;
            }
        }
    }

    @Override
    public User selectUserByEmail(User user, String email) throws ServiceException {
        if (user.getEmail().equals(email)) {
            return user;
        } else {
            if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER) {
                try {
                    return userDao.selectUserByEmail(email);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            } else {
                return null;
            }
        }
    }

    @Override
    public User selectUserByLogin(User user, String login) throws ServiceException {
        if (user.getLogin().equals(login)) {
            return user;
        } else {
            if (user.getRole() == UserRole.ADMIN || user.getRole() == UserRole.MANAGER) {
                try {
                    return userDao.selectUserByLogin(login);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            } else {
                return null;
            }
        }
    }

}
