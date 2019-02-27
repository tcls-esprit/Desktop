/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Oussama;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author souissi oussama
 */
public class Session {
    private int ID_salle;
    private Timestamp date_deb ;
    private Timestamp date_fin ;
    private int ID_event;
    private int ID_session;

    public Session(int ID_salle, Timestamp date_deb, Timestamp date_fin, int ID_event) {
        this.ID_salle = ID_salle;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.ID_event = ID_event;
    }

    public Session(Timestamp date_deb, Timestamp date_fin, int ID_event) {
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.ID_event = ID_event;
    }

    public Session(Timestamp date_deb, int ID_salle, int ID_event, Timestamp date_fin,  int ID_session) {
        this.ID_salle = ID_salle;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.ID_event = ID_event;
        this.ID_session = ID_session;
    }


    public int getID_salle() {
        return ID_salle;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getID_event() {
        return ID_event;
    }

    public int getID_session() {
        return ID_session;
    }

    public void setID_salle(int ID_salle) {
        this.ID_salle = ID_salle;
    }

    public void setDate_deb(Timestamp date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(Timestamp date_fin) {
        this.date_fin = date_fin;
    }

    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public void setID_session(int ID_session) {
        this.ID_session = ID_session;
    }

    @Override
    public String toString() {
        return "Session{" + "ID_salle=" + ID_salle + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", ID_event=" + ID_event + ", ID_session=" + ID_session + '}';
    }

}
