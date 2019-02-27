/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Service.Oussama;

import Model.ConnectionDB;
import Model.Entity.Oussama.Oeuvre;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author souissi oussama
 */
public class OeuvreService
{
     private Connection cnx;

    PreparedStatement pst ;
    ResultSet rs;
    ArrayList <Oeuvre> list = new ArrayList<>();
    public OeuvreService() {
        cnx=ConnectionDB.getInstance().getConnection();
    }
    public void insert (Oeuvre o)
    {
         try {
             String requete="insert into oeuvre (titre,datecreate,prix,type,id_exposition) values (?,?,?,?,?) ";
             pst=cnx.prepareStatement(requete);
             pst.setString(1, o.getTitre());
             java.sql.Date Date_deb = new java.sql.Date(o.getDateCreation().getTime());
             pst.setDate(2, Date_deb);
             pst.setFloat(3, o.getPrix());
             pst.setString(4, o.getType());
             pst.setInt(5, o.getId_exposition());
             pst.executeUpdate();
             System.out.println("oeuvre ajouté avec succès");
         } catch (SQLException ex) {
             Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    public void delete(int id)
    {

         try {
             String requete="delete from oeuvre where id=?";
             pst=cnx.prepareStatement(requete);
             pst.setInt(1, id);
             pst.executeUpdate();
             System.out.println("oeuvre supprimé avec succès");
         } catch (SQLException ex) {
             Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void update (Oeuvre o , int id)
    {
         try {
             String requete= "update oeuvre SET titre=?,datecreate=?,prix=?,type=?,id_exposition=? WHERE id="+id;
             pst=cnx.prepareStatement(requete);
             pst.setString(1, o.getTitre());
             java.sql.Date Date_deb = new java.sql.Date(o.getDateCreation().getTime());
             pst.setDate(2, Date_deb);
             pst.setFloat(3, o.getPrix());
             pst.setString(4, o.getType());
             pst.setInt(5, o.getId_exposition());
             pst.executeUpdate();
             System.out.println("oeuvre modifié avec succès");
         } catch (SQLException ex) {
             Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public ArrayList<Oeuvre> getAll()
    {
         try {
             String requete ="select * from oeuvre";
             pst=cnx.prepareStatement(requete);
             rs=pst.executeQuery();
             while (rs.next())
            {
                 list.add(new Oeuvre(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getFloat(4), rs.getString(5), rs.getInt(6)));
            }
            } catch (SQLException ex) {
             Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list ;
    }
    public ObservableList<Oeuvre> afficheSelonEvent(int id)
    {
        ObservableList<Oeuvre> listeOeuvre= FXCollections.observableArrayList();

        String req="select * from oeuvre where id_exposition="+id;

         try {
             pst=cnx.prepareStatement(req);
             rs=pst.executeQuery();
             while (rs.next())
             {
                 listeOeuvre.add(new Oeuvre (rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getFloat(4), rs.getString(5), rs.getInt(6)));
             }

         } catch (SQLException ex) {
             Logger.getLogger(OeuvreService.class.getName()).log(Level.SEVERE, null, ex);
         }

        return listeOeuvre;

    }

}
