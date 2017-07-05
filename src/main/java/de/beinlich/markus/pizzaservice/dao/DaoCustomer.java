package de.beinlich.markus.pizzaservice.dao;

import de.beinlich.markus.pizzaservice.model.Customer;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Schulung
 */
public class DaoCustomer extends GConnection {

    public List<Customer> getList() {

        List<Customer> customers = new ArrayList();

        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            if (con == null) {
                return null;
            }
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT firstname,lastname,email,phone,postcode,street,town FROM customer");

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customer.setEmail(rs.getString("email"));
               

                customers.add(customer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return customers;
    }

    public Integer store(Customer customer) {

	Integer autoKey=0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs=null;
       
        try {

            con = getConnection();
            if (con == null) {
                return 0;
            }
            stm = con.prepareStatement("INSERT INTO customer (firstname,lastname,email,creationdate) VALUES(?,?,?,CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, customer.getFirstName().trim());
            stm.setString(2, customer.getLastName().trim());
            stm.setString(3, customer.getEmail().trim());
            
            int rows = stm.executeUpdate();
//            con.commit();
            // Vergebener Schlüssel z.B durch AutoIncrement holen
            rs = stm.getGeneratedKeys();

            if (rs.next()) {
                autoKey = rs.getInt(1);
            } else {

                // throw an exception from here
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoCustomer.class.getName()).log(Level.SEVERE, null, ex);
          
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return autoKey;
    }

}
