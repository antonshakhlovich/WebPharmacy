package by.epam.webpharmacy.dao.order;

import by.epam.webpharmacy.dao.order.impl.OrderDaoSQLImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible Order Daos on demand.
 */
public class OrderDaoFactory {
    private static HashMap<OrderDaoName, OrderDao> orderDaos = new HashMap<>();
    private static final OrderDaoFactory instance = new OrderDaoFactory();

    private OrderDaoFactory() {
        orderDaos.put(OrderDaoName.ORDER_DAO, new OrderDaoSQLImpl());
    }

    public static OrderDaoFactory getInstance() {
        return instance;
    }

    public OrderDao getDao(OrderDaoName OrderDaoName) {
        return orderDaos.get(OrderDaoName);
    }
}
