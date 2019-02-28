/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.Acteur;
import Service.ActeurDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class AjoutActeurController implements Initializable {

    @FXML
    private JFXTextField tnom;
    @FXML
    private JFXTextField tprenom;
    @FXML
    private Button ajouter;
    @FXML
    private JFXButton fermerW;
    
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddActeur(ActionEvent event) {
              ActeurDB es = new ActeurDB();
              
              
             if(tnom.getText().isEmpty() && tprenom.getText().isEmpty()){
             Alert info = new Alert(Alert.AlertType.ERROR);
             info.setHeaderText("Information");
             info.setContentText("Svp ajouter les informations");
             info.showAndWait();
             
           }
                
             else 
             {
            es.AjouterActeur(new Acteur(tnom.getText(),tprenom.getText()));
           Alert info;
            info = new Alert(Alert.AlertType.INFORMATION);
           info.setHeaderText("Information");
             info.setContentText("L'acteur a été ajouter avec succée");
             info.showAndWait();  
             }
                           
             
             
        }

    @FXML
    private void Fermer(ActionEvent event) {
         Stage stage = (Stage) fermerW.getScene().getWindow();
         stage.close();
    }

     }


    
