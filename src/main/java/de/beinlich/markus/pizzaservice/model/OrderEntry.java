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
    
    @Id
    @GeneratedValue
    private Integer orderEntryId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    @ManyToOne
    private OrderHeader orderHeader;

    public OrderEntry(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.price = menuItem.getPrice();
        this.quantity = menuItem.getQuantity();
    }

    public OrderEntry() {
    }


    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return price.multiply(new BigDecimal(quantity));
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

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
