/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

;
import Model.CurrentUser;
import Model.Entity.Oussama.Commentaire;
import Model.Entity.Oussama.Evenement;
import Model.Entity.Oussama.Session;
import Model.TypeUser;
import Model.Service.Oussama.CommentaireService;
import Model.Service.Oussama.EvenementService;
import Model.Service.Oussama.SessionService;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class DetailEventController implements Initializable {

    @FXML
    private JFXTextField labelnom;
    @FXML
    private JFXTextField labeldescription;
    @FXML
    private JFXTextField labelduree;
    @FXML
    private JFXTextField labelorganisateur;
    @FXML
    private JFXTextField labeltype;
    @FXML
    private TableView<Session> tablesessions;
    @FXML
    private TableColumn<Session, Timestamp> columndatedebut;
    @FXML
    private TableColumn<Session, Timestamp> columndatefin;
    @FXML
    private TableColumn<Session, Timestamp> columnsalle;
    @FXML
    private TableColumn<Commentaire, String> columnowner;
    @FXML
    private TableColumn<Commentaire, String> columncontenu;
    @FXML
    private TableColumn<Commentaire, String> datecolumn;
    @FXML
    private JFXTextField labelprix;
    @FXML
    private JFXTextArea textareacommentaire;
    @FXML
    private Button buttoncommentaire;
    @FXML
    private Button btnclose;
    @FXML
    private ImageView imageview;
    @FXML
    private MenuItem contextmodif;
    @FXML
    private MenuItem contextsupp;
    @FXML
    private TableView<Commentaire> tablecomment;
     int idevent;
     
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Pane sessionpane;
    @FXML
    private MenuItem contextsignale;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    } 
    public void detail (Evenement e)
    {
        String i;
        idevent=e.getId();
        
        Image image = new Image("file:C:\\wamp64\\www\\images\\"+e.getImage());
        imageview.setImage(image);
        labelnom.setText(e.getTitre());
        labeldescription.setText(e.getDescription());
        i= String.valueOf(e.getDuree());        
        labelduree.setText(i);
        EvenementService es = new EvenementService();
        
        labelorganisateur.setText(es.getNomPrenomUser(e.getId_user()));
        labeltype.setText(e.getType_event());
        i= String.valueOf(e.getPrix());
        labelprix.setText(i);
        afficher();
        detailSession();
        if ((CurrentUser.type==TypeUser.Admin)||(CurrentUser.id==e.getId_user())) 
        {
            if (e.getEtat()==1)
            {
                sessionpane.setVisible(true);
            }    
        }
    }
    public void detailSession()
    {
        SessionService ss  = new SessionService();
        tablesessions.setItems(ss.getSelonEvent(idevent));
        columndatedebut.setCellValueFactory(new PropertyValueFactory<>("Date_deb"));
        columndatefin.setCellValueFactory(new PropertyValueFactory<>("Date_fin"));
        //columnsalle.setCellValueFactory(new PropertyValueFactory<>("Salle"));
    }

    void afficher()
    {
        CommentaireService cs = new CommentaireService();
        System.out.println(idevent);
        tablecomment.setItems(cs.DisplaycomEvent(idevent)); 
        columnowner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        columncontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
    }
    @FXML
    void modifierCommentaire(ActionEvent e)
    {
        Commentaire c = tablecomment.getSelectionModel().getSelectedItem();
        if ((CurrentUser.type==TypeUser.Admin)||(CurrentUser.id==c.getId_user()))
        textareacommentaire.setText(tablecomment.getSelectionModel().getSelectedItem().getContenu());  
        else{alert();}
    }
    @FXML
    void ajouterOuModifCommentaire(ActionEvent e) throws SQLException
    {
        if (textareacommentaire.getText().isEmpty()) 
            {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("info");
            alert.setHeaderText("Champs vide");
            //alert.setContentText("Votre ");
            alert.show();
            }
        
        else if (!tablecomment.getSelectionModel().isEmpty())
        {   Commentaire c = tablecomment.getSelectionModel().getSelectedItem();
            if ((CurrentUser.type==TypeUser.Admin)||(CurrentUser.id==c.getId_user()))
            {
                CommentaireService cs = new CommentaireService();
                c.setContenu(textareacommentaire.getText());
                System.out.println(c.getContenu());
                cs.update(c, c.getId());
            }
            else{alert();}
        }
        else
        {
            Commentaire c = new Commentaire(textareacommentaire.getText(), CurrentUser.id, idevent,CurrentUser.username);
            CommentaireService cs = new CommentaireService();
            cs.AjouterComm(c);
        }
        afficher();
        textareacommentaire.clear();
    }
    @FXML
    void supprimerCommentaire(ActionEvent e) throws SQLException
    {
        Commentaire c = tablecomment.getSelectionModel().getSelectedItem();
        if ((CurrentUser.type==TypeUser.Admin)||(CurrentUser.id==c.getId_user()))
        {
            CommentaireService cs = new CommentaireService();
            cs.supprimer(c.getId());
            afficher();
        }
        else{alert();}
        
    }
    
    @FXML
private void closeButtonAction(){
    // get a handle to the stage
    Stage stage = (Stage) btnclose.getScene().getWindow();
    // do what you have to do
    stage.close();
}
void alert ()
{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("info");
    alert.setHeaderText("Vous n'avez pas le droit ! ");
    alert.show();
}

    @FXML
    private void signaleCommentaire(ActionEvent event) 
    {
        CommentaireService cs = new CommentaireService();
        cs.updateStatus("non signalé", tablecomment.getSelectionModel().getSelectedItem().getId());
        afficher();
        
    }

    @FXML
    private void ajouterSession(ActionEvent event) 
    {
        Session s = new Session(6,new Timestamp(12,6,1996,21,03,11,0), new Timestamp(12,6,1996,22,03,11,0), idevent);
        SessionService ss=new SessionService();
        
        ss.insert(s);
        detailSession();
    }

    @FXML
    private void supprimerSession(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'une session d'evenement");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer cette session ?  ");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
                SessionService ss = new SessionService();
                ss.delete(tablesessions.getSelectionModel().getSelectedItem().getID_session());
            }
            detailSession();
    }

    @FXML
    private void modifierSession(ActionEvent event) {
    }
    
}
