package by.epam.webpharmacy.dao.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.util.ConnectionPool;
import by.epam.webpharmacy.entity.DosageForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * This is an implementation of the {@see ItemDao} interface.
 */
public class ItemDaoSQLImpl implements ItemDao {

    private static final String SELECT_DOSAGE_FORMS = "SELECT id, name FROM drugs_dosage_forms";

    @Override
    public List<DosageForm> getDosageForms() throws DaoException {
        DosageForm dosageForm;
        Connection cn = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = cn.prepareStatement(SELECT_DOSAGE_FORMS);
        ResultSet resultSet =
    }
}
