/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Model.Entity.Oussama.Concert;
import Model.Entity.Oussama.Evenement;
import Model.Entity.Oussama.Exposition;
import Model.Service.Oussama.ConcertService;
import Model.Service.Oussama.EvenementService;
import Model.Service.Oussama.ExpositionService;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class AjouterEventAdminController implements Initializable {

    @FXML
    private JFXTextField labelnom;
    @FXML
    private JFXTextField labeldescription;
    @FXML
    private JFXTextField labelduree;
    @FXML
    private JFXComboBox<String> boxtypeevent;
    @FXML
    private Pane paneexposition;
    @FXML
    private JFXTextField labelnbrrayon;
    @FXML
    private JFXTextField labelprix;
    @FXML
    private Pane paneconcert;
    @FXML
    private JFXTextField textconcert;
    @FXML
    private JFXTextField textartiste;
    @FXML
    private Button buttonconfirmer;
    @FXML
    private Button btnclose;
    @FXML
    private Button btninsert;
    String nomimage="";
    static String getImageUrl;

    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<String> data = FXCollections.observableArrayList("seminaire", "concert", "réunion","exposition","conférence");
        boxtypeevent.setItems(data);
        boxtypeevent.valueProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> composant, String oldValue, String newValue) 
        {

            if(boxtypeevent.getValue().equals("exposition"))
            {
                paneexposition.setVisible(true);
                paneconcert.setVisible(false);
            }
            else if (boxtypeevent.getValue().equals("concert"))
            {
                paneconcert.setVisible(true);
                paneexposition.setVisible(false);
            }
            else
            {
                paneconcert.setVisible(false);
                paneexposition.setVisible(false);
            }
        }
    });        
    }
    @FXML
    void accepter (ActionEvent e)
    {
        
        
        int duree=Integer.valueOf(labelduree.getText());
        float prix = Float.valueOf(labelprix.getText());
        System.out.print( duree + prix);
        
        if (boxtypeevent.getValue().equals("exposition"))
        {
            int nbrayon =Integer.valueOf(labelnbrrayon.getText());
            if (!nomimage.equals(" "))
            {
            Exposition ee = new Exposition(nbrayon,labelnom.getText(),prix,labeldescription.getText(),duree,0,1,nomimage,"exposition");
            ExpositionService es = new ExpositionService();
            es.insert(ee);
            }
            else 
            {
            Exposition ee = new Exposition(nbrayon,labelnom.getText(),prix,labeldescription.getText(),duree,0,1,"exposition");
            ExpositionService es = new ExpositionService();
            es.insert(ee);
            }
        }
        else if (boxtypeevent.getValue().equals("concert"))
        {
            if (!nomimage.equals(" "))
            {    
            Concert c = new Concert(textconcert.getText(),labelnom.getText(),prix,labeldescription.getText(),duree,0,1,nomimage,"concert");
            Concert c1 = artistes(c);
            
            ConcertService cs= new ConcertService();
            cs.insert(c1);
            alert();
            }
            else
            {
            Concert c = new Concert(textconcert.getText(),labelnom.getText(),prix,labeldescription.getText(),duree,0,1,"concert");
            Concert c1 = artistes(c);
            
            ConcertService cs= new ConcertService();
            cs.insert(c1);
            alert();
            }
        }
        else
        {
            if (!nomimage.equals(" "))
            {
            Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,0,1,nomimage,boxtypeevent.getValue());
            EvenementService es = new EvenementService();
            es.insert(ev);
            alert();
            }
            else
            {
            Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,0,1,boxtypeevent.getValue());
            EvenementService es = new EvenementService();
            es.insert(ev);
            alert();
            }
        }
        /*SendMail.sendmail("starjet1900@gmail.com",
                     "Ajout d'événement", " l'événement "+labelnom.getText()+ "a été ajouté avec succés");*/
        
            
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
private void closeButtonAction(ActionEvent e){
    
    Stage stage = (Stage) btnclose.getScene().getWindow();
    
    stage.close();
}
private Concert artistes (Concert c)
{String artistes = textartiste.getText();
 Character c1;
 String artiste="";
    for (int i=0 ; i<artistes.length();i++)
                {
                    c1=artistes.charAt(i);
                    if(!c1.equals(' '))
                    {artiste=artiste+c1;
                    
                    }
                    else
                    {
                        c.ajouter_artiste(artiste);
                        System.out.print(artiste);
                        artiste=" ";
                    }
                }
                c.ajouter_artiste(artiste);
                return c;
}
  
}  
