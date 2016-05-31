package by.epam.webpharmacy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code Order} represents a user's order for a list of items. Contains creation date, in case it was already
 * submitted. Order's owner is also specified.
 */
public class Order implements Serializable{
    private long id;
    private long customerId;
    private Timestamp timestamp;
    private Date creationDate;
    private BigDecimal amount;
    private String orderStatus;
    private boolean isCanceled;
    private Map<Item, Integer> items = new HashMap<>();

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (customerId != order.customerId) return false;
        if (isCanceled != order.isCanceled) return false;
        if (timestamp != null ? !timestamp.equals(order.timestamp) : order.timestamp != null) return false;
        if (creationDate != null ? !creationDate.equals(order.creationDate) : order.creationDate != null) return false;
        if (amount != null ? !amount.equals(order.amount) : order.amount != null) return false;
        if (orderStatus != null ? !orderStatus.equals(order.orderStatus) : order.orderStatus != null) return false;
        return items != null ? items.equals(order.items) : order.items == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (customerId ^ (customerId >>> 32));
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (isCanceled ? 1 : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
