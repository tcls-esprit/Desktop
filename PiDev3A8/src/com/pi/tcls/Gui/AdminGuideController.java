/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import com.pi.connectionBD.ConnectionBD;
import esprit.pidev.tcls.Entity.Guide;
import esprit.pidev.tcls.service.GuideService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.TimeStringConverter;

/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class AdminGuideController implements Initializable {

    @FXML
    private TableView<Guide> guidetableadmin;
    @FXML
    private TableColumn<Guide,String> nomguide;
    @FXML
    private TableColumn<Guide,String> prenomguide;
    @FXML
    private TableColumn<Guide,Date> date;
    @FXML
    private TableColumn<Guide,Time> h_debut;
    @FXML
    private TableColumn<Guide,String> description;
    private ConnectionBD con;
    @FXML
    private TextField rechercher;
    
    /**
     * Initializes the controller class.
     */
    public AdminGuideController(){
        con=ConnectionBD.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guidetableadmin.setEditable(true);
        nomguide.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomguide.setCellFactory(TextFieldTableCell.forTableColumn());
        description.setCellFactory(TextFieldTableCell.forTableColumn());
       // h_debut.setCellFactory(TextFieldTableCell.forTableColumn(new TimeStringConverter()));
       // date.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
     
        initColumns();
        
        LoadData();
    } 
    public void initColumns() {
    nomguide.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomguide.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    h_debut.setCellValueFactory(new PropertyValueFactory<>("h_debut"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    @FXML
    public void LoadData(){
        GuideService gs = new GuideService();
    guidetableadmin.setItems(gs.getAll());
    nomguide.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenomguide.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    date.setCellValueFactory(new PropertyValueFactory<>("date"));
    h_debut.setCellValueFactory(new PropertyValueFactory<>("h_debut"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    
    }
    @FXML
    public void editName(TableColumn.CellEditEvent editedCell){
            Guide g = guidetableadmin.getSelectionModel().getSelectedItem();
            int id = g.getId();
            String sql ="update guide set nom=? where id='"+id+"'";
        try {
            PreparedStatement pst=con.getCnx().prepareStatement(sql);
            pst.setString(1, editedCell.getNewValue().toString());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminGuideController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    @FXML
    
    
    public void editPrenom(TableColumn.CellEditEvent editedCell) throws SQLException{
        Guide g = guidetableadmin.getSelectionModel().getSelectedItem();
        int id = g.getId();
        String sql = "update guide set prenom=? where id='"+id+"'";
         PreparedStatement pst=con.getCnx().prepareStatement(sql);
         pst.setString(1, editedCell.getNewValue().toString());
         pst.executeUpdate();
    }
    
    //@FXML
//    public void editHoraire(TableColumn.CellEditEvent editedCell) throws SQLException{
//          Guide g = guidetableadmin.getSelectionModel().getSelectedItem();
//        int id = g.getId();
//        String sql = "update guide set h_debut=? where id='"+id+"'";
//         PreparedStatement pst=con.getCnx().prepareStatement(sql);
//         pst.setTime(1, editedCell.getNewValue());
//         pst.executeUpdate();
//    }
//     @FXML
//    public void editDate(TableColumn.CellEditEvent editedCell) throws SQLException{
//          Guide g = guidetableadmin.getSelectionModel().getSelectedItem();
//        int id = g.getId();
//        String sql = "update guide set date=? where id='"+id+"'";
//         PreparedStatement pst=con.getCnx().prepareStatement(sql);
//         pst.setString(1, editedCell.getNewValue().toString());
//         pst.executeUpdate();
//    }
     @FXML
    public void editDescription(TableColumn.CellEditEvent editedCell) throws SQLException{
          Guide g = guidetableadmin.getSelectionModel().getSelectedItem();
        int id = g.getId();
        String sql = "update guide set description=? where id='"+id+"'";
         PreparedStatement pst=con.getCnx().prepareStatement(sql);
         pst.setString(1, editedCell.getNewValue().toString());
         pst.executeUpdate();
    }
    
    
    @FXML
    public void AjouterGuide(){
        
       
        try {
             FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AjoutGuide.fxml"));
        Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 600, 400);
              Stage stage = new Stage();
        stage.setTitle("Ajout de guide");
        stage.setScene(scene);
        stage.show();
 
        } catch (IOException ex) {
            Logger.getLogger(MuseeUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    public void SupprimerGuide(Event evt){
               if (!guidetableadmin.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression de guide");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer le guide :  "
                    + guidetableadmin.getSelectionModel().getSelectedItem().getNom()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                GuideService es = new GuideService();
                es.deleted(guidetableadmin.getSelectionModel().getSelectedItem().getNom());
                LoadData();
            }
        }
    }
    public void Refresh(){
        LoadData();
    }
    
    public void filtrer() throws SQLException{
        String s = rechercher.getText();
        GuideService gs = new GuideService();
        gs.filtrerGuide(s);
}
    
    
    @FXML
    public void Retour(Event evt){
        Stage stage = new Stage();
        stage.close();
    }
    
}
