/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.Theatre;
import Service.TheatreDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class InformationDetailleController implements Initializable {

    @FXML
    private Label labeltitre;
    @FXML
    private ImageView imageBD;
    @FXML
    private Label labelnomimage;
    private Image img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    void afficherPoseter(Theatre t) {
        TheatreDB th = new TheatreDB();
        Theatre the = th.getScene(t);
        labelnomimage.setText(the.getImage());
        System.out.println(labelnomimage);
        labeltitre.setText(the.getTitre_scene());
        System.out.println(labeltitre);
        img = new Image("file:C:\\xampp\\htdocs\\img\\"+the.getImage());
         
         imageBD.setImage(img);
        
 
    }
    
}
