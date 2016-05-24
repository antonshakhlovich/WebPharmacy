package by.epam.webpharmacy.service;

import by.epam.webpharmacy.entity.DosageForm;

import java.util.List;

/**
 * Represents an interface of a service for item-related actions
 */
public interface ItemService {
    /**
     * Retrieves all possible dosage forms from dao layer
     */
    public List<DosageForm> getDosageForms() throws ServiceException;
}
