/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi.tcls.Gui;

import esprit.pidev.tcls.Entity.Guide;
import esprit.pidev.tcls.service.GuideService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Bilel
 */
public class AjoutGuideController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField prenom;
    @FXML
    private TextField description;
    @FXML
    private Button submit;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    GuideListUtilController gl = new GuideListUtilController();
           
    }    
    
    public void Ajout(){
        String nom = name.getText();
        String prename = prenom.getText();
        String desc = description.getText();
        
        Guide g = new Guide(nom, prename, desc);
        GuideService gs = new GuideService();
        gs.insert(g);
       
    }
    
}
