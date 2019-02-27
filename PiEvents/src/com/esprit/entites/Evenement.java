/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entites;

import java.util.Date;

/**
 *
 * @author souissi oussama
 */

public class Evenement 
{
    
    
    private int id;
    private String titre ;
    private float prix;
    private String description;
    private int duree ;
    private int id_user;
    private int etat;
    private String image ;    

    
    private String type_event ;

    public Evenement(int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.etat = etat;
        this.image = image;
        this.type_event = type_event;
    }

    public Evenement(String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.etat = etat;
        this.image = image;
        this.type_event = type_event;
    }

    public Evenement(String titre, float prix, String description, int duree, int id_user, int etat, String type_event) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.etat = etat;
        this.type_event = type_event;
    }

    public Evenement(String titre, float prix, String description, int duree, int id_user, String image, String type_event) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.image = image;
        this.type_event = type_event;
    }

    public Evenement(String titre, float prix, String description, int duree, int id_user, String type_event) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.type_event = type_event;
    }
    

    
    
    

    public Evenement() {
    }
    
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public int getDuree() {
        return duree;
    }

    public int getId_user() {
        return id_user;
    }

    public int getEtat() {
        return etat;
    }

    public String getImage() {
        return image;
    }

    public String getType_event() {
        return type_event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", prix=" + prix + ", description=" + description + ", duree=" + duree + ", id_user=" + id_user + ", etat=" + etat + ", image=" + image + ", type_event=" + type_event + '}';
    }
    

    
}
