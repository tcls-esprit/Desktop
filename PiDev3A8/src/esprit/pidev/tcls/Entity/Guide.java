/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.tcls.Entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Bilel
 */
public class Guide {
    private int id;
    private String nom;
    private String prenom;
    private Time h_debut;
    private Date date;
    private String description;
    

    public Guide() {
    }
public Guide(int id,String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.id=id;
    }
    
    

    public Guide(int id, String nom, String prenom, Time h_debut, Date date, String description) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.h_debut = h_debut;
        this.date = date;
        this.description = description;
    }

    public Guide(String nom, String prenom, Time h_debut, Date date, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.h_debut = h_debut;
        this.date = date;
        this.description = description;
    }

    public Guide(String nom, String prenom, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Time getH_debut() {
        return h_debut;
    }

    public void setH_debut(Time h_debut) {
        this.h_debut = h_debut;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Guide other = (Guide) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  nom +" "+ prenom ;
    }
    


    
    
    }
    
    
    
    
    

