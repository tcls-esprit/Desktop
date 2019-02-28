/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.Acteur;
import Entity.Theatre;
import Service.ActeurDB;
import Service.TheatreDB;
import static Vue.AjoutSceneTheatraleController.Url;
import static Vue.HomepageController.thet;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class ModifierSceneTheatraleController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private ComboBox<Acteur> comboact;
    @FXML
    private Button modifbtn;
    private static String url;
    private String nomimage;
    private Image img ;
    @FXML
    private Button ValiderButton;
    @FXML
    private TextField txtmodifsc;
    @FXML
    private TextArea descModif;
    private    ArrayList<Acteur> alp ;
    private ObservableList<Acteur> obp ;
    @FXML
    private JFXButton close;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TheatreDB th = new TheatreDB();
        Charger();
       
        modifbtn.setOnAction((event) -> {
            try {
                ModifierImage();
            } catch (IOException ex) {
                Logger.getLogger(ModifierSceneTheatraleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       ValiderButton.setOnAction((event) -> {
           
           System.out.println(HomepageController.thet);
            
              
            thet.setTitre_scene(txtmodifsc.getText());
            thet.setDescription(descModif.getText());
            thet.setImage(nomimage);
             Acteur a = comboact.getSelectionModel().getSelectedItem();
             int id = a.getId();
            
             th.ModifierSceneTheatrale(HomepageController.thet,id);
            
       });
        
    }    


    /*void modfiierTheatre(Theatre t) {
         TheatreDB th = new TheatreDB();
         thet = th.getScene(t);
      //   txtmodifsc.setText(the.getTitre_scene());
                          
         img = new Image("file:C:\\xampp\\htdocs\\img\\"+t.getImage());         
         image.setImage(img);
     /*    the.setTitre_scene(txtmodifsc.toString());
         the.setDescription(descModif.toString());
         the.setImage(nomimage);
         the.setId_acteur(comboact.getSelectionModel().getSelectedIndex());*/
          
                 
 
    //}
     public void ModifierImage() throws IOException
                {
                TheatreDB the = new TheatreDB(); 
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            Url = selectedfile.getAbsolutePath();
            System.out.println("s " + selectedfile);
           
            File file = new File(Url);
            Image ima = new Image(file.toURI().toString());
            System.out.println(Url);
            int fileNameIndex = Url.lastIndexOf("\\") + 1;

             nomimage = Url.substring(fileNameIndex);
            File dest = new File("C:\\xampp\\htdocs\\img\\" + nomimage);
         
             System.out.println("hello :" + nomimage);
             img = new Image("file:C:\\xampp\\htdocs\\img\\"+nomimage);
         
              image.setImage(img);
            copyFileUsingStream(selectedfile, dest);
        } else {
            System.out.println("file does not exist");
        }
                  
                }
                private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    
                }    
                
                public void Charger()
                {
                 ActeurDB ps = new ActeurDB();
        alp =  (ArrayList<Acteur>) ps.AllActeurs();
        obp = FXCollections.observableArrayList(alp);
        comboact.setItems(obp);
                
                }

    @FXML
    private void close(ActionEvent event) {
         Stage stage = (Stage) close.getScene().getWindow();
         stage.close();
    }
}
