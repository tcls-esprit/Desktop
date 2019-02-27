/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Service.Oussama;

import Model.ConnectionDB;
import Model.Entity.Oussama.Concert;
import Model.Entity.Oussama.Evenement;
import java.sql.Connection;
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
public class ConcertService
{
    private Connection cnx;

    PreparedStatement pst ;
    ResultSet rs;
    ArrayList <Concert> list = new ArrayList<>();

    public ConcertService() {
        cnx=ConnectionDB.getInstance().getConnection();
    }
    public void insert(Concert t)
    {int id=0;
        try {
            Evenement e = new Evenement(t.getTitre(), t.getPrix(), t.getDescription(),t.getDuree(), t.getId_user(),t.getEtat(),t.getImage(),t.getType_event());
            EvenementService es= new EvenementService();
            es.insert(e);
            String requete ="select * from event order by id DESC LIMIT 1";
            pst=cnx.prepareStatement(requete);
            rs= pst.executeQuery();


            while (rs.next())
            {
            id = rs.getInt(1);
            }
            String artistes="";
            for (String ch : t.getArtistes())
            {
                artistes=artistes+" "+ch;
            }

            requete ="insert into concert (id,liste_artistes,type_concert) values (?,?,?)" ;
            pst=cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, artistes);
            pst.setString(3, t.getType_concert());
            pst.executeUpdate();
            System.out.println("\n concert ajouté avec succès");
                    } catch (SQLException ex) {
            Logger.getLogger(ConcertService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(int id)
    {
        try {
            int id_session;
            String requete1 ="select id_session from sessionevent where ID_event=?";
            pst=cnx.prepareStatement(requete1);
            pst.setInt(1, id);
            rs=pst.executeQuery();
            while (rs.next())
            {
                SessionService ss= new SessionService();
                id_session= rs.getInt(1);
                ss.delete(id_session);
            }
            System.out.println("\n sessionsconcert  supprimés avec succès");
            EvenementService es= new EvenementService();
            es.delete(id);

            String requete = "delete from concert where id=?";
            pst=cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("\n concert supprimé avec succès");


        } catch (SQLException ex) {
            Logger.getLogger(ConcertService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Concert c, int id)
    {
        try {
            Evenement e = new Evenement(c.getTitre(), c.getPrix(), c.getDescription(),c.getDuree(), c.getId_user(),c.getEtat(),c.getImage(),c.getType_event());
            EvenementService es= new EvenementService();
            es.update(e , id);
            String requete ="update concert SET liste_artistes=?,type_concert=? WHERE id="+id ;
            String artistes="";
            for (String ch : c.getArtistes())
            {
                artistes=artistes+" "+ch;
            }
            pst=cnx.prepareStatement(requete);
            pst.setString(1, artistes);
            pst.setString(2, c.getType_concert());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConcertService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Concert> getAll ()
    {
        try {
            String requete ="select e.id,e.titre,e.prix,e.description,e.duree,e.id_user,e.etat,e.image,e.type_event,c.liste_artistes,c.type_concert  from event e inner join concert c on e.id = c.id" ;
            pst=cnx.prepareStatement(requete);
            rs=pst.executeQuery();
            while (rs.next())
            {   String artiste="";
                Character c1;
                Concert c=new Concert(rs.getString(11), rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9));
                String artistes = rs.getString(10);
                for (int i=0 ; i<artistes.length();i++)
                {
                    c1=artistes.charAt(i);
                    if(!c1.equals(' '))
                    {artiste=artiste+c1;

                    }
                    else
                    {
                        c.ajouter_artiste(artiste);
                        artiste=" ";
                    }
                }
                c.ajouter_artiste(artiste);

                list.add(c);
            }

            } catch (SQLException ex) {
            Logger.getLogger(ConcertService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String getNomPrenomUser(int idu)
    {
        String req;
        String nom="";
        req="select nom,prenom from user where id="+idu;
        try {
            pst=cnx.prepareStatement(req);
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
