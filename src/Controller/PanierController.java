package Controller;

import Model.*;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
import java.sql.*;
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
    private TableColumn<ShoppingCart, Integer> table_quantity;
    @FXML
    private TableColumn table_total;
    @FXML
    private TableView<ShoppingCart> table_produit;
    @FXML
    private JFXTextField searchInput;
    @FXML
    private Label result;

    public PanierController() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table_produit.setEditable(true);
        /*table_nom.setEditable(false);
        table_total.setEditable(false);
        table_prix.setEditable(false);*/
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
        String s = searchInput.getText();
        ObservableList<ShoppingCart> data = null;
        try {
            data = FXCollections.observableArrayList(new CartServices().filtrerCart(s));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initColumns();
        table_produit.setItems(data);
    }

    @FXML
    private void editableQuantity(TableColumn.CellEditEvent editedCell) throws SQLException {
        ShoppingCart prod = table_produit.getSelectionModel().getSelectedItem();
        //prod.setName(editedCell.getNewValue().toString());
        int id = prod.getId();
        //System.out.println(id);
        Double d = prod.getPrice();
        String req = "update cart set quantity=? where id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, editedCell.getNewValue().toString());
        ps.executeUpdate();
        String req1 = "select quantity from cart where id=" + id;
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req1);
        if (rs.next()) {
            String req2 = "update cart set total=? where id=" + id;
            PreparedStatement ps1 = cnx.prepareStatement(req2);
            ps1.setDouble(1, prod.getPrice() * rs.getInt("quantity"));
            ps1.executeUpdate();
            loadData();
        }
    }

    @FXML
    private void removeItem(ActionEvent actionEvent) {
        ShoppingCart prod = table_produit.getSelectionModel().getSelectedItem();
        CartServices s = new CartServices();
        int id = prod.getId();
        try {
            s.removeCart(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadData();
    }

    @FXML
    private void payItems(ActionEvent actionEvent) throws SQLException {
        CartServices c = new CartServices();
        Double tot = c.showCart(2).stream().mapToDouble(e -> e.getTotal()).sum();
        System.out.println(tot);
        //pay.chargeCreditCard(tot);
        result.setText("Payment Accepted! =D");
        c.showCart(2).stream().forEach(e->{
            int id = e.getId();
            //System.out.println(id);
            String req1 = "delete from cart where id="+id;
            try {
                Statement st = cnx.createStatement();
                st.executeUpdate(req1);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        loadData();
    }
}
