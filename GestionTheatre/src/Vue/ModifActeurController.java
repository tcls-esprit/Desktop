/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.Acteur;
import Service.ActeurDB;
import static Vue.HomepageController.actsm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class ModifActeurController implements Initializable {

    @FXML
    private JFXTextField tnommodif;
    @FXML
    private JFXTextField tprenommodif;
    @FXML
    private Button modfi;
    @FXML
    private JFXButton fermer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 
    }    

    @FXML
    private void ModActeur(ActionEvent event) {
            ActeurDB a = new ActeurDB();
            
            Acteur modif = actsm;
            int identite = modif.getId();
            System.out.println(identite);
            modif.setNom(tnommodif.getText());
            modif.setPrenom(tprenommodif.getText());
            System.out.println(modif);  
            a.ModifierActeur(modif, identite);
           
        
                 
         
        
    }
    
    
    void savedata(Acteur row, int ax) {
       
        //  int x = row.getId();
       // tnommodif.setText(row.getNom());
       // tprenommodif.setText(row.getPrenom());

       // ActeurDB dB = new ActeurDB();
       // Acteur a = dB.getActeur(new Acteur());
      //  dB.ModifierActeur(a, x);
    }

    @FXML
    private void fermer(ActionEvent event) {
        Stage stage = (Stage) fermer.getScene().getWindow();
         stage.close();
    }
    
 

     
}