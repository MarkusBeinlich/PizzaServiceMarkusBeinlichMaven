package de.beinlich.markus.pizzaservice.dao;

import de.beinlich.markus.pizzaservice.model.Order;
import java.math.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoOrder extends GConnection{
    
     public Integer store(Order order) {

	Integer autoKey=0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs=null;
       
        try {

            con = getConnection();
            if (con == null) {
                return 0;
            }
            System.out.println("DaoOrder.store: ip-" + order.getIpAddress() + " session: " + order.getSessionId());
            stm = con.prepareStatement("INSERT INTO orderheader (customerid, ipaddress, sessionid, amount, orderdate) VALUES(?,?,?,?,CURRENT_TIMESTAMP)", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, order.getCustomer().getCustomerId());
            stm.setString(2, order.getIpAddress());
            stm.setString(3, order.getSessionId());
            stm.setBigDecimal(4, order.getAmount());
            
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
