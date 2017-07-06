package de.beinlich.markus.pizzaservice.model;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.beinlich.markus.pizzaservice.dao.DaoOrder;
import de.beinlich.markus.pizzaservice.dao.DaoOrderEntry;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Order implements Serializable{

    private static final long serialVersionUID = 4994150745256346814L;

    private Customer customer;
    private Map<MenuItem, OrderEntry> orderEntries;
    private Integer orderId;
    private Date orderDate;
    private String sessionId;
    private String ipAddress;

    public Order() {
        orderEntries = new HashMap<>();
    }

    public Collection<OrderEntry> getOrderEntriesAsCollection() {
        return orderEntries.values();
    }

    public ByteArrayOutputStream createPdf() {
        Document document = new Document();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfPTable table1;
        PdfPTable table;
        NumberFormat nfInteger;
        nfInteger = NumberFormat.getIntegerInstance();
        NumberFormat nfCurr;
        nfCurr = NumberFormat.getCurrencyInstance();

        try {

            // document = new Document();
            //bos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, bos);

            document.open();

            document.add(new Paragraph("  Bestellung  "));
            table1 = new PdfPTable(4);
            table1.addCell("Name ");
            table1.addCell("Beschreibung");
            table1.addCell("Menge");
            table1.addCell("Betrag");
            for (Map.Entry<MenuItem, OrderEntry> orderEntry : orderEntries.entrySet()) {
                table1.addCell(orderEntry.getValue().getMenuItem().getName());
                table1.addCell(orderEntry.getValue().getMenuItem().getDescription());
                table1.addCell(nfInteger.format(orderEntry.getValue().getQuantity()));
                table1.addCell(nfCurr.format(orderEntry.getValue().getPrice()));
            }

            document.add(table1);
            document.add(new Paragraph("neue Tabelle"));
            document.add(new Paragraph(""));
            table = new PdfPTable(4);
            table.addCell("Nachname");

            table.addCell("Vorname");
            table.addCell("Email");
            table.addCell("Gesamtbetrag");
            table.addCell(this.customer.getLastName());
            table.addCell(this.customer.getFirstName());
            table.addCell(this.customer.getEmail());
            table.addCell(nfCurr.format(this.getAmount()));

            document.add(table);


            document.close();

            //for ( PrintService s : PrintServiceLookup.lookupPrintServices( null, null ) )System.out.println( s.getName() );

        } catch (DocumentException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bos;
    }
    
    public void store() {
        DaoOrder daoOrder = new DaoOrder();
        DaoOrderEntry daoOrderEntry = new DaoOrderEntry();
        this.orderId = daoOrder.store(this);
        for (Map.Entry<MenuItem, OrderEntry> orderEntry : orderEntries.entrySet()) {
            daoOrderEntry.store(orderEntry.getValue(), orderId);
        }
    }

    public void addOrderEntry(OrderEntry orderEntry) {
        orderEntries.put(orderEntry.getMenuItem(), orderEntry);
    }
    
    public void removeOrderEntry(OrderEntry orderEntry) {
        orderEntries.remove(orderEntry.getMenuItem());
    }

    public int getOrderEntryQuantity(MenuItem menuItem) {
        if (orderEntries.containsKey(menuItem)) {
            return orderEntries.get(menuItem).getQuantity();
        }
        return 0;
    }

    public BigDecimal getAmount() {
        BigDecimal amount;
        amount = BigDecimal.ZERO;
        for (Map.Entry<MenuItem, OrderEntry> orderEntry : orderEntries.entrySet()) {
            amount = amount.add(orderEntry.getValue().getPrice());
        }
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<MenuItem, OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(Map<MenuItem, OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
