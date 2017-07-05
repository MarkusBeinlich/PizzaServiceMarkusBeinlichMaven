
package de.beinlich.markus.pizzaservice.dao;

import de.beinlich.markus.pizzaservice.model.MenuItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Schulung
 */
public class DaoMenu extends GConnection {

    public List<MenuItem> getMenu() {

       List<MenuItem> menu = new ArrayList<>();
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            if (con == null) {
                return null;
            }
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT menuitemid, name, price, description FROM menuitem");

            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setMenuItemId(rs.getInt("menuitemid"));
                menuItem.setName(rs.getString("name"));
                menuItem.setDescription(rs.getString("description"));
                menuItem.setPrice(rs.getBigDecimal("price"));

                menu.add(menuItem);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DaoMenu.class.getName()).log(Level.SEVERE, null, e);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DaoMenu.class.getName()).log(Level.SEVERE, null, e);
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Logger.getLogger(DaoMenu.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return menu;
    }

}
