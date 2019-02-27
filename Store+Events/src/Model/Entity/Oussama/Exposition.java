/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Oussama;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author souissi oussama
 */
public class Exposition extends Evenement
{
    private int nombre_rayon;

    public Exposition(int nombre_rayon, int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        super(id, titre, prix, description, duree, id_user, etat, image, type_event);
        this.nombre_rayon = nombre_rayon;
    }

    public Exposition(int nombre_rayon, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        super(titre, prix, description, duree, id_user, etat, image, type_event);
        this.nombre_rayon = nombre_rayon;
    }

    public Exposition(int nombre_rayon, String titre, float prix, String description, int duree, int id_user, int etat, String type_event) {
        super(titre, prix, description, duree, id_user, etat, type_event);
        this.nombre_rayon = nombre_rayon;
    }

    public int getNombre_rayon() {
        return nombre_rayon;
    }

    public void setNombre_rayon(int nombre_rayon) {
        this.nombre_rayon = nombre_rayon;
    }

    @Override
    public String toString() {
        return "Exposition{" + super.toString() + "nombre_rayon=" + nombre_rayon + '}';
    }


}
