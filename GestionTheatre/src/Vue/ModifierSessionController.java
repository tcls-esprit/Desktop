/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.SessionTheatre;
import Entity.Theatre;
import Service.SessionTheatreBD;
import Service.TheatreDB;
import static Vue.HomepageController.sest;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class ModifierSessionController implements Initializable {

    @FXML
    private DatePicker dateDmod;
    @FXML
    private DatePicker dateFmod;
    @FXML
    private TextField prixmod;
    @FXML
    private Button ValMod;
    @FXML
    private ComboBox<Theatre> combMod;
    @FXML
    private TextField sallemod;
    private ArrayList<Theatre> the;
    private ObservableList<Theatre> theop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
            TheatreDB  thdb = new TheatreDB();
            the = (ArrayList<Theatre>) thdb.AllScene();
            theop = FXCollections.observableArrayList(the);
            combMod.setItems(theop); 
    }    

    @FXML
    private void ModifierAction(ActionEvent event) {
        SessionTheatreBD test = new SessionTheatreBD();
        LocalDate dateDebutvalue = dateDmod.getValue();
        System.out.println(dateDebutvalue.toString());
        LocalDate dateFinvalue = dateFmod.getValue();
        System.out.println(dateDebutvalue.toString());
        int x = Integer.parseInt(sallemod.getText());
        System.out.println(x);
        float rs = Float.parseFloat(prixmod.getText());
        System.out.println(rs);
        Theatre thet = combMod.getSelectionModel().getSelectedItem();
        int ax = thet.getCode_scene();
        System.out.println(ax);
        Date datedeb = java.sql.Date.valueOf(dateDebutvalue);
        Date dat = java.sql.Date.valueOf(dateFinvalue);
        System.out.println(HomepageController.sest);
        //SessionTheatre  ss = sest;
       
        sest.setId_salle(x);
        sest.setDate_debut(datedeb);
        sest.setDate_fin(dat);
        sest.setPrix(rs);
        sest.setId_scene(ax);
        System.out.println(sest);
       test.ModifierSessionTheatre(sest,ax);
       
    }
    
}
