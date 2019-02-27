
package com.esprit.services;

import JDBC.ConnexionDB;
import com.esprit.entites.Evenement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EvenementService 
{
    private ConnexionDB cnx;
    
    PreparedStatement pst ;
    ResultSet rs;
    ArrayList <Evenement> list = new ArrayList<Evenement>();

    public EvenementService() {
        cnx=ConnexionDB.getInstance();
    }
    
    public void insert(Evenement t) 
    {
        try {
            
            String requete ="insert into event (titre,prix,description,duree,id_user,etat,image,type_event) values (?,?,?,?,?,?,?,?) ";
            pst=cnx.getCnx().prepareStatement(requete);
            pst.setString(1, t.getTitre());
            pst.setFloat(2, t.getPrix());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getDuree());
            pst.setInt(5, t.getId_user());
            pst.setInt(6, t.getEtat());
            pst.setString(7, t.getImage());
            pst.setString(8, t.getType_event());
            pst.executeUpdate();
            System.out.println("Event ajouté avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void delete(int id) {
        try {
            String requete = "delete from event where id=?";
            pst=cnx.getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("event supprimé avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void update(Evenement t, int id) {
        try {
            String requete ="update event SET titre=?,prix=?,description=?,duree=?,id_user=?,etat=?,image=?,type_event=? WHERE id="+id;
            pst = cnx.getCnx().prepareStatement(requete);
            pst.setString(1, t.getTitre());
            pst.setFloat(2, t.getPrix());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getDuree());
            pst.setInt(5, t.getId_user());
            pst.setInt(6, t.getEtat());
            pst.setString(7, t.getImage());
            pst.setString(8, t.getType_event());
            pst.executeUpdate();
            System.out.println("event modifié avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    
    public ArrayList<Evenement> getAll() {
        try {
            String requete ="select * from event";
            pst=cnx.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                list.add(new Evenement(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9) ));
            }
                } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ObservableList<Evenement> DisplayAll() {
        ObservableList<Evenement> listeevent= FXCollections.observableArrayList();
        try {
            String requete ="select * from event";
            pst=cnx.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                listeevent.add(new Evenement(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9) ));
            }
                } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeevent;
    }
    public ObservableList<Evenement> DisplayAllEtat1() {
        ObservableList<Evenement> listeevent= FXCollections.observableArrayList();
        try {
            String requete ="select * from event where etat=1";
            pst=cnx.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                listeevent.add(new Evenement(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9) ));
            }
                } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeevent;
    }
    public List<String> liste_nom_event()
    {
        List<String> listenomevent = FXCollections.observableArrayList();
        String requete="select titre from event";
        try {
            pst=cnx.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                listenomevent.add(rs.getString("titre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listenomevent;
    }
    public ObservableList<Evenement> DisplayUserEvents(int id) {
        ObservableList<Evenement> listeevent= FXCollections.observableArrayList();
        try {
            String requete ="select * from event where id_user="+id;
            pst=cnx.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                listeevent.add(new Evenement(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9) ));
            }
                } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeevent;
    }
    public String getNomPrenomUser(int idu)
    {
        String req;
        String nom="";
        req="select nom,prenom from user where id="+idu;
        try {
            pst=cnx.getCnx().prepareStatement(req);
            rs=pst.executeQuery();
            while (rs.next())
            {
                nom=rs.getString(1)+" "+rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nom;
        
    }
    
}
