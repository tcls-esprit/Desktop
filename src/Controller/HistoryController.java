package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
