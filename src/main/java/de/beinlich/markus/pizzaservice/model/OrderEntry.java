package de.beinlich.markus.pizzaservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Markus Beinlich
 */
@Entity
public class OrderEntry implements Serializable{

    private static final long serialVersionUID = -6948893238653679210L;
    @GeneratedValue
    @Id
    private Integer orderEntryId;
    @OneToOne
    private MenuItem menuItem;
    private int quantity;
    @ManyToOne
    private Order order;

    public OrderEntry(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.quantity = menuItem.getQuantity();
    }

    public OrderEntry() {
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return menuItem.getPrice().multiply(new BigDecimal(quantity));
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderEntryId() {
        return orderEntryId;
    }

    public void setOrderEntryId(Integer orderEntryId) {
        this.orderEntryId = orderEntryId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
