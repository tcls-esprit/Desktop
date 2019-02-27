/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import JDBC.ConnexionDB;
import com.esprit.entites.Commentaire;
import static com.esprit.services.UserService.LoggedUser;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author souissi oussama
 */
public class CommentaireService 
{
     private ConnexionDB cnx;
    
    PreparedStatement pst ;
    ResultSet rs;
    

    public CommentaireService() {
        cnx=ConnexionDB.getInstance();
    }
    public void AjouterComm(Commentaire c) throws SQLException{
        String req="INSERT INTO commentaire (contenu,id_user,id_event,owner,status) VALUES (?,?,?,?,?)";
        pst = cnx.getCnx().prepareStatement(req);
        pst.setString(1, c.getContenu());
        pst.setInt(2, c.getId_user());
        pst.setInt(3, c.getId_event());
        pst.setString(4, LoggedUser.getUsername());
        pst.setString(5, "non signalé");
        pst.executeUpdate();
        System.out.println("comm ajoutee");
       }
    public void update(Commentaire co,int id){
        try {
            String req;
            req = "UPDATE commentaire SET `contenu`=? WHERE id="+id;
            
            PreparedStatement pst=cnx.getCnx().prepareStatement(req);
            pst.setString(1, co.getContenu());
            //pst.setDate(2, new Date(118));
            pst.executeUpdate();
            //System.out.println(pst.execute());
            System.out.println("Modification avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateStatus(String status , int id)
    {
        String req;
            req = "UPDATE commentaire SET `status`=? WHERE id="+id;
            
            
         try {
             PreparedStatement pst=cnx.getCnx().prepareStatement(req);
             if (status.equals("non signalé"))
             pst.setString(1, "signalé");
             else 
             pst.setString(1, "non signalé");
             pst.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
            //pst.setDate(2, new Date(118));
            
            //System.out.println(pst.execute());
            System.out.println("status changed");
    }
    public void supprimer(int id) throws SQLException{

        String req = "DELETE FROM commentaire WHERE id="+id ;
        pst=cnx.getCnx().prepareStatement(req);
        
        pst.executeUpdate();
        System.out.println("suppression avec succès");
    }
    public ObservableList<Commentaire> DisplayAll() {
        ObservableList<Commentaire> listecom= FXCollections.observableArrayList();
             
        String requete ="select * from commentaire  "; 
        try {
             
             pst=cnx.getCnx().prepareStatement(requete);
             rs=pst.executeQuery();
             while (rs.next())
             {
                 
                 listecom.add(new Commentaire(rs.getInt(1), rs.getString(2), convert(rs.getDate(3)), rs.getInt(4), rs.getInt(5),rs.getString(6),rs.getString(7)));
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    return listecom;
    }
    public ObservableList<Commentaire> DisplaycomEvent(int id) {
        ObservableList<Commentaire> listecom= FXCollections.observableArrayList();
             
        String requete ="select  c.id,c.contenu,c.date,c.id_user,c.id_event,c.owner from commentaire c , event e where e.id=c.id_event and e.id="+id  ; 
        try {
             
             pst=cnx.getCnx().prepareStatement(requete);
             rs=pst.executeQuery();
             while (rs.next())
             {
                 
                 listecom.add(new Commentaire(rs.getInt(1), rs.getString(2), convert(rs.getDate(3)), rs.getInt(4), rs.getInt(5),rs.getString(6)));
             }
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    return listecom;
    }
    public double pourcentageCommentParTypeEvent(String type)
    {
        double nb=0;
        double nb2=0;
        String requete= "select count(*) from commentaire c , event e where c.id_event = e.id and e.type_event=?";
         try {
             System.out.print(type);
             pst=cnx.getCnx().prepareStatement(requete);
             pst.setString(1, type);
             rs = pst.executeQuery(); 
             rs.next() ;
             nb = rs.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
         String requete2= "select count(*) from commentaire" ;
         try {
             
             pst=cnx.getCnx().prepareStatement(requete2);
             rs = pst.executeQuery(); 
             rs.next() ;
             nb2 = rs.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
         System.out.println("nb "+nb+"nb2 "+nb2);
         return nb/nb2 * 100 ;
        
    }
    public int nombreDeCommentaires(String type)
    {
        int nb=0;
        String requete= "select count(*) from commentaire c , event e where c.id_event = e.id and e.type_event=?" ;
         try {
             
             System.out.print(type);
             pst=cnx.getCnx().prepareStatement(requete);
             pst.setString(1, type);
             rs = pst.executeQuery(); 
             rs.next() ;
             nb = rs.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return nb;
    }
  
    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }    
}        
