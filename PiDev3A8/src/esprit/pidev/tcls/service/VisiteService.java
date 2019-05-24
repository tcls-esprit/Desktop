/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.tcls.service;

import com.pi.connectionBD.ConnectionBD;

import esprit.pidev.tcls.Entity.Visite;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.sql.Time;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bilel
 */
public class VisiteService implements CiteService<Visite> {
    private Connection con;
    private Statement ste;
    private ResultSet rs;
    
    public VisiteService(){
        con= ConnectionBD.getInstance().getCnx();
    }

    @Override
    public void insert(Visite t) {
       
        
         String sql = "insert into visite (date,h_debut,h_fin,prix,id_guide) values (?,?,?,?,?)";
         PreparedStatement pst = null;
        try {
            System.out.println(t);
            pst=con.prepareStatement(sql);
            pst.setDate(1, t.getDate());
            pst.setTime(2,t.getH_debut());
            pst.setTime(3,t.getH_fin());
            pst.setInt(4,t.getPrix());
            pst.setInt(5,t.getId_guide());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void deleted(int id ) {
      String sql = "delete from visite where id_visite="+id;
      PreparedStatement pst = null;
        try {
            pst=con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Visite t) {
    String sql="update guide set date=? h_debut=?,h_fin=?,prix= ? where id_visite="+t.getId_visite();
    PreparedStatement pst = null;
        try {
            pst=con.prepareStatement(sql);
            pst.setDate(1, t.getDate());
            pst.setTime(2, t.getH_debut());
            pst.setTime(3, t.getH_fin());
            pst.setInt(4, t.getPrix());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Visite> getAll() {
              String requete="Select * from visite";
        List<Visite> list=new ArrayList<>();
        try {
            ste=con.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                //System.out.println("ok");
                 Date timeStampDate =rs.getDate("date");
                 DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                 String formattedDate = df.format(timeStampDate);
                 System.out.println(formattedDate);
                 String formatted = rs.getTime(3).toString();
                 //System.out.println("ok");
                 System.out.println(formatted);
                 String formattedf = rs.getTime(4).toString();
                list.add(new Visite(rs.getInt(1),formattedDate,formatted,formattedf,rs.getInt(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void delete(Visite t) {
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
