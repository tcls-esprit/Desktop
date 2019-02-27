/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AjouterEventAdminController.getImageUrl;
import Model.CurrentUser;
import Model.Entity.Oussama.Concert;
import Model.Entity.Oussama.Evenement;
import Model.Entity.Oussama.Exposition;
import Model.TypeUser;
import Model.Service.Oussama.ConcertService;
import Model.Service.Oussama.EvenementService;
import Model.Service.Oussama.ExpositionService;
import Model.Service.UserService;
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
public class ModifierEventController implements Initializable {

    @FXML
    private JFXTextField labelnom;
    @FXML
    private JFXTextField labeldescription;
    @FXML
    private JFXTextField labelduree;
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
    private JFXComboBox<Integer> combotetat;
    @FXML
    private JFXTextField labeltype;
    @FXML
    private JFXTextField labelid;
    @FXML
    private Button btnclose;
    String nomimage="";
    int etat;
    int iduser;
    @FXML
    private Button btnimage;
    @FXML
    private Pane paneetat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Integer> etat = FXCollections.observableArrayList(1,0);
        combotetat.setItems(etat);
        if (CurrentUser.type==TypeUser.Admin)
            paneetat.setVisible(true);
        
        labeltype.textProperty().addListener(new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> composant, String oldValue, String newValue) 
        {

            if(labeltype.getText().equals("exposition"))
            {
                paneexposition.setVisible(true);
                paneconcert.setVisible(false);
            }
            else if (labeltype.getText().equals("concert"))
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
        
        if (labeltype.getText().equals("exposition"))
        {
            
            int nbrayon =Integer.valueOf(labelnbrrayon.getText());
            if (!nomimage.equals(" "))
            {Exposition ee = new Exposition(nbrayon,labelnom.getText(),prix,labeldescription.getText(),duree,0,combotetat.getValue(),nomimage,"exposition");
            ExpositionService es = new ExpositionService();
            es.update(ee,Integer.valueOf(labelid.getText()));
            }
            else
            {
            Exposition ee = new Exposition(nbrayon,labelnom.getText(),prix,labeldescription.getText(),duree,0,combotetat.getValue(),"exposition");
            ExpositionService es = new ExpositionService();
            es.update(ee,Integer.valueOf(labelid.getText()));
            }
        }
        else if (labeltype.getText().equals("concert"))
        {
            if (!nomimage.equals(" "))
            {
            Concert c = new Concert(textconcert.getText(),labelnom.getText(),prix,labeldescription.getText(),duree,0,combotetat.getValue(),nomimage,"concert");
            Concert c1 = artistes(c);
            ConcertService cs= new ConcertService();
            cs.update(c1,Integer.valueOf(labelid.getText()));
            alert();
            }
            else
            {
            Concert c = new Concert(textconcert.getText(),labelnom.getText(),prix,labeldescription.getText(),duree,0,combotetat.getValue(),"concert");
            Concert c1 = artistes(c);
            ConcertService cs= new ConcertService();
            cs.update(c1,Integer.valueOf(labelid.getText()));
            alert();
            }
        }
        else
        {
            if (!nomimage.equals(" "))
            {
                if(CurrentUser.type==TypeUser.SimpleUser)
                {
                Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,iduser,etat,nomimage,labeltype.getText());
                EvenementService es = new EvenementService();
                es.update(ev,Integer.valueOf(labelid.getText()));
                alert();
                }
                else if(CurrentUser.type==TypeUser.Admin)
                {
                Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,iduser,combotetat.getValue(),nomimage,labeltype.getText());
                EvenementService es = new EvenementService();
                es.update(ev,Integer.valueOf(labelid.getText()));
                sendMail();
                alert();
                
                }
            }
            else 
            {
                if(CurrentUser.type==TypeUser.SimpleUser)
                {
                Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,iduser,etat,labeltype.getText());
                EvenementService es = new EvenementService();
                es.update(ev,Integer.valueOf(labelid.getText()));
                alert();
                }
                if(CurrentUser.type==TypeUser.Admin)
                {
                Evenement ev = new Evenement(labelnom.getText(),prix,labeldescription.getText(),duree,iduser,combotetat.getValue(),labeltype.getText());
                EvenementService es = new EvenementService();
                es.update(ev,Integer.valueOf(labelid.getText()));
                sendMail();
                alert();
                }
            }
        }
        
        
        
            
    }
    public void alert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification d'un événement");
        alert.setHeaderText("événement a été modifié avec succès  ");
        Optional<ButtonType> result = alert.showAndWait();
    }
    public void getId(Evenement e)
    {
        labelid.setText(String.valueOf(e.getId()));
    }
    public void gettype(Evenement e)
    {
        labeltype.setText(e.getType_event());
    }
    public void detail (Evenement e)
    {
        String i;
        etat=e.getEtat();
        iduser=e.getId_user();
        labelnom.setText(e.getTitre());
        labeldescription.setText(e.getDescription());
        i= String.valueOf(e.getDuree());        
        labelduree.setText(i);
        EvenementService es = new EvenementService();
        labeltype.setText(e.getType_event());
        i= String.valueOf(e.getPrix());
        labelprix.setText(i);
        
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
            System.out.println("hello " + nomimage);
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
    @FXML
private void closeButtonAction(ActionEvent e){
    
    Stage stage = (Stage) btnclose.getScene().getWindow();
    
    stage.close();
}
private void sendMail()
{
    if (combotetat.getValue()!=etat)
        {
            UserService us =new UserService();
            SendMail.sendmail(us.getUserMail(iduser),
                     "Etat de votre événement", " Etat de votre événement "+labelnom.getText()+ " a été modifier par l'administrateur ");
        }
}
}  

