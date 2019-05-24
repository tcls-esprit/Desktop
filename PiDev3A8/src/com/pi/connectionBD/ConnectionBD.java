/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.connectionBD;

/**
 *
 * @author Bilel
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionBD {
private  String url="jdbc:mysql://localhost:3306/tcls";
private  String login = "root";
private  String pwd="";
private  Connection cnx;
private static ConnectionBD instance;

  private ConnectionBD(){
try {
            cnx=DriverManager.getConnection(url, login,pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
   static public ConnectionBD getInstance() {
        if (instance==null)
            instance= new ConnectionBD();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
   
    
}
