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
import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Statement;
import gestiontheatre.ConnectionDBTheatre;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
 
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class AjoutSceneTheatraleController implements Initializable {

    @FXML
    private TextField titre;
    @FXML
    private Button image;
    @FXML
    private TextArea des;
     
    @FXML
    private Button ajoutscene;
    
    private FileInputStream fs ;
     
    private ConnectionDBTheatre conn;
    private Statement st ;
   static String Url; 
   String nomimage="";
    @FXML
    private ComboBox<Acteur> acteurs;
     private    ArrayList<Acteur> alp ;
    private ObservableList<Acteur> obp ;
    @FXML
    private JFXButton close;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Acteur();
         
        image.setOnAction((event) -> {
            try {
                AjouterImage();
            } catch (IOException ex) {
                Logger.getLogger(AjoutSceneTheatraleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

        
    
   
                public void AjouterImage() throws IOException
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
    @FXML
    private void AjoutScene(ActionEvent event) {
        
        System.out.println(acteurs);
        int a =   acteurs.getSelectionModel().getSelectedIndex();
        Acteur c = acteurs.getSelectionModel().getSelectedItem();
        //System.out.println(c.getId());
        //System.out.println(a);
      //  System.out.println(a);
       TheatreDB the = new TheatreDB();
        if (titre.getText().isEmpty() && des.getText().isEmpty())
        { 
            Alert info = new Alert(Alert.AlertType.ERROR);
             info.setHeaderText("Information");
             info.setContentText("Svp ajouter les informations");
             info.showAndWait();   
        }
        else {
             
              the.AjouterSceneTheatrale(new Theatre(titre.getText(),nomimage,des.getText(),a),c.getId());
             Alert info = new Alert(Alert.AlertType.INFORMATION);
             info.setHeaderText("Information");
             info.setContentText("Theatre  a été ajouter avec succes");
             info.showAndWait();    
        }
        
    }
   public void Acteur()
   {
         ActeurDB ps = new ActeurDB();
         alp =  (ArrayList<Acteur>) ps.AllActeurs();
         obp = FXCollections.observableArrayList(alp);
         acteurs.setItems(obp);
   }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
         stage.close();
    }
    
    
      
    
}
