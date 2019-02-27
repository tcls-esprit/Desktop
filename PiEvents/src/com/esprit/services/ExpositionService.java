/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import JDBC.ConnexionDB;
import com.esprit.entites.Evenement;
import com.esprit.entites.Exposition;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author souissi oussama
 */
public class ExpositionService 
{
     private ConnexionDB cnx;
    
    PreparedStatement pst ;
    ResultSet rs;
    ArrayList <Exposition> list = new ArrayList<>();

    public ExpositionService() {
        cnx=ConnexionDB.getInstance();
    }
    public void insert(Exposition ex) 
    {int id = 0;
        try 
        {
             Evenement e = new Evenement(ex.getTitre(), ex.getPrix(), ex.getDescription(),ex.getDuree(), ex.getId_user(),ex.getEtat(),ex.getImage(),ex.getType_event());
             EvenementService es= new EvenementService();
             es.insert(e);
             String requete ="select * from event order by id DESC LIMIT 1";
             pst=cnx.getCnx().prepareStatement(requete);
             rs= pst.executeQuery();
             
            while (rs.next())
            {
            id = rs.getInt(1);
            }
             requete ="insert into exposition (id,nombre_rayon) values (?,?)" ;
             pst=cnx.getCnx().prepareStatement(requete);
             pst.setInt(1, id);
             pst.setInt(2, ex.getNombre_rayon());
             
             pst.executeUpdate();
             System.out.println("\n exposition ajouté avec succès");
        } catch (SQLException ex1) 
            {
             Logger.getLogger(ExpositionService.class.getName()).log(Level.SEVERE, null, ex1);
            }
                    
    }
    public void delete(int id) 
    {
        
        try 
        {
             int id_session;
             int id_oeuvre;
             String requete1 ="select id_session from sessionevent where ID_event=?";
             pst=cnx.getCnx().prepareStatement(requete1);
             pst.setInt(1, id);
             rs=pst.executeQuery();
             while (rs.next())
             {
                 SessionService ss= new SessionService();
                 id_session= rs.getInt(1);
                 ss.delete(id_session);
             }
             System.out.println(" sessionsexposition  supprimés avec succès");
             
             String requete2 ="select id from oeuvre where id_exposition=?";
             pst=cnx.getCnx().prepareStatement(requete2);
             pst.setInt(1, id);
             rs=pst.executeQuery();
             while (rs.next())
             {
                 OeuvreService ss= new OeuvreService();
                 id_oeuvre= rs.getInt(1);
                 ss.delete(id_oeuvre);
             }
             System.out.println(" id_oeuvresexposition  supprimés avec succès");
             
             EvenementService es= new EvenementService();
             es.delete(id);
             
             String requete = "delete from exposition where id=?";
             pst=cnx.getCnx().prepareStatement(requete);
             pst.setInt(1, id);
             pst.executeUpdate();
             System.out.println("exposition supprimée avec succès");
        } catch (SQLException ex) {
             Logger.getLogger(ExpositionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Exposition c, int id)
    {
        try {
            Evenement e = new Evenement(c.getTitre(), c.getPrix(), c.getDescription(),c.getDuree(), c.getId_user(),c.getEtat(),c.getImage(),c.getType_event());
            EvenementService es= new EvenementService();
            es.update(e , id);
            String requete ="update exposition SET nombre_rayon=?" ;
            pst=cnx.getCnx().prepareStatement(requete);
            pst.setInt(1, c.getNombre_rayon());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConcertService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Exposition> getAll ()
    {
         try {
             String requete ="select e.id,e.titre,e.prix,e.description,e.duree,e.id_user,e.etat,e.image,e.type_event,expo.nombre_rayon  from event e inner join exposition expo on e.id = expo.id" ;
             pst=cnx.getCnx().prepareStatement(requete);
             rs=pst.executeQuery();
             while (rs.next())
             {
                 list.add(new Exposition(rs.getInt(10),rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9)));
             }
         } catch (SQLException ex) {
             Logger.getLogger(ExpositionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return list;
    }
    
    
}
