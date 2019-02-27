/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.esprit.entites.Oeuvre;
import com.esprit.entites.TypeUser;
import com.esprit.services.OeuvreService;
import static com.esprit.services.UserService.LoggedUser;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author souissi oussama
 */
public class AfficheOeuvreEventController implements Initializable {

    @FXML
    private TableView<Oeuvre> tableoeuvres;
    @FXML
    private TableColumn<Oeuvre, String> columntitre;
    @FXML
    private TableColumn<Oeuvre, Float> columnprix;
    @FXML
    private TableColumn<Oeuvre, String> columntype;
    @FXML
    private TableColumn<Oeuvre, Date> columndate;
    @FXML
    private JFXTextField txttitre;
    @FXML
    private JFXTextField txtprix;
    @FXML
    private JFXDatePicker pickerdate;
    @FXML
    private JFXComboBox<String> boxtype;
    @FXML
    private Pane pane;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    int idevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        ObservableList<String> data = FXCollections.observableArrayList("Peinture", "Sculpture", "Livre");
        boxtype.setItems(data);
        if (LoggedUser.getType()==TypeUser.Admin)
        {
            pane.setVisible(true);
        }
    }    
    void afficher(int id) {
    OeuvreService os = new OeuvreService();
    idevent=id;
    //System.out.print(id);
    tableoeuvres.setItems(os.afficheSelonEvent(id));
    columntitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    columnprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    columndate.setCellValueFactory(new PropertyValueFactory<>("DateCreation"));
    columntype.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    @FXML
    void supprimer(ActionEvent e)
    {
        if (!tableoeuvres.getSelectionModel().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression d'oeuvre");
            alert.setHeaderText("etes-vous sur que vous voulez supprimer l'oeuvre :  "
                    + tableoeuvres.getSelectionModel().getSelectedItem().getTitre() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                OeuvreService os = new OeuvreService();
                os.delete(tableoeuvres.getSelectionModel().getSelectedItem().getId());
                
                afficher(idevent);
            }
        }
        else {alert();}
    }
    @FXML
    void ajouterOeuvre(ActionEvent e)
    {
        Oeuvre o = new Oeuvre(boxtype.getValue(), Float.valueOf(txtprix.getText()), asDate(pickerdate.getValue()), txttitre.getText(),idevent);
        OeuvreService os = new OeuvreService();
        os.insert(o);
        afficher(idevent);
    }
    @FXML
    void modifierOeuvre(ActionEvent e)
    {
        if (!tableoeuvres.getSelectionModel().isEmpty())
        {    
        Oeuvre o = new Oeuvre(boxtype.getValue(), Float.valueOf(txtprix.getText()), asDate(pickerdate.getValue()), txttitre.getText(),idevent);
        OeuvreService os = new OeuvreService();
        os.update(o,tableoeuvres.getSelectionModel().getSelectedItem().getId());
        afficher(idevent);
        }
        else {alert();}
    }
    public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    public void alert ()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("info");
        alert.setHeaderText("aucun oeuvre n'a été selectioné ");
        alert.show();
    }
    
    
    
}
