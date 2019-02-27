/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Service.Oussama;

import Model.ConnectionDB;
import Model.Entity.Oussama.Evenement;
import Model.Entity.Oussama.Session;
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
public class SessionService
{
    private Connection cnx;

    PreparedStatement pst ;
    ResultSet rs;
    ArrayList <Session> list = new ArrayList<>();
    public SessionService() {
        cnx=ConnectionDB.getInstance().getConnection();
    }
    public void insert(Session s)
    {
        try {
            String requete="insert into sessionevent (date_deb,id_salle,id_event,date_fin) values (?,?,?,?) ";
            pst=cnx.prepareStatement(requete);
            java.sql.Timestamp Date_deb = new java.sql.Timestamp(s.getDate_deb().getTime());
            pst.setTimestamp(1, Date_deb);
            pst.setInt(2, s.getID_salle());
            pst.setInt(3, s.getID_event());
            java.sql.Timestamp Date_fin = new java.sql.Timestamp(s.getDate_fin().getTime());
            pst.setTimestamp(4, Date_fin);
            pst.executeUpdate();
            System.out.println("session ajouté avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(SessionService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void delete(int id) {
        try {
            String requete = "delete from sessionevent where id_session=?";
            pst=cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("session supprimé avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(SessionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update (Session s , int id)
    {
        try {
            String requete="update sessionevent SET date_deb=?,ID_salle=?,id_event=?,date_fin=? WHERE id_session="+id;
            pst=cnx.prepareStatement(requete);
            java.sql.Date Date_deb = new java.sql.Date(s.getDate_deb().getTime());
            pst.setDate(1, Date_deb);
            pst.setInt(2, s.getID_salle());
            pst.setInt(3, s.getID_event());
            java.sql.Date Date_fin = new java.sql.Date(s.getDate_fin().getTime());
            pst.setDate(4, Date_fin);
                    } catch (SQLException ex) {
            Logger.getLogger(SessionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Session> getAll() {
        try {
            String requete ="select * from sessionevent";
            pst=cnx.prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                list.add(new Session(rs.getTimestamp(1),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4),rs.getInt(5) ));
            }
                } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ObservableList<Session> getSelonEvent(int id)
    {
        ObservableList<Session> listeevent= FXCollections.observableArrayList();
        String requete ="select * from sessionevent where id_event="+id;
        try {
            pst=cnx.prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {
                listeevent.add(new Session(rs.getTimestamp(1),rs.getInt(2),rs.getInt(3),rs.getTimestamp(4),rs.getInt(5) ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeevent;
    }
}
