/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Model.CurrentUser;
import Model.Entity.Oussama.Evenement;
import Model.Service.Oussama.EvenementService;
import Model.UserService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class AfficheEventUserController implements Initializable {

    @FXML
    private TableView<Evenement> tableevenements;
    @FXML
    private TableColumn<Evenement, String> titrecolumn;
    @FXML
    private TableColumn<Evenement, String> descriptioncolumn;
    @FXML
    private TableColumn<Evenement, String> typecolumn;
    @FXML
    private Button buttondetail;
    @FXML
    private JFXTextField txtchercher;
    @FXML
    private Menu menuprofil;
    @FXML
    private Menu menulogout;
    @FXML
    private Tab tabevent;
    @FXML
    private Button buttonacceeuil;
    @FXML
    private Button buttonajouter;
    @FXML
    private Tab tabmesévent;
    @FXML
    private Button buttonacceeuil1;
    @FXML
    private Button buttondetail1;
    @FXML
    private Button buttonsupprimer;
    @FXML
    private Button buttonmodifier;
    @FXML
    private TableView<Evenement> tableevenements1;
    @FXML
    private MenuItem menuajout;
    @FXML
    private MenuItem menumodifier;
    @FXML
    private MenuItem menusupprimer;
    @FXML
    private TableColumn<Evenement, String> titrecolumn1;
    @FXML
    private TableColumn<Evenement, String> descriptioncolumn1;
    @FXML
    private TableColumn<Evenement, String> typecolumn1;
    @FXML
    private TableColumn<Evenement, Integer> columnetat;
    @FXML
    private JFXTextField txtchercher1;
    @FXML
    private Button btnactualiser1;
    @FXML
    private Button btnactualiser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        afficherMesEvents();
        txtchercher.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterEventList((String) oldValue, (String) newValue);
            }
        });
        txtchercher1.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                filterMesEventList((String) oldValue, (String) newValue);
            }
        });
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
                        System.out.print("testt");
                        DetailEventExpositionsController deec= (DetailEventExpositionsController) loader.getController();
                        deec.detail(e);
                        
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Detail de l'événement");
                        stage.setScene(new Scene(root));
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
                        stage.setScene(new Scene(root));
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
                    stage.setTitle("Detail de l'événement");
                    stage.setScene(new Scene(root));
                    stage.show();
                    }  
                    catch (IOException ex) {
                    Logger.getLogger(AfficheEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
        }
    }
    
     void loadWindow(String loc, String title)
    {
        
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficheEventAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    void afficher() {
    EvenementService es = new EvenementService();
    tableevenements.setItems(es.DisplayAllEtat1());
    titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
    descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    typecolumn.setCellValueFactory(new PropertyValueFactory<>("type_event"));
    }
    void filterEventList(String oldValue, String newValue) {
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
    void filterMesEventList(String oldValue, String newValue) {
        EvenementService es = new EvenementService();
        ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
        if (txtchercher1.getText() == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableevenements1.setItems(es.DisplayUserEvents(CurrentUser.id));

        } else {

            newValue = newValue.toUpperCase();

            for (Evenement e : tableevenements1.getItems()) {

                String filtertitre= e.getTitre();

                if (filtertitre.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }

            }
            tableevenements1.setItems(filteredList);

        }
    }

    @FXML
    void ajouterEvent (ActionEvent event)
    {
        loadWindow("PostulerDemandeEvent.fxml", "Ajouter Evenement");
    }

    @FXML
    private void supprimer(ActionEvent event) 
    {
        if (!tableevenements1.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d evenement");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer evenement:  "
                    + tableevenements1.getSelectionModel().getSelectedItem().getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService es = new EvenementService();
                es.delete(tableevenements1.getSelectionModel().getSelectedItem().getId());
                UserService us = new UserService();
                afficherMesEvents();
            }
            
        }
    }

    @FXML
    private void modifierEvent(ActionEvent event) throws IOException {
        Evenement e = tableevenements1.getSelectionModel().getSelectedItem();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("ModifierEventAdmin.fxml"));
        Parent root = loader.load();
        ModifierEventController meac= (ModifierEventController) loader.getController();
        meac.getId(e);
        meac.gettype(e);
        meac.detail(e);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Modifier événement");
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    @FXML
    private void AjouterSession(ActionEvent event) {
    }

    @FXML
    private void modifierSession(ActionEvent event) {
    }
    
    private void afficherMesEvents()
    {
        EvenementService es = new EvenementService();
        tableevenements1.setItems(es.DisplayUserEvents(CurrentUser.id));
        titrecolumn1.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptioncolumn1.setCellValueFactory(new PropertyValueFactory<>("description"));
        typecolumn1.setCellValueFactory(new PropertyValueFactory<>("type_event"));
        columnetat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    @FXML
    private void Actualiser(ActionEvent event) {
        afficher();
    }

    @FXML
    private void Actualiser1(ActionEvent event) {
        afficherMesEvents();
    }
    private void logout(ActionEvent event) {
        loadWindow("Login.fxml", "Login");
    }
}
