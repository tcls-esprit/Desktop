/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

 
import java.util.Date;

/**
 *
 * @author AQuel
 */
public class SessionTheatre {
    private int id_sess;
    private int id_scene;
    private int id_salle;
    private Date date_debut;
    private Date date_fin ;
    private float prix ;
     
    public SessionTheatre(int id_sess,int id_salle,  Date date_debut, Date date_fin, float prix,int id_scene) {
        this.id_sess = id_sess;
        this.id_scene = id_scene;
        this.id_salle = id_salle ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        
    }
    public SessionTheatre(int id_scene,int id_salle,  Date date_debut, Date date_fin,float prix) {
        
        this.id_scene = id_scene;
        this.id_salle = id_salle ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix ;
        
        
    }
    public SessionTheatre(int id_salle,  Date date_debut, Date date_fin, float prix,int id_scene) {
        
       
        this.id_salle = id_salle ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
         this.id_scene = id_scene;
        
    }
    public SessionTheatre(){}

    public int getId_sess() {
        return id_sess;
    }

    
    public int getId_scene() {
        return id_scene;
    }

     

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setId_scene(int id_scene) {
        this.id_scene = id_scene;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

   
    public int getId_salle() {
        return id_salle;
    }

     
     
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id_scene;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionTheatre other = (SessionTheatre) obj;
        if (this.id_scene != other.id_scene) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SessionTheatre{" + "id_sess=" + id_sess + ", id_scene=" + id_scene + ", id_salle=" + id_salle + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix=" + prix + '}';
    }
    
    
    
    
}
