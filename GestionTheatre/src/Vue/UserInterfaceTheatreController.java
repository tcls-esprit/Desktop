/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Entity.Acteur;
import Entity.SessionTheatre;
import Entity.Theatre;
import Service.SessionTheatreBD;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AQuel
 */
public class UserInterfaceTheatreController implements Initializable {

    @FXML
    private Button participerbtn;
    @FXML
    private Button Deconnection;
    @FXML
    private TableColumn<SessionTheatre, String> sceneth;
    @FXML
    private TableColumn<SessionTheatre, String> salle;
    @FXML
    private TableColumn<SessionTheatre, String> debut;
    @FXML
    private TableColumn<SessionTheatre, String> fin;
    @FXML
    private TableView<SessionTheatre> TableUser;
    @FXML
    private TableColumn<SessionTheatre, String> prixscene;
    private ArrayList<SessionTheatre> sess;
    private ObservableList<SessionTheatre> session;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Inisialisation();
        Session();
    }    

    @FXML
    private void participerAction(ActionEvent event) throws DocumentException {
     SessionTheatre s = TableUser.getSelectionModel().getSelectedItem();
        System.out.println(s);
        System.out.println(s.getId_sess());
        Document ticket = new Document(PageSize.A7);
            ticket.addAuthor("Mayank");
            ticket.addTitle("Votre Ticket de participation");
        try {
            
            
            PdfWriter.getInstance(ticket, new FileOutputStream("Ticket.pdf"));
            ticket.open();
            String pdf = " Votre Scene:  "+s.getId_scene()+"\n  la Salle:  "+ s.getId_salle()+"\n  Heure Debut:  "+s.getDate_debut()+"\n  Heure Fin:  "+s.getDate_fin()+"\n  ¨Prix:  "+s.getPrix();
            Paragraph contenu = new Paragraph(pdf);
            ticket.add(contenu);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserInterfaceTheatreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ticket.close();
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) {
         Stage stage = (Stage) Deconnection.getScene().getWindow();
         stage.close();
        
    }
    
    private void Inisialisation()
    {
     salle.setCellValueFactory(new PropertyValueFactory<>("id_salle"));
     debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
     fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
     prixscene.setCellValueFactory(new PropertyValueFactory<>("prix"));
     sceneth.setCellValueFactory(new PropertyValueFactory<>("idscene_fk"));
    }
    
   private void Session()
    {
    SessionTheatreBD ss = new SessionTheatreBD();
       sess = (ArrayList<SessionTheatre>) ss.AllSession();
       session = FXCollections.observableArrayList(sess);
         System.out.println(session);
        
        TableUser.setItems(session);
        
    
    }
}

