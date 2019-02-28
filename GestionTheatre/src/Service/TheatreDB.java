/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Theatre;
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
public class TheatreDB  {
    
   private ConnectionDBTheatre cnnx ;
   private PreparedStatement stat ; 
   private ResultSet res ;
   private Statement st ;
 
     public TheatreDB()
    {
       cnnx = ConnectionDBTheatre.Open();
    }
   
   
    
    public void AjouterSceneTheatrale(Theatre o , int idacteur) {
         
       String req1 = "select id_acteur from acteur where id_acteur=?";
       try {
           stat= cnnx.getConnexion().prepareStatement(req1);
           stat.setInt(1,idacteur);
       } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       
       
         String req = "insert into theatre (titre_scene,image,description,idacteur_fk) values (?,?,?,?)";
                
               try {
                   stat= cnnx.getConnexion().prepareStatement(req);
                   stat.setString(1, o.getTitre_scene()); 
                   stat.setString(2, o.getImage());
                   stat.setString(3, o.getDescription());
                   stat.setInt(4, idacteur);
                  
                   stat.executeUpdate();
                   System.out.println("Ajout d'une scene theatrale");
               } catch (SQLException ex) {
                   Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
               }
               
        
     }

   
    public void ModifierSceneTheatrale(Theatre o , int idacteur ) {
        String req1 = "select id_acteur from acteur where id_acteur=?";
       try {
           stat= cnnx.getConnexion().prepareStatement(req1);
           stat.setInt(1,idacteur);
       } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
        String req = "update theatre set titre_scene=? ,image=?, description=? ,idacteur_fk=?  where id_scene=?";
                try {
               
                    stat= cnnx.getConnexion().prepareStatement(req);
                    stat.setString(1, o.getTitre_scene());
                    stat.setString(2, o.getImage());
                    stat.setString(3,o.getDescription());
                    stat.setInt(4,idacteur);
                    stat.setInt(5,o.getCode_scene());                                                         
                    stat.executeUpdate();
                    System.out.println("Modif d'une scene theatrale");
                       } catch (SQLException ex) {
               Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
     }

     
    public void SupprimerSceneTheatrale(Theatre o ) {
            String req = "delete from theatre where id_scene=?";
         try { 
                if (o.getCode_scene() != 0){
                stat= cnnx.getConnexion().prepareStatement(req);
                stat.setInt(1, o.getCode_scene());                                                      
               stat.executeUpdate();
               System.out.println("Suppression d'une scene theatrale");
                }
                else 
                {
                    System.out.println("Rien à supprimer");
                }   
           } catch (SQLException ex) {
               Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
        
     }

    
    public void SupprimerTousSceneTheatrale() {
             String req = "delete  from theatre ";
           try {
               stat.executeUpdate(req);
           } catch (SQLException ex) {
               Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
           }
        
     }

     
    public List<Theatre> AllScene() {
        List <Theatre> tht = new ArrayList<>();
       String req = "select * from Theatre";
       try {
           st  =  cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
               tht.add(new Theatre(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5)));
           }        
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return tht ;
     }

     
    public void AjouterSceneThatraleParPoster(Theatre o) {
         String req = "insert into theatre (image) values (?)";
                
               try {
                   stat= cnnx.getConnexion().prepareStatement(req);                                      
                   stat.setString(1,o.getImage());                                   
                   stat.executeUpdate();
                   System.out.println("Ajout d'une image theatrale");
               } catch (SQLException ex) {
                   Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
               }
        
     }

      public Theatre getScene(Theatre t)
    {
        Theatre ac = new Theatre();
    
       try {
           String req = "select titre_scene,image,description from theatre where id_scene=?";
               stat= cnnx.getConnexion().prepareStatement(req);
               stat.setInt(1,t.getCode_scene()); 
               res = stat.executeQuery();
               if(res.next())
               {
                    ac.setTitre_scene(res.getString(1));
                    ac.setImage(res.getString(2));
                    ac.setDescription(res.getString(3));
               }
               
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return  ac;
    }
    public List<Theatre >RechercherTheatres(String champ) {
        List <Theatre> Thts = new ArrayList<>();
        
       String req = "select * from theatre where titre_scene like'%"+champ+"%' or image like'%"+champ+"%' or description like'%"+champ+"%'";
       try {
           st = cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
               Thts.add(new Theatre(res.getString("titre_scene"),res.getString("image"),res.getString("description"),res.getInt("idacteur_fk")));

           }        
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return Thts ;
     }
}
