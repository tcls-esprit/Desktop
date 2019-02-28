package Controller;

import Model.CurrentUser;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CORBA.Current;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label name;
    @FXML
    private Label lastname;
    @FXML
    private JFXButton xbutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(CurrentUser.nom);
        lastname.setText(CurrentUser.prenom);
        String path= "C:\\Users\\PlusUltra\\Documents\\GitHub\\Desktop\\src\\View\\img\\";
        Image log = new Image("file:"+path+"close.png");
        xbutton.setGraphic(new ImageView(log));
    }

    @FXML
    private void loadCinema(ActionEvent actionEvent) {
    }

    @FXML
    private void loadMusee(ActionEvent actionEvent) {
    }

    @FXML
    private void loadStore(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/userStoreHome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadTheatre(ActionEvent actionEvent) {
    }

    @FXML
    private void loadEvents(ActionEvent actionEvent) {
    }

    @FXML
    private void Exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    @FXML
    private void logOut(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Cite De La Culture");
        stage.setScene(scene);
        stage.show();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
}
