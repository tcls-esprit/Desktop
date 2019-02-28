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
import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class AjouterSessionController implements Initializable {

    @FXML
    private ComboBox<Theatre> comboScene;
     
    private ArrayList<Theatre> the;
    private ObservableList<Theatre> theop;
    @FXML
    private TextField prix;
    @FXML
    private Button validertest;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField salle;
    @FXML
    private JFXButton close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ChargerDonnées();
    }    
    public void ChargerDonnées()
    {
     
            TheatreDB  thdb = new TheatreDB();
            the = (ArrayList<Theatre>) thdb.AllScene();
            theop = FXCollections.observableArrayList(the);
            comboScene.setItems(theop); 
    
    }

    @FXML
    private void validertest(ActionEvent event) {
        SessionTheatreBD test = new SessionTheatreBD();
        LocalDate dateDebutvalue = datedebut.getValue();
        System.out.println(dateDebutvalue.toString());
        LocalDate dateFinvalue = datefin.getValue();
        System.out.println(dateDebutvalue.toString());
        int x = Integer.parseInt(salle.getText());
        System.out.println(x);
        float rs = Float.parseFloat(prix.getText());
        System.out.println(rs);
        Theatre ax = comboScene.getSelectionModel().getSelectedItem();
        int idscene = ax.getCode_scene();
        System.out.println(ax);
        Date datedeb = java.sql.Date.valueOf(dateDebutvalue);
        Date dat = java.sql.Date.valueOf(dateFinvalue);
       SessionTheatre ses = new SessionTheatre(idscene,x, datedeb, dat ,rs);
        //System.out.println(ses);
        test.AjouterSessionTheatre(ses,idscene);
        
        

        
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
         stage.close();
    }
    

}
