/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Acteur;
import gestiontheatre.ConnectionDBTheatre;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class ActeurDB {
    
    private ConnectionDBTheatre cnnx ;
   private PreparedStatement stat ; 
   private ResultSet res ;
  private Statement st ;
   public ActeurDB()
   {
       cnnx = ConnectionDBTheatre.Open();
    }
   public void AjouterActeur(Acteur a)
   {
   String req = "insert into acteur  (nom,prenom) values (?,?)";
               try {
                    stat= cnnx.getConnexion().prepareStatement(req);
                    stat.setString(1, a.getNom());
                    stat.setString(2,a.getPrenom()); 
                
                    stat.executeUpdate();
                    System.out.println("Ajout d'un acteur avec succes ");
               } catch (SQLException ex) {
                   Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
               }
   }
   
   public void ModifierActeur(Acteur a , int id)
   {
   String req = "update acteur  set nom=? , prenom=? where id_acteur=?";
               try {
                    stat= cnnx.getConnexion().prepareStatement(req);
                    stat.setString(1, a.getNom());
                    stat.setString(2,a.getPrenom());
                    stat.setInt(3,id) ;
                   stat.executeUpdate();
                   System.out.println("Modifier d'un acteur avec succes ");
               } catch (SQLException ex) {
                   Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
               }
   }
   
   public void SupprimerActeur(Acteur a)
   {
   String req = "delete from acteur  where id_acteur=?";
         try { 
                if (a.getId() != 0){
                stat= cnnx.getConnexion().prepareStatement(req);
                stat.setInt(1,a.getId());  
                System.out.println("Supprimer d'un acteur avec succes ");
                stat.executeUpdate();
                }
                else 
                {
                    System.out.println("Rien à supprimer");
                }   
           } catch (SQLException ex) {
               Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
           }
    
    }
   public void SupprimerTousActeurs() {
             String req = "delete  from acteur  ";
           try {
               stat.executeUpdate(req);
               System.out.println("Supprimer les acteurs avec succes ");
           } catch (SQLException ex) {
               Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
           }
        
     }
    public List<Acteur> AllActeurs() {
        List <Acteur> Acts = new ArrayList<>();
       String req = "select * from acteur ";
       try {
           st = cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
               Acts.add(new Acteur(res.getInt(1),res.getString(2),res.getString(3)));
           }        
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return Acts ;
     }
    public List<Acteur> RechercherActeurs(String champ) {
        List <Acteur> Acts = new ArrayList<>();
        
       String req = "select * from acteur where nom like'%"+champ+"%' or prenom like'%"+champ+"%'";
       try {
           st = cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
               Acts.add(new Acteur(res.getString("nom"),res.getString("prenom")));
           }        
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return Acts ;
     }
    
    public Acteur getActeur(Acteur a)
    {
        Acteur ac = new Acteur();
    
       try {
           String req = "select nom , prenom from acteur where id_acteur=?";
               stat= cnnx.getConnexion().prepareStatement(req);
               stat.setInt(1,a.getId()); 
               res = stat.executeQuery();
               if(res.next())
               {
                    ac.setNom(res.getString(1));
                    ac.setPrenom(res.getString(2));
               }
               
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return  ac;
    }
}