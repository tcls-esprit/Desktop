/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;
 
import JDBC.ConnexionDB;
import com.esprit.entites.Gender;
import com.esprit.entites.TypeUser;
import com.esprit.entites.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class UserService implements Iservice<User>{
 private ConnexionDB cnx;
    
    PreparedStatement pst ;
    ResultSet rs;
    ArrayList <User> Users = new ArrayList<User>();
    public static User LoggedUser ; 
    
        public UserService() {
        cnx=ConnexionDB.getInstance();
    }
    @Override
    public void insert(User t) {
       try {
            
            String requete ="insert into user (nom,prenom,email,pwd,ddn,sexe,tel,ci,type,username) values (?,?,?,?,?,?,?,?,?,?) ";
            pst=cnx.getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getPassword());
            pst.setDate(5, (Date) t.getBirthDate());
            pst.setString(6, t.getGender().name());
            pst.setInt(7, t.getNumero());
            pst.setInt(8, t.getCin());
            pst.setString(9, t.getType().name());
            pst.setString(10, t.getUsername());
            pst.executeUpdate();
            System.out.println("User added");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            String requete = "delete from user where id="+id;
            pst=cnx.getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("user deleted");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void update(User t,int id) {
        try {
            String requete ="update user SET nom=?,prenom=?,email=?,pwd=?,ddn=?,sexe=?,tel=?,ci=?,type=?,username=? WHERE id="+t.getUserId();
            pst = cnx.getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getPassword());
            pst.setDate(5, (Date) t.getBirthDate());
            pst.setString(6, t.getGender().name());
            pst.setInt(7, t.getNumero());
            pst.setInt(8, t.getCin());
            pst.setString(9, t.getType().name());
            pst.setString(10, t.getUsername());
            pst.executeUpdate();
            System.out.println("user updated");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<User> GetAll() {
        try {
            String requete ="select * from user";
            pst=cnx.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                Users.add(new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6)
                        ,Gender.valueOf(rs.getString(7)),rs.getInt(8),rs.getInt(9),TypeUser.valueOf(rs.getString(10)) ,rs.getString(11))); 
            }
                } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Users;    }
        public boolean isLogin(String user, String pass) throws SQLException {

        String query = "select * from user where username = ?";

      //  String query = "select * from user where login = ? and password = ?";
        try {
            pst=cnx.getCnx().prepareStatement(query);
            pst.setString(1, user);
           
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {

                LoggedUser = new User();
                LoggedUser.setUserId(resultSet.getInt("id"));
                LoggedUser.setNom(resultSet.getString("nom"));
                LoggedUser.setPrenom(resultSet.getString("prenom"));
                LoggedUser.setBirthDate(resultSet.getDate("ddn"));
                LoggedUser.setGender(Gender.valueOf(resultSet.getString("sexe")));
                LoggedUser.setUsername(resultSet.getString("username"));
                LoggedUser.setPassword(resultSet.getString("pwd"));
                LoggedUser.setEmail(resultSet.getString("email"));
                LoggedUser.setType(TypeUser.valueOf(resultSet.getString("type")));
                LoggedUser.setNumero(resultSet.getInt("tel"));
                LoggedUser.setCin(resultSet.getInt("ci"));
               // return BCrypt.checkpw(pass, LoggedUser.getPassword());
               return true ; 
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public String getUserMail(int id)
    {
        String email="";
        String query = "select * from user where id ="+id;
     try {
         pst=cnx.getCnx().prepareStatement(query);
         rs=pst.executeQuery();
         while (rs.next())
         {
             email= rs.getString(4);
         }
     } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
        return email;
    }
    
    
}