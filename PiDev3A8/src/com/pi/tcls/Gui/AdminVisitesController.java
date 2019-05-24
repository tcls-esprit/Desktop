/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import com.jfoenix.controls.JFXButton;
import esprit.pidev.tcls.Entity.Guide;
import esprit.pidev.tcls.Entity.Visite;
import esprit.pidev.tcls.service.GuideService;
import esprit.pidev.tcls.service.VisiteService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class AdminVisitesController implements Initializable {

    @FXML
    private AnchorPane visitetableadmin;
    @FXML
    
    private TableView<Visite> visiteTable;
    @FXML
    private TableColumn<Visite,String> dateVisite;
    @FXML
    private TableColumn<Visite,String> heureDeb;
    @FXML
    private TableColumn<Visite,String> heureFin;
    @FXML
    private TableColumn<Visite,String> prix;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateVisite.setCellFactory(TextFieldTableCell.forTableColumn());
        heureDeb.setCellFactory(TextFieldTableCell.forTableColumn());
        heureFin.setCellFactory(TextFieldTableCell.forTableColumn());
        initColumns();
        loadData();
        
    }
        private void initColumns() {
        dateVisite.setCellValueFactory(new PropertyValueFactory<>("d"));
        heureDeb.setCellValueFactory(new PropertyValueFactory<>("hd"));
        heureFin.setCellValueFactory(new PropertyValueFactory<>("hf"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }    
    private void loadData() {
        ObservableList<Visite> data = null;
        data = FXCollections.observableArrayList(new VisiteService().getAll());
        visiteTable.setItems(data);
        System.out.println(data);
    }
    
    @FXML
    public void Supprimer(){
          if (!visiteTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d evenement");
              System.out.println(visiteTable.getSelectionModel().getSelectedItem().getId_visite());
            alert.setHeaderText("etes-vous sur que vous voulez supprimer evenement:  "
                    + visiteTable.getSelectionModel().getSelectedItem().getD()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                VisiteService es = new VisiteService();
                es.deleted(visiteTable.getSelectionModel().getSelectedItem().getId_visite());
                
            }
    }
    
}

    @FXML
    private void callAjoutUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("HoraireVisites.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Effectuez votre reservation");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void refresh(ActionEvent event) {
        loadData();
    }

    @FXML
    public Visite modifier() throws ParseException, IOException {
        Visite V = new Visite();
        Visite X = new Visite();
        
        if (!visiteTable.getSelectionModel().isEmpty()) {
            V = visiteTable.getSelectionModel().getSelectedItem();
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date date = sdf1.parse(V.getD());
            java.sql.Date d = new java.sql.Date(date.getTime());
            java.sql.Time t1 = java.sql.Time.valueOf(V.getHd());
            java.sql.Time t2 = java.sql.Time.valueOf(V.getHf());
            X = new Visite(V.getId_visite(),d,t1,t2,V.getPrix());
            System.out.println(X);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("HoraireModifier.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Effectuez votre reservation");
        stage.setScene(scene);
        stage.show();
        return X;
    }
}
