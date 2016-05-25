package by.epam.webpharmacy.tests;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.impl.ItemDaoSQLImpl;
import by.epam.webpharmacy.dao.impl.UserDaoSQLImpl;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.entity.User;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;
import by.epam.webpharmacy.service.impl.ItemServiceImpl;
import by.epam.webpharmacy.util.JspPage;

import java.math.BigDecimal;

public class TestBundle {
    public static void main(String[] args) throws DaoException, ServiceException {
        JspPage jspPage = JspPage.valueOf("test");
        System.out.println(jspPage.toString());
    }
}
