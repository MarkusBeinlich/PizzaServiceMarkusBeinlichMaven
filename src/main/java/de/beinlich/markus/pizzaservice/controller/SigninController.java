/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beinlich.markus.pizzaservice.controller;

import de.beinlich.markus.pizzaservice.model.Customer1;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author schulung
 */
@ManagedBean
@SessionScoped
public class SigninController {

    private Customer1 cust;
    private String message;

    public SigninController() {
        this.message = "nicht eingeloggt";
        this.cust = new Customer1();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer1 getCust() {
        return cust;
    }

    public void setCust(Customer1 cust) {
        this.cust = cust;
    }

    public void doSomething() {

    }

    public String gotoLogin() {
        System.out.println("gotoLogin");
        return "toLogin";
    }

    public void login() {

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        System.out.println("login with " + cust.getEmail() + cust.getPassword() );
        loggedIn = false;
        message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(cust.getEmail(), cust.getPassword());
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", cust.getEmail());
        } catch (ServletException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
            context.addCallbackParam("loggedIn", loggedIn);
        }
    }

}
