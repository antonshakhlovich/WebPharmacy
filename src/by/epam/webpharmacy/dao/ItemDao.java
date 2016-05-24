package by.epam.webpharmacy.dao;

import by.epam.webpharmacy.entity.DosageForm;

import java.util.List;

/**
 * Represents an interface for retrieving item-related data. Contains all methods, required for getting such data from
 * the database
 */
public interface ItemDao {
    /**
     * Retrieves all possible dosage forms from database
     */
    List<DosageForm> getDosageForms() throws DaoException;
}
