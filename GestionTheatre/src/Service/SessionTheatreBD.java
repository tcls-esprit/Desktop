/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.SessionTheatre;
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
 * @author AQuel
 */
public class SessionTheatreBD {
    
    private ConnectionDBTheatre cnnx ;
   private PreparedStatement stat ; 
   private ResultSet res ;
   private Statement st;
   public SessionTheatreBD()
   {
       cnnx = ConnectionDBTheatre.Open();
    }
   
   
     
    public void AjouterSessionTheatre(SessionTheatre st , int idscene)  { 
        String req1 = "select id_scene from theatre where id_scene=?";
       try {
           stat= cnnx.getConnexion().prepareStatement(req1);
           stat.setInt(1,idscene);
       } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
        String req = "insert into sessiontheatre (id_salle,date_debut,date_fin,prix,idscene_fk) values (?,?,?,?,?)";
                                
        try {
            stat= cnnx.getConnexion().prepareStatement(req);
            
            stat.setInt(1,st.getId_salle());
             java.sql.Timestamp Date_debut = new java.sql.Timestamp(st.getDate_debut().getTime());
            stat.setTimestamp(2, Date_debut);
             java.sql.Timestamp Date_fin = new java.sql.Timestamp(st.getDate_fin().getTime());
            stat.setTimestamp(3,Date_fin);
            stat.setFloat(4, st.getPrix());
            stat.setInt(5,idscene);
            stat.executeUpdate();          
            System.out.println("Ajout d'une session theatrale");
        } catch (SQLException ex) {
            Logger.getLogger(SessionTheatreBD.class.getName()).log(Level.SEVERE, null, ex);
        }
             
                    
                
        
     }

  
    public void ModifierSessionTheatre(SessionTheatre st , int idscene) {
       
        String req1 = "select id_scene from theatre where id_scene=?";
       try {
           stat= cnnx.getConnexion().prepareStatement(req1);
           stat.setInt(1,idscene);
       } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       String req = "update sessiontheatre set  id_salle=?, date_debut=? , date_fin=? ,prix=? , idscene_fk=?  where id_ses=?";
                
             if ( st.getId_sess() != 0 ){
            try {
                 stat= cnnx.getConnexion().prepareStatement(req);
                 
                 stat.setInt(1,st.getId_salle());
                 java.sql.Timestamp Date_debut = new java.sql.Timestamp(st.getDate_debut().getTime());
                 stat.setTimestamp(2, Date_debut);
                 java.sql.Timestamp Date_fin = new java.sql.Timestamp(st.getDate_fin().getTime());
                 stat.setTimestamp(3,Date_fin);
                 stat.setFloat(4, st.getPrix());
                 stat.setInt(5,idscene);
                 stat.setInt(6,st.getId_sess());
                 stat.executeUpdate();
                System.out.println("Modif d'une session theatrale");
            } catch (SQLException ex) {
                Logger.getLogger(SessionTheatreBD.class.getName()).log(Level.SEVERE, null, ex);
            }
                
             }
           
        
        
     }

    
    public void SupprimerSessionTheatre(SessionTheatre st) {
            String req = "delete from sessiontheatre where id_ses=?";
         try { 
                if ( st.getId_sess() != 0){
                stat= cnnx.getConnexion().prepareStatement(req);
                stat.setInt(1, st.getId_sess());                                                      
               stat.executeUpdate();
                    System.out.println("Suppression d'une session ");
                }
                else 
                {
                    System.out.println("Rien à supprimer");
                }   
          } catch (SQLException ex) {
                Logger.getLogger(SessionTheatreBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
     }

     
    public void SupprimerTousSessionTheatre() {
             String req = "delete  from sessiontheatre ";
           try {
               stat.executeUpdate(req);
           } catch (SQLException ex) {
                Logger.getLogger(SessionTheatreBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        
     }

     
    public List<SessionTheatre> AllSession() {
        List <SessionTheatre> stht = new ArrayList<>();
       String req = "select * from sessiontheatre";
       try {
           st  =  cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
              stht.add(new SessionTheatre(res.getInt(1),res.getInt(2),res.getDate(3),res.getDate(4),res.getFloat(5),res.getInt(6)));
           }        
            } catch (SQLException ex) {
                Logger.getLogger(SessionTheatreBD.class.getName()).log(Level.SEVERE, null, ex);
            }
       
  return stht ;
     }
   
      public SessionTheatre getSession(SessionTheatre st)
    {
        SessionTheatre ac = new SessionTheatre();
    
       try {
           String req = "select * from sessiontheatre where id_ses=?";
               stat= cnnx.getConnexion().prepareStatement(req);
               stat.setInt(1,st.getId_sess()); 
               res = stat.executeQuery();
               if(res.next())
               {
                   
                    ac.setId_salle(res.getInt(1));
                    ac.setDate_debut(res.getTimestamp(2));
                    ac.setDate_fin(res.getTimestamp(3));
                    ac.setPrix(res.getFloat(4));
                    ac.setId_scene(res.getInt(5));
               }
               
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return  ac;
    }
       public List<String> AllSessionParScene() {
        List <String> stht = new ArrayList<>();
       String req =     "SELECT titre_scene ,date_debut,date_fin,nom,prenom\n" +
                        "FROM sessiontheatre,theatre,acteur \n" +
                        "WHERE sessiontheatre.idscene_fk = theatre.id_scene\n" +
                        "AND theatre.idacteur_fk = acteur.id_acteur;";
       try {
           st  =  cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
             stht.add(res.getString("titre_scene"));
             stht.add(res.getDate("date_debut").toString());
             stht.add(res.getDate("date_fin").toString());
             stht.add(res.getString("nom"));
             stht.add(res.getString("prenom"));
              
           }        
            } catch (SQLException ex) {
                Logger.getLogger(SessionTheatreBD.class.getName()).log(Level.SEVERE, null, ex);
            }
       
  return stht ;
     }
      
       public List<SessionTheatre >RechercherTheatres(String champ) {
        List <SessionTheatre> Sess = new ArrayList<>();
        
       String req = "select * from sessiontheatre where id_salle like '"+champ+"' or date_debut like'%"+champ+"%' or date_fin like'%"+champ+"%' or prix like'%"+champ+"%'";
       try {
           st = cnnx.getConnexion().createStatement();
           res = st.executeQuery(req);
           while (res.next()==true)
           {
               Sess.add( new SessionTheatre(res.getInt(1),res.getInt(2),res.getTimestamp(3), res.getTimestamp(4), res.getFloat(5)) );

           }        
           } catch (SQLException ex) {
           Logger.getLogger(TheatreDB.class.getName()).log(Level.SEVERE, null, ex);
       }
       
  return Sess ;
     }
}
