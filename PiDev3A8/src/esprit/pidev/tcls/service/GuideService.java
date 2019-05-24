/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.pidev.tcls.service;

import esprit.pidev.tcls.Entity.Guide;
import com.pi.connectionBD.ConnectionBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Bilel
 */
public class GuideService implements CiteService<Guide> {
    private ConnectionBD con;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    
    public GuideService(){
    con= ConnectionBD.getInstance();
    }    
    

    @Override
    public void insert(Guide t) {
        String sql = "insert into guide(nom,prenom,date,h_debut,description) values (?,?,?,?,?)";
        try {
            pst=con.getCnx().prepareStatement(sql);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getPrenom());
            pst.setNull(3,java.sql.Types.DATE);
            pst.setNull(4,java.sql.Types.TIME);
            pst.setString(5,t.getDescription());
            
            pst.executeUpdate();
            System.out.println("-------------");
           
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void AjouterGuide(String s1,String s2, String s3){
        
            String sql="Insert into guide(nom,prenomn,description) values('"+s1+"','"+s2+"','"+s3+"'";
        try {  
            ste = con.getCnx().createStatement();
//            ste.setString(2,s1);
//            ste.setString(3,s2);
//            ste.setString(5,s3);
            pst.executeUpdate(sql);
        }
        catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void deleted(String nom) {
    try {
            String sql="Delete from guide where nom=?";
            pst=con.getCnx().prepareStatement(sql);
            pst.setString(1,nom);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void updated(Guide t,String nom) {
    String sql="update guide set date=?,h_debut? where nom='"+nom+"'";
        try {
            pst=con.getCnx().prepareStatement(sql);
           
            pst.setTime(3, t.getH_debut());
            pst.setDate(4, t.getDate());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void update(Guide t) {
    String sql="update guide set date=?,h_debut? where nom='"+t.getNom()+"'";
        try {
            pst=con.getCnx().prepareStatement(sql);
           
            pst.setTime(3, t.getH_debut());
            pst.setDate(4, t.getDate());
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Guide> getAll() {
        String requete="Select nom,prenom,date,h_debut,description from guide";
        ObservableList<Guide> listGuide= FXCollections.observableArrayList();
        try {
            ste=con.getCnx().createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                
                listGuide.add(new Guide(rs.getString(1),rs.getString(2),rs.getTime(4),rs.getDate(3),rs.getString(5)));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGuide;
    }
    public ObservableList<Guide> getAllg() {
        String requete="Select id,nom,prenom from guide";
        ObservableList<Guide> listGuide= FXCollections.observableArrayList();
        try {
            pst=con.getCnx().prepareStatement(requete);
            rs=pst.executeQuery();
            while(rs.next()){
                
                listGuide.add(new Guide(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom")));
                
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listGuide;
    }
    public List<Guide> filtrerGuide(String s) throws SQLException {
        List<Guide> guides = new ArrayList<>();
        String rq = "select * from guide where id like'"+s+"' or name like'"+s+"' or price like'"+s+"'  or quantity like'"+s+"' or category like'"+s+"' or description like'"+s+"'";

        Statement st = con.getCnx().createStatement();
        ResultSet rst = st.executeQuery(rq);

        while (rst.next()) {
            Guide g=new Guide(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getTime(4),rst.getDate(5),rst.getString(6));
            guides.add(g);
        }

        return guides;
    }

    @Override
    public void delete(Guide t) {
        
    }
    
    
}
