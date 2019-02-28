/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontheatre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;  
import java.util.logging.Logger;

 

/**
 *
 * @author ahmed
 */
public class ConnectionDBTheatre {
    private   String url = "jdbc:mysql://localhost:3306/bdtest";
    private   String login = "root";
    private   String   passwd = "";
    private Connection connexion ;
    private static ConnectionDBTheatre cnnx ;
    
    private ConnectionDBTheatre()
    {
        try {
            connexion = DriverManager.getConnection(url, login , passwd);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDBTheatre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionDBTheatre Open()
    {
      if (cnnx == null )
      {
        cnnx = new ConnectionDBTheatre();
      }
      return cnnx;
    }
    
    public Connection getConnexion()
    {
     return connexion;
    }

     

    
}
