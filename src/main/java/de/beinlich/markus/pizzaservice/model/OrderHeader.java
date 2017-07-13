package de.beinlich.markus.pizzaservice.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderHeader implements Serializable {

    private static final long serialVersionUID = 4994150745256346814L;

    
    @Id
    @GeneratedValue
    private Integer orderId;
    @OneToOne
    private Customer customer;
    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    private List<OrderEntry> orderEntries;

    private LocalDateTime orderDate;
    private String sessionId;
    private String ipAddress;

    public OrderHeader() {
        orderEntries = new ArrayList<>();
    }

    public Collection<OrderEntry> getOrderEntriesAsCollection() {
        return orderEntries;
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
            for (OrderEntry orderEntry : orderEntries) {
                table1.addCell(orderEntry.getName());
                table1.addCell(orderEntry.getDescription());
                table1.addCell(nfInteger.format(orderEntry.getQuantity()));
                table1.addCell(nfCurr.format(orderEntry.getAmount()));
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
            Logger.getLogger(OrderHeader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bos;
    }

    public void store() {
//        DaoOrder daoOrder = new DaoOrder();
//        DaoOrderEntry daoOrderEntry = new DaoOrderEntry();
//        this.orderId = daoOrder.store(this);
//        for (Map.Entry<MenuItem, OrderEntry> orderEntry : orderEntries.entrySet()) {
//            daoOrderEntry.store(orderEntry.getValue(), orderId);
//        }
    }

    public void addOrderEntry(OrderEntry orderEntry) {
        orderEntry.setOrderHeader(this);
        orderEntries.add(orderEntry);
    }

    public void removeOrderEntry(OrderEntry orderEntry) {
        orderEntries.remove(orderEntry);
    }


    public BigDecimal getAmount() {
        BigDecimal amount;
        amount = BigDecimal.ZERO;
        for (OrderEntry orderEntry : orderEntries) {
            amount = amount.add(orderEntry.getAmount());
        }
        return amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
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

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

}
