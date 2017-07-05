/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beinlich.markus.pizzaservice.servlet;

import de.beinlich.markus.pizzaservice.controller.OrderPizza;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Schulung_IBB
 */
@WebServlet(name = "myPdf", urlPatterns = "/generate/myPdf.pdf")
public class OrderPdfServlet extends HttpServlet {

    /**
     *
     * @param req
     * @param resp
     */
    //MyBean sessBean;
    @Inject
    private OrderPizza orderPizza;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            resp.setContentType("application/pdf");

            OutputStream os = resp.getOutputStream();
            orderPizza.getOrder().createPdf().writeTo(os);
            os.flush();
            os.close();

            //for ( PrintService s : PrintServiceLookup.lookupPrintServices( null, null ) )System.out.println( s.getName() );
        } catch (IOException ex) {
            Logger.getLogger(OrderPdfServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
