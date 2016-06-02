package by.epam.webpharmacy.service.order;

import by.epam.webpharmacy.service.order.impl.OrderServiceImpl;

import java.util.HashMap;

/**
 * This class based on factory design pattern and provides all possible order services on demand.
 */
public class OrderServiceFactory {
    private static HashMap<OrderServiceName, OrderService> orderServiceMap = new HashMap<>();
    private static final OrderServiceFactory instance = new OrderServiceFactory();

    private OrderServiceFactory() {
        orderServiceMap.put(OrderServiceName.ORDER_SERVICE, new OrderServiceImpl());
    }

    public static OrderServiceFactory getInstance() {
        return instance;
    }
    public OrderService getService(OrderServiceName orderServiceName) {
        return orderServiceMap.get(orderServiceName);
    }
}
