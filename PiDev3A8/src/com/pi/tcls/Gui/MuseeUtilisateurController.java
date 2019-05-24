/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class MuseeUtilisateurController implements Initializable {

    @FXML
    private Button Reserver;
    @FXML
    private JFXButton Retour;
    @FXML
     AnchorPane Pane;
    @FXML
    private ImageView aa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    @FXML
    public void Resever(ActionEvent evt){
       
        try {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("HoraireVisites.fxml"));
        Pane.getChildren().setAll(ap);
 
        } catch (IOException ex) {
            Logger.getLogger(MuseeUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    @FXML
    public void Consulter(ActionEvent evt){
            try {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("GuideListUtil.fxml"));
        Pane.getChildren().setAll(ap);
        
 
        } catch (IOException ex) {
            Logger.getLogger(MuseeUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    public void Retour(ActionEvent evt){
          try {
        AnchorPane ap = FXMLLoader.load(getClass().getResource("MuseeUtilisateur.fxml"));
        Pane.getChildren().setAll(ap);
        
 
        } catch (IOException ex) {
            Logger.getLogger(MuseeUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
