/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beinlich.markus.pizzaservice.controller;

import de.beinlich.markus.pizzaservice.model.Customer1;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
        this.message="nicht eingeloggt";
        this.cust =new Customer1();
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
    
    public void doSomething(){
        
    }
    
    public String gotoLogin() {
        System.out.println("gotoLogin");
        return "toLogin";
    }
    
    public void login(){
        System.out.println("login");
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(cust.getEmail(), cust.getPassword());
            message="Sie haben sich erfolgreich authentifiziert";
        } catch (ServletException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
