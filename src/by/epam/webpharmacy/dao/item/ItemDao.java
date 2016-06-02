package by.epam.webpharmacy.dao.item;

import by.epam.webpharmacy.dao.DaoException;
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

    /**
     * Insert item to the storage, e.g. database
     * @param item is given item
     * @return true if item was added to the storage, and false if such item already exists in database
     * @throws DaoException
     */
    boolean insertItem(Item item) throws DaoException;
    /**
     * Update item in the storage, e.g. database
     * @param item is given item
     * @return true if item was updated, and false if not
     * @throws DaoException
     */
    boolean updateItem(Item item) throws DaoException;

    /**
     * Delete item from the storage, e.g. database
     * @param id is item's id
     * @return true if item was deleted, and false if item is used in orders
     * @throws DaoException
     */
    boolean deleteItem(long id) throws DaoException;


    /**
     * Retrieves a list of all items from {@param offset}. List's max limit is {@param limit}.
     * @param offset set the number of the first row from request
     * @param limit set the number of items that will be listed
     * @return list that contains limited number of items.
     * @throws DaoException
     */
    List<Item> selectAllItems(int offset, int limit) throws DaoException;

    /**Counts all items in the storage.
     * @return number of items .
     * @throws DaoException
     */
    int countAllItems() throws DaoException;

    /**
     * Retrieves a list of items with given label from {@param offset}. List's max limit is {@param limit}.
     * @param offset set the number of the first row from request
     * @param limit set the number of items that will be listed
     * @return list that contains limited number of items.
     * @throws DaoException
     */
    List<Item> selectItemsByLabel(String label, int offset, int limit) throws DaoException;

    /**Counts only currently searched items in the storage.
     * @return number of items .
     * @throws DaoException
     */
    int countItemsByLabel(String label) throws DaoException;
}
