package de.beinlich.markus.pizzaservice.dao;

import de.beinlich.markus.pizzaservice.model.OrderEntry;
import java.math.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoOrderEntry extends GConnection{
    
    public void store(OrderEntry orderEntry, Integer orderId) {

	Integer autoKey=0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs=null;
       
        try {

            con = getConnection();
            if (con == null) {
                return;
            }
            stm = con.prepareStatement("INSERT INTO orderentry (orderid, menuitemid, quantity, amount) VALUES(?,?,?,?)");
            stm.setInt(1, orderId);
            stm.setInt(2, orderEntry.getMenuItem().getMenuItemId());
            stm.setInt(3, orderEntry.getQuantity());
            stm.setBigDecimal(4, orderEntry.getPrice());
            
            int rows = stm.executeUpdate();
//            con.commit();
           
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
    }
}
