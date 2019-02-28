/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

 
import java.util.Objects;

/**
 *
 * @author ahmed
 */
public class Theatre {
    
    private int code_scene ; 
    private String titre_scene ;    
    private String image ;
    private String description ; 
    private int id_acteur ;
    

    
   
    public Theatre(){}

    public Theatre(int code_scene, String titre_scene,String image, String description,int id_acteur) {
             this.code_scene = code_scene;
             this.titre_scene = titre_scene;
             this.image = image;
             this.description = description ;
             this.id_acteur=id_acteur;
             
    }
    public Theatre( String titre_scene,String image, String description, int id_acteur ) {
             
             this.titre_scene = titre_scene;
             this.description = description ;
             this.image = image;
             this.id_acteur = id_acteur;
             
    }

    public int getId_acteur() {
        return id_acteur;
    }

    public void setId_acteur(int id_acteur) {
        this.id_acteur = id_acteur;
    }
    
    
  
    
    public int getCode_scene() {
        return code_scene;
    }

     
    public String getTitre_scene() {
        return titre_scene;
    }

    public void setTitre_scene(String titre_scene) {
        this.titre_scene = titre_scene;
    }

    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
 public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
     

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.code_scene;
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
        final Theatre other = (Theatre) obj;
        if (this.code_scene != other.code_scene) {
            return false;
        }
        if (!Objects.equals(this.titre_scene, other.titre_scene)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return  "Titre de la scéne:  " + titre_scene +" Scénario:   " + description;
    }

    

    
    
    
    
    
    
}
