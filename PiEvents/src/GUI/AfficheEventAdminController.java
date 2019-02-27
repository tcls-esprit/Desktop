/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.esprit.entites.Commentaire;
import com.esprit.entites.Evenement;
import com.esprit.services.CommentaireService;
import com.esprit.services.EvenementService;
import com.esprit.services.UserService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class AfficheEventAdminController implements Initializable {

    @FXML
     TableView<Evenement> tableevenements;
    @FXML
     TableColumn<Evenement, String> titrecolumn;
    @FXML
     TableColumn<Evenement, String> descriptioncolumn;
    @FXML
     TableColumn<Evenement, String> typecolumn;
    @FXML
    private TableColumn<Evenement, Integer> columnetat;
    @FXML
    private Button buttondetail;
    @FXML
    private Button buttonsupprimer;
    @FXML
    private Button buttonmodifier;
    @FXML
    private JFXTextField txtchercher;
    
    private ObservableList<Evenement> data;
    int idnew;
    
    @FXML
    private Button buttonajouter;
    @FXML
    private Menu btnmenuprofil;
    @FXML
    private Menu btnmenulogout;
    @FXML
    private Button buttonacceeuil;
    @FXML
    private Button btnactualiser;
    @FXML
    private PieChart statpie;
    @FXML
    private BarChart<String, Integer> statbar;
    @FXML
    private TableView<Commentaire> tablecomment;
    @FXML
    private MenuItem contextsupp;
    @FXML
    private TableColumn<Commentaire, String> columnowner;
    @FXML
    private TableColumn<Commentaire, String> columncontenu;
    @FXML
    private TableColumn<Commentaire, String> datecolumn;
    @FXML
    private MenuItem contextstatus;
    @FXML
    private TableColumn<?, ?> columnstatus;
    @FXML
    private MenuItem contextactualise;
    @FXML
    private MenuItem contextlogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        afficher();
        afficherComms();
                
        
        EvenementService es = new EvenementService();
        TextFields.bindAutoCompletion(txtchercher, es.liste_nom_event());
        txtchercher.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filtereventList((String) oldValue, (String) newValue);
            }
        });
        ArrayList <String> type= new ArrayList<>();
        type.add("seminaire");
        type.add("concert");
        type.add("exposition");
        type.add("réunion");
        type.add("conférence");
        ObservableList<String> listtype = FXCollections.observableArrayList(type);
        ObservableList<PieChart.Data> data= FXCollections.observableArrayList();
        CommentaireService cs = new CommentaireService();
        for (String s : listtype)
            {
                data.add(new PieChart.Data(s,cs.pourcentageCommentParTypeEvent(s)));
            }
        statpie.setData(data);
        statpie.setTitle("Pourcentage nombre de Commentaires par type événement");
        statpie.setLegendSide(Side.BOTTOM);
        statpie.setLabelsVisible(true);
        statpie.setStartAngle(90);
        XYChart.Series set1 = new XYChart.Series<>() ; 
        for (String s: listtype)
        {
            set1.getData().add(new XYChart.Data<>(s,cs.nombreDeCommentaires(s) )) ;
        }
        statbar.getData().addAll(set1) ; 
    } 
    void afficher() {
    EvenementService es = new EvenementService();
    tableevenements.setItems(es.DisplayAll());
    titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
    descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    typecolumn.setCellValueFactory(new PropertyValueFactory<>("type_event"));
    columnetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }
    @FXML
    private void detailEvent (ActionEvent event) throws IOException
    {
        if (!tableevenements.getSelectionModel().isEmpty())
        {
            if (tableevenements.getSelectionModel().getSelectedItem().getType_event().equals("exposition"))
                    {
                        Evenement e = tableevenements.getSelectionModel().getSelectedItem();
                        try 
                        {
                        FXMLLoader loader =new FXMLLoader(getClass().getResource("DetailEventExpositions.fxml"));
                        Parent root = loader.load();
                        //System.out.print("testt");
                        DetailEventExpositionsController deec= (DetailEventExpositionsController) loader.getController();
                        deec.detail(e);
                        
                        Stage stage = new Stage(StageStyle.DECORATED);
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add("GUI/Style.css");
                        stage.setScene(scene);
                        stage.setTitle("Detail de l'événement");
                        
                        stage.show();
                        }  
                        catch (IOException ex)
                        {
                        Logger.getLogger(AfficheEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            else if (tableevenements.getSelectionModel().getSelectedItem().getType_event().equals("concert"))
                    {
                        Evenement e = tableevenements.getSelectionModel().getSelectedItem();
                        try 
                        {
            
                        FXMLLoader loader =new FXMLLoader(getClass().getResource("DetailEventConcert.fxml"));
                        Parent root = loader.load();
                        DetailEventConcertController decc= (DetailEventConcertController) loader.getController();
                        decc.detail(e);
                        
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Detail de l'événement");
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add("GUI/Style.css");
                        stage.setScene(scene);
                        
                        stage.show();
                        }  
                        catch (IOException ex)
                        {
                        Logger.getLogger(AfficheEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
            else
                    {
                    Evenement e = tableevenements.getSelectionModel().getSelectedItem();
                    try 
                    {
            
                    FXMLLoader loader =new FXMLLoader(getClass().getResource("DetailEvent.fxml"));
                    Parent root = loader.load();
                    DetailEventController dec= (DetailEventController) loader.getController();
                    dec.detail(e);
                    
                    Stage stage = new Stage(StageStyle.DECORATED);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("GUI/Style.css");
                    stage.setScene(scene);
                    stage.setTitle("Detail de l'événement");
                    
                    stage.show();
                    }  
                    catch (IOException ex) {
                    Logger.getLogger(AfficheEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
        }
    }
    @FXML
    private void supprimer(ActionEvent event) {
        if (!tableevenements.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d evenement");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer evenement:  "
                    + tableevenements.getSelectionModel().getSelectedItem().getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService es = new EvenementService();
                es.delete(tableevenements.getSelectionModel().getSelectedItem().getId());
                UserService us = new UserService();
                SendMail.sendmail(us.getUserMail(tableevenements.getSelectionModel().getSelectedItem().getId_user()),"Suppression d'événement", 
              " l'événement "+tableevenements.getSelectionModel().getSelectedItem().getTitre()+ "a été supprimé ");
                afficher();
            }
            
        }
    }
    @FXML
    void ajouterEvent (ActionEvent event)
    {
        loadWindow("AjouterEventAdmin.fxml", "Ajouter Evenement");
    }
    @FXML
    void modifierEvent (ActionEvent event) throws IOException
    {
        Evenement e = tableevenements.getSelectionModel().getSelectedItem();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("ModifierEventAdmin.fxml"));
        Parent root = loader.load();
        ModifierEventController meac= (ModifierEventController) loader.getController();
        meac.getId(e);
        meac.gettype(e);
        meac.detail(e);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modifier événement");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("GUI/Style.css");
        stage.setScene(scene);
        
        stage.show();
        
    }
    void modifierSession (ActionEvent event)
    {
        
    }
    void AjouterSession (ActionEvent event)
    {
        
    }
    void filtereventList(String oldValue, String newValue) {
        EvenementService es = new EvenementService();
        ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
        if (txtchercher.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableevenements.setItems(es.DisplayAll());

        } else {

            newValue = newValue.toUpperCase();

            for (Evenement e : tableevenements.getItems()) {

                String filtertitre= e.getTitre();

                if (filtertitre.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }

            }
            tableevenements.setItems(filteredList);

        }
    }
    void loadWindow(String loc, String title)
    {
        
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("GUI/Style.css");
            stage.setScene(scene);
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
   
    
   
    public String getNomPrenomUser()
    {
        EvenementService es = new EvenementService();
        return es.getNomPrenomUser(tableevenements.getSelectionModel().getSelectedItem().getId());
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        afficher();
    }


    @FXML
    private void supprimerCommentaire(ActionEvent event) throws SQLException 
    {
        CommentaireService cs = new CommentaireService();
        cs.supprimer(tablecomment.getSelectionModel().getSelectedItem().getId());
        afficherComms();
        
    }
    @FXML
    void updateStatus(ActionEvent event)
    {
        CommentaireService cs = new CommentaireService();
        cs.updateStatus(tablecomment.getSelectionModel().getSelectedItem().getStatus(), tablecomment.getSelectionModel().getSelectedItem().getId());
        afficherComms();
    }
    void afficherComms()
    {
        CommentaireService cs = new CommentaireService();
        
        tablecomment.setItems(cs.DisplayAll()); 
        columnowner.setCellValueFactory(new PropertyValueFactory<>("owner"));
        columncontenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
    }

    @FXML
    private void refresh(ActionEvent event) {
        afficherComms();
    }

   
    
    
}
