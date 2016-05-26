package by.epam.webpharmacy.service;

import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.entity.Item;

import java.util.List;

/**
 * Represents an interface of a service for item-related actions
 */
public interface ItemService {

    /**
     * Retrieves all possible dosage forms from dao layer
     */
    List<DosageForm> getDosageForms() throws ServiceException;

    /**
     * Retrieves all possible volume types from dao layer
     */
    List<String> getVolumeTypes() throws ServiceException;

    /**
     * Attempts to add a new item with given parameters.
     * @param item item with given parameters
     * @return true if adding succeeded, false if item with such label, dosage and volume already exists
     * @throws ServiceException if DaoException occurred
     */
    boolean addItem(Item item) throws ServiceException;

    /**
     * Selects item by a given id
     * @param id id of the item
     * @return corresponding item
     * @throws ServiceException if exception occurred on an underlying level
     */
    Item selectItemById(long id) throws ServiceException;

    /**
     * Selects item by a given parameters
     * @param label item's label
     * @param dosageFormId item's dosageFormId
     * @param dosage item's  dosage
     * @param volume  item's volume or quantity
     * @param volumeType item's volume type
     * @param manufacturerId item's manufacturer id
     * @return item with all parameters from storage
     * @throws ServiceException
     */
    Item selectItemByLabelDosageVolume(String label, long dosageFormId, String dosage, double volume,
                                       String volumeType, long manufacturerId) throws ServiceException;

}
