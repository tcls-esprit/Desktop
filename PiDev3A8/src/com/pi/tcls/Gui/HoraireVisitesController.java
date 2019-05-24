/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.pi.connectionBD.ConnectionBD;
import esprit.pidev.tcls.Entity.Guide;
import esprit.pidev.tcls.Entity.Visite;
import esprit.pidev.tcls.service.GuideService;
import esprit.pidev.tcls.service.VisiteService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class HoraireVisitesController implements Initializable {
    @FXML
    private JFXTimePicker heureDebut;
    @FXML
    private JFXTimePicker HeureFin;
    @FXML
    private JFXDatePicker datepick;
    private JFXDatePicker datepick1;
    private JFXTimePicker heureDebut2;
    private JFXTimePicker HeureFin2;
    @FXML
    private Button confirmerHoraire;
    @FXML
    private AnchorPane PaneVisite;
    public static Time hdeb;
    public static Date d;
    
    private ConnectionBD con;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public static Time t;
    
   
    @FXML
    private JFXComboBox<Guide> comboguide;
        
    /**
     * Initializes the controller class.
     */
    
    public HoraireVisitesController(){
        con=ConnectionBD.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setData();
        } catch (SQLException ex) {
            Logger.getLogger(HoraireVisitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

   
    public void setData() throws SQLException{
       String query="select nom,prenom from guide";
      
       GuideService gs = new GuideService();
       final ObservableList<Guide> options = FXCollections.observableArrayList(gs.getAllg());
      // comboguide.setItems(options);
       for(Guide g:gs.getAllg()){
       comboguide.getItems().addAll(g);
           System.out.println(gs.getAllg());
          
               }
       
       
//       for(Guide g : gs.getAll()){
//           guide.add(g);
//       }
       
//       guidecombo = new JFXComboBox(FXCollections.observableArrayList());
       
       
    }
    
    @FXML
    public  void Confirmer(ActionEvent evt){
        
        Date date1 = java.sql.Date.valueOf(datepick.getValue());
        Time Hdebut = java.sql.Time.valueOf(heureDebut.getValue());
        //System.out.println(date1);
       
        Time Hfin = java.sql.Time.valueOf(HeureFin.getValue());
        long Duree = ChronoUnit.MINUTES.between(HeureFin.getValue(),heureDebut.getValue());
        
        //System.out.println(datepick.getValue());
        Visite v = new Visite(date1, Hdebut, Hfin, 0,comboguide.getValue().getId());
        //System.out.println(v);
        VisiteService vs = new VisiteService();
      if(Duree<30){
             vs.insert(v);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);

           
                     alert.setTitle("Feliciation");
            alert.setHeaderText("Reservation effectuée !");
            Optional<ButtonType> result1 = alert.showAndWait();
            
        
             
        }
         else {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("la durée de visite ne peut pas dépasser 30 minutes.");
            Optional<ButtonType> result = alert.showAndWait();
             
        }
    }
    public Date getDate(){
       return java.sql.Date.valueOf(datepick.getValue());
    }
    public Time getTime(){
       return java.sql.Time.valueOf(heureDebut.getValue());
    }
            
    
   
//   @FXML
//    
//    public static void UpdateGuide(int id){
//         d = java.sql.Date.valueOf(datepick.getValue());
//        hdeb = java.sql.Time.valueOf(heureDebut.getValue());
//        System.out.println(d);
//        System.out.println(hdeb); 
//        String sql ="update guide set h_debut='"+hdeb+"',date='"+d+"'Where id = '"+id+"'";
//         try {
//            pst=con.getCnx().prepareStatement(sql);
//            
//            pst.setDate(1,d);
//            pst.setTime(1,hdeb);
//            pst.executeUpdate();
//           // pst2.executeUpdate(sql2);
//        } catch (SQLException ex) {
//            Logger.getLogger(AdminGuideController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
   

    private void modifier(ActionEvent event) throws ParseException, IOException {
        AdminVisitesController q = new AdminVisitesController();
        Visite v = q.modifier();
        System.out.println(v);
        datepick1.setValue(v.getDate().toLocalDate());
        heureDebut2.setValue(v.getH_debut().toLocalTime());
        HeureFin2.setValue(v.getH_fin().toLocalTime());
    }
}
