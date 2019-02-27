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


public class Concert extends Evenement
{
    private List <String> artistes = new ArrayList<>();
    private String type_concert ;

    public Concert(String type_concert, int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        super(id, titre, prix, description, duree, id_user, etat, image, type_event);
        this.type_concert = type_concert;
    }

    public Concert(String type_concert, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        super(titre, prix, description, duree, id_user, etat, image, type_event);
        this.type_concert = type_concert;
    }

    public Concert(String type_concert, String titre, float prix, String description, int duree, int id_user, int etat, String type_event) {
        super(titre, prix, description, duree, id_user, etat, type_event);
        this.type_concert = type_concert;
    }





    public List<String> getArtistes() {
        return artistes;
    }

    public String getType_concert() {
        return type_concert;
    }

    public void setArtistes(List<String> artistes) {
        this.artistes = artistes;
    }

    public void setType_concert(String type_concert) {
        this.type_concert = type_concert;
    }
    public void ajouter_artiste(String artiste )
    {
        if (!artistes.contains(artiste))
        artistes.add(artiste);
    }
    public void supprimer_artiste(String artiste )
    {
        artistes.remove(artiste);
    }
    public String display_artistes()
    {
        String s="";
        for(String e:artistes)
        {s+=e;}

        return s;
    }

    @Override
    public String toString() {
        return "Concert{"+super.toString() + "artistes=" + display_artistes() + ", type_concert=" + type_concert + '}' +"\n";
    }

}
