package Controller;

import Model.CurrentUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageAdminController implements Initializable {
    @FXML
    private Label name;
    @FXML
    private Label lastname;
    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(CurrentUser.nom);
        lastname.setText(CurrentUser.prenom);
    }

    @FXML
    private void loadCinema(ActionEvent actionEvent) {
    }

    @FXML
    private void loadMusee(ActionEvent actionEvent) {
    }

    @FXML
    private void loadSalles(ActionEvent actionEvent) {
    }

    @FXML
    private void loadTheatre(ActionEvent actionEvent) {
    }

    @FXML
    private void loadEvents(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AfficheEventAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Cite De La Culture");
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void loadStore(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
    }
}
