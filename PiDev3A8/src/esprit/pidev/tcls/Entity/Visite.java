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
public class Visite {
    private int id_visite;
    private Date date;
    private Time h_debut;
    private Time h_fin;
    private int prix;
    private int id_guide;
    
    private String d,hd,hf;
    public Visite(){}

    public Visite(Date date, Time h_debut, Time h_fin, int prix, int id_guide) {
        this.date = date;
        this.h_debut = h_debut;
        this.h_fin = h_fin;
        this.prix = prix;
        this.id_guide = id_guide;
    }
    
    public Visite(Date date, Time h_debut, Time h_fin, int prix, int id_guide, String d, String hd, String hf) {
        this.date = date;
        this.h_debut = h_debut;
        this.h_fin = h_fin;
        this.prix = prix;
        this.id_guide = id_guide;
        this.d = d;
        this.hd = hd;
        this.hf = hf;
    }

    public int getId_guide() {
        return id_guide;
    }

    public void setId_guide(int id_guide) {
        this.id_guide = id_guide;
    }
    
    public Visite(int id_visite,Date date, Time h_debut,Time h_fin, int prix) {
        this.id_visite = id_visite;
        this.date=date;
        this.h_debut =h_debut;
        this.h_fin=h_fin;
        this.prix = prix;
    }

    public Visite(Date date,Time h_debut,Time h_fin, int prix) {
        this.date=date;
        this.h_debut = h_debut;
        this.h_fin=h_fin;
        this.prix = prix;
    }
    public Visite(int id_visite,String d,String hd,String hf, int prix) {
        this.id_visite = id_visite;
        this.d=d;
        this.hd = hd;
        this.hf=hf;
        this.prix = prix;
    }

    public int getId_visite() {
        return id_visite;
    }

    public void setId_visite(int id_visite) {
        this.id_visite = id_visite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getH_debut() {
        return h_debut;
    }

    public void setH_debut(Time h_debut) {
        this.h_debut = h_debut;
    }

    public Time getH_fin() {
        return h_fin;
    }

    public void setH_fin(Time h_fin) {
        this.h_fin = h_fin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getHd() {
        return hd;
    }

    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getHf() {
        return hf;
    }

    public void setHf(String hf) {
        this.hf = hf;
    }

   

    @Override
    public String toString() {
        return "Visite{" + "id_visite=" + id_visite + ", date=" + date + ", h_debut=" + h_debut + ", h_fin=" + h_fin + ", prix=" + prix + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Visite other = (Visite) obj;
        if (this.id_visite != other.id_visite) {
            return false;
        }
        return true;
    }
    
    
}
