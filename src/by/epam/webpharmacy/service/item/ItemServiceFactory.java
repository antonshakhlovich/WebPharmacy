package by.epam.webpharmacy.service.item;

import by.epam.webpharmacy.service.item.impl.ItemServiceImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible Item services on demand.
 */
public class ItemServiceFactory {
    private static HashMap<ItemServiceName, ItemService> itemServiceMap = new HashMap<>();
    private static final ItemServiceFactory instance = new ItemServiceFactory();

    private ItemServiceFactory() {
        itemServiceMap.put(ItemServiceName.ITEM_SERVICE, new ItemServiceImpl());
    }

    public static ItemServiceFactory getInstance() {
        return instance;
    }

    public ItemService getService(ItemServiceName itemServiceName) {
        return itemServiceMap.get(itemServiceName);
    }
}