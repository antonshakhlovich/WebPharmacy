package by.epam.webpharmacy.dao.item;


import by.epam.webpharmacy.dao.item.impl.ItemDaoSQLImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible Item Daos on demand.
 */
public class ItemDaoFactory {
    private static HashMap<ItemDaoName, ItemDao> itemDaoMap = new HashMap<>();
    private static final ItemDaoFactory instance = new ItemDaoFactory();

    private ItemDaoFactory() {
        itemDaoMap.put(ItemDaoName.ITEM_DAO, new ItemDaoSQLImpl());
    }

    public static ItemDaoFactory getInstance() {
        return instance;
    }

    public ItemDao getDao(ItemDaoName ItemDaoName) {
        return itemDaoMap.get(ItemDaoName);
    }
}