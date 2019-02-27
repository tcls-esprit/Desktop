/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import GUI.SendMail;
import JDBC.ConnexionDB;
import JDBC.ConnexionDB;
import com.esprit.entites.Commentaire;
import com.esprit.entites.Concert;
import com.esprit.entites.Evenement;
import com.esprit.entites.Exposition;
import com.esprit.entites.Gender;
import com.esprit.entites.Oeuvre;
import com.esprit.entites.Session;
import com.esprit.entites.TypeUser;
import com.esprit.entites.User;
import com.esprit.services.CommentaireService;
import com.esprit.services.ConcertService;
import com.esprit.services.EvenementService;
import com.esprit.services.ExpositionService;
import com.esprit.services.OeuvreService;
import com.esprit.services.SessionService;
import com.esprit.services.UserService;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;

/**
 *
 * @author souissi oussama
 */
public class Test {
    
    public static void main(String[] args) throws SQLException
    {
        ConnexionDB con = ConnexionDB.getInstance();
        System.out.println(con);
        /*
        Evenement e = new Evenement("hhhhh", 15, "test", 22 , 1, 1, "src", "seminaire");
        EvenementService es = new EvenementService();
        es.insert(e);
        
        //concert        
        Concert c= new Concert("rock", "titre1", (float)558.3, "description1", 20, 3, 1, "img1", "concert");
        Concert c2= new Concert("metal", "titre2", (float)558.3, "description2", 60, 2, 1, "img2", "concert");
        c2.ajouter_artiste("artiste1");
        c2.ajouter_artiste("artiste2");
        ConcertService cs = new ConcertService();
        cs.insert(c);
        cs.insert(c2);
        for (Concert cc :cs.getAll() )
        {
            System.out.print(cc);
        }
       // cs.update(c2, 1);
        //cs.update(c, 2);
        
        
        //exposition
        Exposition e = new Exposition(3, "expo1", 11, "okk", 11, 1, 0, "img", "exposition");
        Exposition e2 = new Exposition (4, "expo2", 11, "okk", 11, 1, 0, "img", "exposition");
        ExpositionService es=new ExpositionService();
        es.insert(e);
        es.update(e2, 3);
        es.update(e, 4);
        for (Exposition ee :es.getAll() )
        {
            System.out.print(ee);
        }
        
         
        //session
        Session s = new Session(6,new Timestamp(12,6,1996,21,03,11,0), new Timestamp(12,6,1996,22,03,11,0), 1);
        Session s1 = new Session(5,new Timestamp(12,6,1999,21,03,11,0), new Timestamp(12,6,1999,22,03,11,0), 2);
        Session s2 = new Session(8,new Timestamp(12,5,2018,22,03,11,0), new Timestamp(12,6,1996,22,03,11,0), 3);
        Session s5 = new Session(8,new Timestamp(12,5,2018,22,03,11,0), new Timestamp(12,6,1996,22,03,11,0), 3);
        Session s3 = new Session(7,new Timestamp(12,5,2018,22,03,11,0), new Timestamp(12,5,2018,22,03,11,0), 4);
        Session s4 = new Session(10,new Timestamp(12,5,2018,22,03,11,0), new Timestamp(12,5,2018,22,03,11,0), 4);
        Session s6 = new Session(10,new Timestamp(12,5,2018,22,03,11,0), new Timestamp(12,5,2018,22,03,11,0), 1);
        SessionService ss=new SessionService();
        
        ss.insert(s1);
        ss.insert(s2);
        ss.insert(s3);
        ss.insert(s4);
        ss.insert(s5);
        ss.insert(s6);
        
        
        //oeuvre
        Oeuvre o1=new Oeuvre("sculpture", (float) 12.2, new Date(12,6,1996,21,03,11), "aaaaa",4);
        Oeuvre o4=new Oeuvre("sculpture", (float) 12.2, new Date(12,6,1996,21,03,11), "aaaaa",4);
        Oeuvre o2=new Oeuvre("livre", (float) 13.2, new Date(12,6,1996,21,03,11), "aaaaa", 3);
        Oeuvre o3=new Oeuvre("peinture", (float) 15.2, new Date(12,6,1996,21,03,11), "aaaaa", 3);
        Oeuvre o5=new Oeuvre("livre", (float) 13.2, new Date(12,6,1996,21,03,11), "aaaaa", 3);
        OeuvreService os= new OeuvreService();
        os.insert(o1);
        os.insert(o2);
        os.insert(o3);
        os.insert(o5);
        os.insert(o4);
        */
        /*
        os.delete(3);
        for(Oeuvre o : os.getAll())
        {
            System.out.println(o);
        }
     
        //ConcertService cs = new ConcertService();
        cs.delete(1);
        
        cs.delete(2);
        
        //ExpositionService es= new ExpositionService();
        es.delete(3);
        es.delete(4);
       
        
       SendMail.sendmail("starjet1900@gmail.com",
                     "Annulation d'événement", "nous sommes désolés mais l'événement est annulé");
        */
       // Commentaire c1 = new Commentaire("ddddd", "eeee", 1, 2,"rr");
       // Commentaire c2 = new Commentaire("dddddhh", "eebee", 1,2, "rrrrr");
        CommentaireService cs = new CommentaireService();
        //cs.AjouterComm(c1);
        //cs.AjouterComm(c2);
        cs.supprimer(5);
        /*
        User u1 = new User("oussama", "souissi", "souissi.oussama11@gmail.com", "azerty", new Date(96,03,25), Gender.Male, 25694782, 96874125, TypeUser.Admin, "oussamasouissi1");
        User u2 = new User("sami", "sassi", "starjet1900@gmail.com", "qwerty", new Date(98,03,26), Gender.Male, 26294782, 99871125, TypeUser.SimpleUser, "oussamasouissi2");  
        User u3 = new User("salim", "bani", "salimbani1793@gmail.com", "qwerty", new Date(91,03,26), Gender.Male, 28294782, 91171125, TypeUser.SimpleUser, "oussamasouissi3");
        UserService us = new UserService();
        us.insert(u3);
        us.insert(u2);
        us.insert(u1);
        */
    }
}
