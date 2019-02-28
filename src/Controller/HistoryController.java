package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TableView<History> table_history;
    @FXML
    private TableColumn<History,String> table_trx;
    @FXML
    private TableColumn<History,Double> table_amount;
    @FXML
    private TableColumn<History,String> table_date;
    @FXML
    private Label name;
    @FXML
    private Label lastname;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(CurrentUser.nom);
        lastname.setText(CurrentUser.prenom);
        table_trx.setCellFactory(TextFieldTableCell.forTableColumn());
        table_amount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_date.setCellFactory(TextFieldTableCell.forTableColumn());
        initColumns();
        loadData();
    }

    private void initColumns() {
        table_trx.setCellValueFactory(new PropertyValueFactory<>("idt"));
        table_amount.setCellValueFactory(new PropertyValueFactory<>("amounth"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("dateh"));
    }

    @FXML
    private void loadHome(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/userStoreHome.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void loadP(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/userPanier.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void searchTable(KeyEvent keyEvent) {
    }

    private void loadData(){
        ObservableList<History> data = null;
        try {
            data = FXCollections.observableArrayList(new HistoryService().showHistory(CurrentUser.id));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        table_history.setItems(data);

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

    @FXML
    private void backHome(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/HomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Cite De La Culture");
        stage.setScene(scene);
        stage.show();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
}
