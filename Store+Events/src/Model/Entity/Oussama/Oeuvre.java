/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Oussama;

import java.util.Date;

/**
 *
 * @author souissi oussama
 */

public class Oeuvre
{
    private String type ;
    private float prix ;
    private Date DateCreation;
    private String titre ;
    private int id_exposition ;
    private int id;

    public Oeuvre(int id,String titre,Date DateCreation,float prix,String type, int id_exposition ) {
        this.type = type;
        this.prix = prix;
        this.DateCreation = DateCreation;
        this.titre = titre;
        this.id_exposition = id_exposition;
        this.id = id;
    }

    public Oeuvre(String type, float prix, Date DateCreation, String titre, int id_exposition) {
        this.type = type;
        this.prix = prix;
        this.DateCreation = DateCreation;
        this.titre = titre;
        this.id_exposition = id_exposition;
    }






    public String getType() {
        return type;
    }

    public float getPrix() {
        return prix;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public String getTitre() {
        return titre;
    }

    public int getId_exposition() {
        return id_exposition;
    }

    public int getId() {
        return id;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDateCreation(Date DateCreation) {
        this.DateCreation = DateCreation;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId_exposition(int id_exposition) {
        this.id_exposition = id_exposition;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "type=" + type + ", prix=" + prix + ", DateCreation=" + DateCreation + ", titre=" + titre + ", id_exposition=" + id_exposition + ", id=" + id + '}';
    }

}
