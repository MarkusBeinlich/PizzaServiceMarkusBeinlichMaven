package de.beinlich.markus.pizzaservice.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Invoice {

    private Customer customer;
    private List<OrderEntry> invoiceEntries;
    private BigDecimal invoiceAmount;
    private Order order;
    private String invoiceId;

    public Invoice() {
    }

    public Invoice(Customer customer, Order order) {
        this.customer = customer;
        this.order = order;
        this.invoiceAmount = order.getAmount();
        //eigentlich wäre hier eine tiefe Kopie angebracht. 
        this.invoiceEntries = new ArrayList(order.getOrderEntries().values());
    }

    
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderEntry> getInvoiceEntries() {
        return invoiceEntries;
    }

    public void setInvoiceEntries(List<OrderEntry> invoiceEntries) {
        this.invoiceEntries = invoiceEntries;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
}
