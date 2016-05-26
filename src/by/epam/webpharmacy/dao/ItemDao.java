package by.epam.webpharmacy.dao;

import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.entity.Item;

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
    /**
     * Retrieves all possible volume types from database
     */
    List<String> getVolumeTypes() throws DaoException;

    /**
     * Retrieves an item with given id.
     * @param id item's id
     * @return Item with corresponding id and other parameters or {@code null} if such item doesn't exist
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    Item selectItemById(long id) throws DaoException;

    /**
     * Retrieves an item with given id.
     * @param label item's label
     * @param dosage item's dosage
     * @param volume item's volume
     * @return Item with corresponding label,dosage and volume and other parameters or {@code null} if such item doesn't exist
     * @throws DaoException if failed to retrieve data from the storage due to technical problems
     */
    Item selectItemByLabelDosageVolume(String label, long dosageFormId, String dosage, double volume,
                                       String volumeType, long manufacturerId) throws DaoException;

    boolean insertItem(Item item) throws DaoException;
}
