package de.beinlich.markus.pizzaservice.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Markus Beinlich
 */
public class OrderEntry implements Serializable{

    private static final long serialVersionUID = -6948893238653679210L;

    private MenuItem menuItem;
    private int quantity;

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

}
