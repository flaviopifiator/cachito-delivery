/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.*;

/**
 *
 * @author Seba Cabrera
 */
public class BaseDeDatos {
    
     protected static Connection instance = null;
     public static Connection getInstance() throws ClassNotFoundException, SQLException {

        if (instance == null) {
            String url = "jdbc:postgresql://localhost:5432/cachito";  
            String driver = "org.postgresql.Driver";
            String user = "postgres";
            String pass = "1234";
            Class.forName(driver);
            instance = DriverManager.getConnection(url, user, pass);
        }
         
        return instance;
    }
}
