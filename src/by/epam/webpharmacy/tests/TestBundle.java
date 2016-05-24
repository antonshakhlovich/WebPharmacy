package by.epam.webpharmacy.tests;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.impl.UserDaoSQLImpl;
import by.epam.webpharmacy.entity.User;

public class TestBundle {
    public static void main(String[] args) throws DaoException {
        User user = UserDaoSQLImpl.getInstance().selectUserById(16);
        System.out.println(user.getLogin());

    }
}
