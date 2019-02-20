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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PanierController implements Initializable {
    @FXML
    private AnchorPane mainPane;
    private Connection cnx;
    @FXML
    private TableColumn table_nom;
    @FXML
    private TableColumn table_prix;
    @FXML
    private TableColumn table_quantity;
    @FXML
    private TableColumn table_total;
    @FXML
    private TableView<ShoppingCart> table_produit;

    public PanierController(){ cnx= ConnectionDB.getInstance().getConnection(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table_produit.setEditable(true);
        table_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        table_prix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_total.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        initColumns();
        loadData();
    }
    private void initColumns() {
        table_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        table_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        table_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


    }
    private void loadData() {
        ObservableList<ShoppingCart> data = null;
        try {
            data = FXCollections.observableArrayList(new CartServices().showCart(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table_produit.setItems(data);
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
    private void searchTable(KeyEvent keyEvent) {
    }
}
