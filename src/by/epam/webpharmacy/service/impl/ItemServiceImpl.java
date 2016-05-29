package by.epam.webpharmacy.service.impl;

import by.epam.webpharmacy.dao.DaoException;
import by.epam.webpharmacy.dao.ItemDao;
import by.epam.webpharmacy.dao.impl.ItemDaoSQLImpl;
import by.epam.webpharmacy.entity.DosageForm;
import by.epam.webpharmacy.entity.Item;
import by.epam.webpharmacy.service.ItemService;
import by.epam.webpharmacy.service.ServiceException;

import java.util.List;

/**
 * A singleton implementation of the {@see ItemService} interface, using {@see ItemDaoSQLImpl} as an underlying level
 */
public class ItemServiceImpl implements ItemService {
    private static ItemDao itemDao = ItemDaoSQLImpl.getInstance();
    private static ItemService instance = new ItemServiceImpl();

    private ItemServiceImpl() {
    }

    public static ItemService getInstance() {
        return instance;
    }

    @Override
    public List<DosageForm> getDosageForms() throws ServiceException {
        try {
            return itemDao.getDosageForms();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> getVolumeTypes() throws ServiceException {
        try {
            return itemDao.getVolumeTypes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addItem(Item item) throws ServiceException {
        try {
            if (itemDao.selectItemByLabelDosageVolume(item.getLabel(), item.getDosageFormId(), item.getDosage(),
                    item.getVolume(), item.getVolumeType(), item.getManufacturerId()) != null) {
                return false;
            } else {
                return itemDao.insertItem(item);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Item selectItemById(long id) throws ServiceException {
        try {
            return itemDao.selectItemById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Item selectItemByLabelDosageVolume(String label, long dosageFormId, String dosage, double volume,
                                              String volumeType, long manufacturerId) throws ServiceException {
        try {
            return itemDao.selectItemByLabelDosageVolume(label, dosageFormId, dosage, volume, volumeType, manufacturerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Item> selectAllItems(int offset, int limit) throws ServiceException {
        try {
            return itemDao.selectAllItems(offset, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countAllItems() throws ServiceException {
        try {
            return itemDao.countAllItems();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Item> selectItemsByLabel(String label, int offset, int limit) throws ServiceException {
        try {
            return itemDao.selectItemsByLabel(label, offset, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countItemsByLabel(String label) throws ServiceException {
        try {
            return itemDao.countItemsByLabel(label);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
