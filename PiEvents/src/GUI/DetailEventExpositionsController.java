/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.esprit.entites.Commentaire;
import com.esprit.entites.Evenement;
import com.esprit.entites.Exposition;
import com.esprit.entites.Session;
import com.esprit.entites.TypeUser;
import com.esprit.services.CommentaireService;
import com.esprit.services.EvenementService;
import com.esprit.services.ExpositionService;
import com.esprit.services.OeuvreService;
import com.esprit.services.SessionService;
import static com.esprit.services.UserService.LoggedUser;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class DetailEventExpositionsController implements Initializable {

    @FXML
    private Button buttonlisteoeuvre;
    @FXML
    private JFXTextField labelnom;
    @FXML
    private JFXTextField labeldescription;
    @FXML
    private JFXTextField labelduree;
    private JFXTextField labelorganisateur;
    @FXML
    private JFXTextField labeltype;
    @FXML
    private JFXTextField labelprix;
    @FXML
    private JFXTextField labelnombrerayon;
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
    private JFXTextField labelid;
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
        // TODO
    }    
    public void detail (Evenement e)
    {
        idevent=e.getId();
        Image image = new Image("file:C:\\wamp64\\www\\images\\"+e.getImage());
        imageview.setImage(image);
        String i;
        labelid.setText(String.valueOf(e.getId()));
        labelnom.setText(e.getTitre());
        labeldescription.setText(e.getDescription());
        i= String.valueOf(e.getDuree());        
        labelduree.setText(i);
        EvenementService es = new EvenementService();
 
        labeltype.setText(e.getType_event());
        i= String.valueOf(e.getPrix());
        labelprix.setText(i);
        ExpositionService ess = new ExpositionService();
        ArrayList <Exposition> list = new ArrayList<>();
        list=ess.getAll();
        for(Exposition ee : list)
        {
            if (ee.getId()==e.getId())
            {
                labelnombrerayon.setText(String.valueOf(ee.getNombre_rayon()));
            }
        }
        afficher();
        detailSession();
        if ((LoggedUser.getType()==TypeUser.Admin)||(LoggedUser.getUserId()==e.getId_user())) 
        {
            if (e.getEtat()==1)
            {
                sessionpane.setVisible(true);
            }    
        }
    }
   @FXML
   public void afficheOeuvre(ActionEvent e) throws IOException
   {
       FXMLLoader loader =new FXMLLoader(getClass().getResource("AfficheOeuvreEvent.fxml"));
       Parent root = loader.load();
       AfficheOeuvreEventController aoec = (AfficheOeuvreEventController) loader.getController();
       int i = Integer.valueOf(labelid.getText());
       System.out.print(i);
       aoec.afficher(i);
       Stage stage = new Stage(StageStyle.DECORATED);
       stage.setTitle("listes des oeuvres");
       Scene scene = new Scene(root);
       scene.getStylesheets().add("GUI/Style.css");
       stage.setScene(scene);
       
       stage.show();
   }
    public void detailSession()
    {
        SessionService ss  = new SessionService();
        tablesessions.setItems(ss.getSelonEvent(idevent));
        columndatedebut.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        columndatefin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
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
        if ((LoggedUser.getType()==TypeUser.Admin)||(LoggedUser.getUserId()==c.getId_user()))
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
            
            alert.show();
            }
        
        else if (!tablecomment.getSelectionModel().isEmpty())
        {   Commentaire c = tablecomment.getSelectionModel().getSelectedItem();
            if ((LoggedUser.getType()==TypeUser.Admin)||(LoggedUser.getUserId()==c.getId_user()))
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
            Commentaire c = new Commentaire(textareacommentaire.getText(), LoggedUser.getUserId(), idevent, LoggedUser.getUsername());
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
        if ((LoggedUser.getType()==TypeUser.Admin)||(LoggedUser.getUserId()==c.getId_user()))
        {
            CommentaireService cs = new CommentaireService();
            cs.supprimer(c.getId());
            afficher();
        }
        else{alert();}
        
    }
    @FXML
private void closeButtonAction(ActionEvent e){
    Stage stage = (Stage) btnclose.getScene().getWindow();
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
    private void ajouterSession(ActionEvent event) {
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

    @FXML
    private void signaleCommentaire(ActionEvent event) 
    {
        CommentaireService cs = new CommentaireService();
        cs.updateStatus("non signalé", tablecomment.getSelectionModel().getSelectedItem().getId());
        afficher();
        
    }
}
