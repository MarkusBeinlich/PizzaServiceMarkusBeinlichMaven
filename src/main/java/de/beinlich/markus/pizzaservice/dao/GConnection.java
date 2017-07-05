/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.beinlich.markus.pizzaservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Schulung
 */
public class GConnection {

    /**
     * Holt eine Datenbankverbindung.
     *
     * @return SQL Connection oder null wenn Fehler.
             try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Context ctx = new InitialContext();
            //Get a connection
            //DataSource ds = (DataSource) ctx.lookup("jdbc/meine");//
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza", "root", "secret");
            //Connection conn = ds.getConnection();
            //conn.setAutoCommit(false);
            //Connection  conn = ds.getConnection();
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     */
    public Connection getConnection() {
             try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Context ctx = new InitialContext();
            //Get a connection
            //DataSource ds = (DataSource) ctx.lookup("jdbc/meine");//
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pizza", "root", "secret");
            //Connection conn = ds.getConnection();
            //conn.setAutoCommit(false);
            //Connection  conn = ds.getConnection();
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
