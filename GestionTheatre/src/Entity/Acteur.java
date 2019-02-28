/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javafx.scene.control.Button;

/**
 *
 * @author ahmed
 */
public class Acteur {
    
   private int id ;
   private String nom ;
   private String prenom ;
  
   
   public Acteur (){};
   
    public Acteur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public Acteur(String nom, String prenom)
    {
   
        this.nom = nom;
        this.prenom = prenom;
       
    }
    

    public int getId() {
        return id;
    }

    

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

     
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
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
        final Acteur other = (Acteur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return    " Nom: " + nom + "    Prénom:  " + prenom ;
    }
      
     
    
}
