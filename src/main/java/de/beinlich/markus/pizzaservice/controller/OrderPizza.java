package de.beinlich.markus.pizzaservice.controller;

import de.beinlich.markus.pizzaservice.model.*;
import java.io.Serializable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Markus Beinlich
 */
@Named
@SessionScoped
public class OrderPizza implements Serializable {

    private static final long serialVersionUID = 4711892445353241012L;

    private Customer customer;
    private Invoice invoice;
    private Order order;
    private String time;
    private Menu menu;
    private Boolean submitted;

    public OrderPizza() {
        customer = new Customer();
        invoice = new Invoice();
        order = new Order();
        menu = new Menu();
        submitted = false;
    }

    public void submitOrder() {
        this.save();
        submitted = true;
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Vielen Dank für Ihre Bestellung", "Guten Appetit.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public void save() {
        System.out.println("OrderPizza - save");
        System.out.println("OrderPizza.save: ip-" + order.getIpAddress() + " session: " + order.getSessionId());
        customer.store();
        order.setCustomer(customer);
        order.store();
    }

    public void showPdf() {
        try {
//            RequestContext.getCurrentInstance().getApplicationContext().
//            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            System.out.println("showPdf context: " + context);
            context.redirect("generate/myPdf.pdf");
        } catch (IOException ex) {
            Logger.getLogger(OrderPizza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addOrderEntry() {
        System.out.println("addOrderEntry");
        for (MenuItem menuItem : menu.getMenuItems()) {
            if (menuItem.getQuantity() != 0) {
                order.addOrderEntry(new OrderEntry(menuItem));
            } else {
                order.removeOrderEntry(new OrderEntry(menuItem));
            }
        }
    }

    public String startOrder() {
        return "toCustomer";
    }

    public String login() {
        System.out.println("-------------------login");
        return "toLogin";
    }

    public String customerEntered() {
        return "customerEntered";
    }

    public void setIpAndSession(HttpServletRequest req) {
        order.setIpAddress(req.getRemoteAddr());
        HttpSession sess = req.getSession();
        order.setSessionId(sess.getId());
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return new Invoice(customer, order);
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Order getOrder() {
        System.out.println("getOrder");
        return order;
    }

    public void setOrder(Order order) {
        System.out.println("setOrder");
        this.order = order;
    }

    public String getSessionId() {
        return order.getSessionId();
    }

    public void setSessionId(String sessionId) {
        order.setSessionId(sessionId);
    }

    public String getIpAddress() {
        return order.getIpAddress();
    }

    public void setIpAddress(String ipAddress) {
        order.setIpAddress(ipAddress);
    }

    public String getTime() {
        return "a" + String.valueOf(System.currentTimeMillis()) + "x";
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

}
