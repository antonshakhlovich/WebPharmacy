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
    public List<DosageForm> getDosageForms() throws ServiceException;

    /**
     * Retrieves all possible volume types from dao layer
     */
    public List<String> getVolumeTypes() throws ServiceException;

    /**
     * Attempts to add a new item with given parameters.
     * @param item item with given parameters
     * @return true if adding succeeded, false if item with such label, dosage and volume already exists
     * @throws ServiceException if DaoException occurred
     */
    public boolean addItem(Item item) throws ServiceException;

}
