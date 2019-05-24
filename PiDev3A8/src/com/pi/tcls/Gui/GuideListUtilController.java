/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import com.jfoenix.controls.JFXTextField;
import com.pi.connectionBD.ConnectionBD;
import esprit.pidev.tcls.Entity.Guide;
import esprit.pidev.tcls.Entity.Visite;
import esprit.pidev.tcls.service.GuideService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class GuideListUtilController implements Initializable {
    private ConnectionBD con;
    
    
    @FXML
    private TableView<Guide> guideTable;
    @FXML
    private TableColumn<Guide,String> nom;
    @FXML
    private TableColumn<Guide,String> prenom;
    @FXML
    private TableColumn<Guide,String> description;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    @FXML
    private JFXTextField rechercher;
    

    /**
     * Initializes the controller class.
     */
    
    public GuideListUtilController(){
        con=ConnectionBD.getInstance();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con=ConnectionBD.getInstance();
    
        LoadData();
    }    
    
    public void LoadData(){
    GuideService gs = new GuideService();
    guideTable.setItems(gs.getAll());
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    }
    
     @FXML
    private void searchTable() {
        String s = rechercher.getText();
        ObservableList<Guide> data = null;
        try {
            data = FXCollections.observableArrayList(new GuideService().filtrerGuide(s));
        } catch (SQLException e) {
            e.printStackTrace();
        }
       // initColumns();
       guideTable.setItems(data);
    }
    

    
}
