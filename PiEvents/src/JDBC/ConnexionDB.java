/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author souissi oussama
 */
public class ConnexionDB 
{
    private  String url="jdbc:mysql://localhost:3306/tcls";
    private  String login="root";
    private  String pwd="";
    private  Connection cnx;
    private static ConnexionDB con;

    private ConnexionDB()
    {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection établie");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ConnexionDB getInstance()
    {
        if (con==null)
            con=new ConnexionDB();
        return con;
    }
    public static void main (String[] args)
    {
        ConnexionDB a=new ConnexionDB();
    }
    public Connection getCnx()
    {
        return cnx;
    }
}
