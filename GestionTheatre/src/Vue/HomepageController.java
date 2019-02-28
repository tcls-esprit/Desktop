/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.Acteur;
import Entity.SessionTheatre;
import Entity.Theatre;
import Service.ActeurDB;
import Service.SessionTheatreBD;
import Service.TheatreDB;
 
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
 
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
 
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
 
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
 
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
 

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class HomepageController implements Initializable {

    @FXML
    private Tab tabacteur;
    @FXML
    private TableView<Acteur> tactview;
    @FXML
    private Button btn_acteur;
    @FXML
    private TableColumn<Acteur, String> tnomact;
    @FXML
    private TableColumn<Acteur, String> tprenomact;
    @FXML
    private TextField tfrech;
    
     
    private    ArrayList<Acteur> alp ;
    private ObservableList<Acteur> obp ;
    private ArrayList<Theatre> the;
    private ObservableList<Theatre> theop;
     private ArrayList<SessionTheatre> sess;
    private ObservableList<SessionTheatre> session;
    @FXML
    private Button theatre;
    @FXML
    private TableView<Theatre> tabthe;
    @FXML
    private TableColumn<Theatre, String> tnomth;
    @FXML
    private TableColumn<Theatre, String> imagecol;
    @FXML
    private TableColumn<Theatre,String> descol;
    @FXML
    private Button AjoutSess;
    @FXML
    private TableColumn<Theatre, Integer> colact;
    public static Theatre thet;
    public static Acteur actsm;
    public static SessionTheatre sest;
    @FXML
    private TableColumn<SessionTheatre, String> titrescene;
    @FXML
    private TableColumn<SessionTheatre, String> lasalle;
    @FXML
    private TableColumn<SessionTheatre, String> hreDeb;
    @FXML
    private TableColumn<SessionTheatre, String> hrefin;
    @FXML
    private TableColumn<SessionTheatre, String> Prix;
    @FXML
    private TableView<SessionTheatre> listSession;
    @FXML
    private TextField RechercheTheatre;
    @FXML
    private TextField Recherchesession;
    @FXML
    private MenuItem sessdel;
    @FXML
    private MenuItem sessUpd;
    @FXML
    private MenuItem sessref;
    @FXML
    private Button Quitter;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Init();
        DonnéesActeur();
        LesTheatres();
        LesSession();
      
        // addButtonModifier();
        // addButtonSupprimer();
    }    
    
    public void Init()
    {
     tnomact.setCellValueFactory(new PropertyValueFactory<>("nom"));
     tprenomact.setCellValueFactory(new PropertyValueFactory<>("prenom"));
     tnomth.setCellValueFactory(new PropertyValueFactory<>("titre_scene"));
     imagecol.setCellValueFactory(new PropertyValueFactory<>("image"));
     descol.setCellValueFactory(new PropertyValueFactory<>("description"));
     colact.setCellValueFactory(new PropertyValueFactory<>("id_acteur"));
     titrescene.setCellValueFactory(new PropertyValueFactory("id_scene"));
     lasalle.setCellValueFactory(new PropertyValueFactory("id_salle"));
     hreDeb.setCellValueFactory(new PropertyValueFactory("date_debut"));
     hrefin.setCellValueFactory(new PropertyValueFactory("date_fin"));
     Prix.setCellValueFactory(new PropertyValueFactory("prix"));
    }
    
    public void DonnéesActeur()
    {
          
         ActeurDB ps = new ActeurDB();
          
         alp =  (ArrayList<Acteur>) ps.AllActeurs();
         obp = FXCollections.observableArrayList(alp);
         tactview.setItems(obp);
    }
    
      
    public void LesTheatres()
    {
            TheatreDB  thdb = new TheatreDB();
            the = (ArrayList<Theatre>) thdb.AllScene();
            theop = FXCollections.observableArrayList(the);
            tabthe.setItems(theop);
    
    }
    
    public void LesSession(){
        SessionTheatreBD ss = new SessionTheatreBD();
        sess = (ArrayList<SessionTheatre>) ss.AllSession();
        session = FXCollections.observableArrayList(sess);
        listSession.setItems(session);
        
    }
    @FXML
    private void AjouterActeurAction(ActionEvent event) throws IOException{             
            CallInterfaces("AjoutActeur.fxml");
            }
    @FXML
    private void AjoutTheatre(ActionEvent event) throws IOException {
        CallInterfaces("AjoutSceneTheatrale.fxml");
    }
    public void CallInterfaces(String url) throws IOException
    {
      Parent  test = FXMLLoader.load(getClass().getResource(url));
            Stage st = new Stage();
            Scene scene = new Scene(test);
           st.setScene(scene);
            st.show();
        
    
    }    
    @FXML
    private void DeleteFromTV(ActionEvent event) {
        ActeurDB a = new ActeurDB();
        Acteur row = tactview.getSelectionModel().getSelectedItem();
       
        
        if ( row == null)
          {
             Alert info = new Alert(Alert.AlertType.ERROR);
             info.setHeaderText("Information");
             info.setContentText("Svp Selectionnez un acteur");
             info.showAndWait();
          }else {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
             info.setHeaderText("Supprimer Un Acteur");
             info.setContentText("Voulez-vous Supprimer cet acteur ?");
            
         Optional<ButtonType> rep = info.showAndWait();
         if (rep.get()==ButtonType.OK)
         {
            a.SupprimerActeur(row);
             
         }
        }
    }

    @FXML
    private void EditFromTV(ActionEvent event) throws IOException {
        
        Acteur row = tactview.getSelectionModel().getSelectedItem();
         System.out.println(row.getId());
        actsm = row ;
         
        if ( row == null)
          {
             Alert info = new Alert(Alert.AlertType.ERROR);
             info.setHeaderText("Information");
             info.setContentText("Svp Selectionnez un acteur");
             info.showAndWait();
          }
        else 
        {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
             info.setHeaderText("Modifier Un Acteur");
             info.setContentText("Voulez-vous Modifier cet acteur ?");
            
         Optional<ButtonType> rep = info.showAndWait();
         if (rep.get()==ButtonType.OK)
         {
             
             FXMLLoader lod = new FXMLLoader(getClass().getResource("ModifActeur.fxml"));
             Parent  test = lod.load();
             ModifActeurController modcon = (ModifActeurController)lod.getController();
            // modcon.savedata(row , ax);
             System.out.println(actsm);
            Stage st = new Stage();
            Scene scene = new Scene(test);
            st.setTitle("ModifierActeur");
            st.setScene(scene);
            st.show();
            
         }
        }
        
    }


    @FXML
    private void RefreshTV(ActionEvent event) {
       DonnéesActeur();
    }

    @FXML
    private void supprimerTheatreAction(ActionEvent event) {
         Theatre row = tabthe.getSelectionModel().getSelectedItem();
         TheatreDB dbt = new TheatreDB();
        if ( row == null)
          {
             Alert info = new Alert(Alert.AlertType.ERROR);
             info.setHeaderText("Information");
             info.setContentText("Svp Selectionnez un acteur");
             info.showAndWait();
          }else {
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
             info.setHeaderText("Supprimer Un Acteur");
             info.setContentText("Voulez-vous Supprimer cet acteur ?");
            
         Optional<ButtonType> rep = info.showAndWait();
         if (rep.get()==ButtonType.OK)
         {
            dbt.SupprimerSceneTheatrale(row);
            
         }
        }
        
    }

    @FXML
    private void ModifierTheatreAction(ActionEvent event) throws IOException {
        Theatre t = tabthe.getSelectionModel().getSelectedItem();
         thet = t ;
        FXMLLoader lod = new FXMLLoader(getClass().getResource("ModifierSceneTheatrale.fxml"));
             Parent  test = lod.load();
             ModifierSceneTheatraleController mod = (ModifierSceneTheatraleController)lod.getController();
             //mod.modfiierTheatre(thet);
             Stage st = new Stage();
             st.setScene(new Scene(test));
             st.show();
        
        
    }

    @FXML
    private void RefreshTheatreAction(ActionEvent event) {
        LesTheatres();
    }

    @FXML
    private void InformationTheatreAction(ActionEvent event) throws IOException {
         
              Theatre t = tabthe.getSelectionModel().getSelectedItem();
             FXMLLoader lod = new FXMLLoader(getClass().getResource("InformationDetaille.fxml"));
             Parent  test = lod.load();
             InformationDetailleController afficher = (InformationDetailleController)lod.getController();
             afficher.afficherPoseter(t);
             Stage st = new Stage();
             st.setScene(new Scene(test));
             st.show();
    }

    @FXML
    private void AjouterSessionTheatrale(ActionEvent event) throws IOException {
        CallInterfaces("AjouterSession.fxml");
    }

    @FXML
    private void RechercheO(KeyEvent event) {
           String acteur = tfrech.getText();
        
         
            obp  = FXCollections.observableArrayList(new ActeurDB().RechercherActeurs(acteur));
            Init();
        tactview.setItems(obp);
    }

    @FXML
    private void RechercherT(KeyEvent event) {
        String rech = RechercheTheatre.getText();
        
        theop = FXCollections.observableArrayList(new TheatreDB().RechercherTheatres(rech));
        Init();
        tabthe.setItems(theop);
    }

    @FXML
    private void RechercherS(KeyEvent event) {
        String re = Recherchesession.getText();
        session = FXCollections.observableArrayList(new SessionTheatreBD().RechercherTheatres(re));
        Init();
        listSession.setItems(session);
        
    }

    @FXML
    private void sessiondelete(ActionEvent event) {
        SessionTheatre sees = listSession.getSelectionModel().getSelectedItem();
        SessionTheatreBD sesdb = new SessionTheatreBD();
        sesdb.SupprimerSessionTheatre(sees);
    }

    @FXML
    private void sessionUpdate(ActionEvent event) throws IOException {
        SessionTheatre mod = listSession.getSelectionModel().getSelectedItem();
         sest = mod  ;
        FXMLLoader lod = new FXMLLoader(getClass().getResource("ModifierSession.fxml"));
             Parent  test = lod.load();
             ModifierSessionController sesmod = (ModifierSessionController)lod.getController();
             
             Stage st = new Stage();
             st.setScene(new Scene(test));
             st.show();
        
    }

    @FXML
    private void sessionRefresh(ActionEvent event) {
        LesSession();
    }

    @FXML
    private void QuitterAction(ActionEvent event) {
         Stage stage = (Stage) Quitter.getScene().getWindow();
         stage.close();
    }

    @FXML
    private void DeleteAllFromTV(ActionEvent event) {
        
        
    }

        
 
    

    

    
        
    }
    
    

