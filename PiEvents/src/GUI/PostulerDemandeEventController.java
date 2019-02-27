/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AjouterEventAdminController.getImageUrl;
import com.esprit.entites.Evenement;
import com.esprit.services.EvenementService;
import com.esprit.services.UserService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class PostulerDemandeEventController implements Initializable {

    @FXML
    private JFXTextField labelnom;
    @FXML
    private JFXTextField labeldescription;
    @FXML
    private JFXTextField labelduree;
    @FXML
    private JFXComboBox<String> boxtypeevent;
    @FXML
    private JFXTextField labelprix;
    @FXML
    private Button btninsert;
    @FXML
    private Button buttonconfirmer;
    @FXML
    private Button btnclose;
    String nomimage="";
    static String getImageUrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> data = FXCollections.observableArrayList("seminaire","réunion","conférence");
        boxtypeevent.setItems(data);
    }    

   

    @FXML
    private void accepter(ActionEvent event) 
    {
        int duree=Integer.valueOf(labelduree.getText());
        float prix = Float.valueOf(labelprix.getText());
        if (!nomimage.equals(" "))
            {
            Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,UserService.LoggedUser.getUserId(),0,nomimage,boxtypeevent.getValue());
            EvenementService es = new EvenementService();
            es.insert(ev);
            alert();
            }
            else
            {
            Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,UserService.LoggedUser.getUserId(),0,boxtypeevent.getValue());
            EvenementService es = new EvenementService();
            es.insert(ev);
            alert();
            }
    }
    public void alert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout d'un événement");
        alert.setHeaderText("événement a été ajouté avec succès   ");
        Optional<ButtonType> result = alert.showAndWait();
    }
     @FXML
    private void insert_image(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            getImageUrl = selectedfile.getAbsolutePath();
            System.out.println("s " + selectedfile);
            File file = new File(getImageUrl);
            Image ima = new Image(file.toURI().toString());
            System.out.println(getImageUrl);
            int fileNameIndex = getImageUrl.lastIndexOf("\\") + 1;

            nomimage = getImageUrl.substring(fileNameIndex);
            File dest = new File("C:\\wamp64\\www\\images\\" + nomimage);
            System.out.println("hello" + nomimage);
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
    private void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnclose.getScene().getWindow();
    
        stage.close();
    }
    
}
