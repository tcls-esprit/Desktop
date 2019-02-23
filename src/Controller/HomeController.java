package Controller;

import Model.*;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;



public class HomeController  implements Initializable {
    @FXML
    private TableColumn table_nom;
    @FXML
    private TableColumn table_prix;
    @FXML
    private TableColumn table_description;
    @FXML
    private TableColumn table_category;
    @FXML
    private TableColumn table_Quantity;
    @FXML
    private TableView<ProduitStock> table_produit;

    private Connection cnx;
    @FXML
    private ImageView view;
    @FXML
    private JFXTextField searchInput;
    @FXML
    private AnchorPane mainPane;

    public HomeController(){cnx = ConnectionDB.getInstance().getConnection();}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        table_description.setCellFactory(TextFieldTableCell.forTableColumn());
        table_category.setCellFactory(TextFieldTableCell.forTableColumn());
        table_prix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_Quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        initColumns();
        loadData();
    }
    private void initColumns() {

        //id_prod.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        table_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        table_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        //table_update.setCellValueFactory(new PropertyValueFactory<>("image"));
        //colEdit.setCellFactory(cellFactory);

    }
    private void loadData() {
        ObservableList<ProduitStock> data = null;
        try {
            data = FXCollections.observableArrayList(new StockServices().listerProduit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table_produit.setItems(data);
    }

    @FXML
    private void searchTable(KeyEvent keyEvent) {
        String s = searchInput.getText();
        ObservableList<ProduitStock> data = null;
        try {
            data = FXCollections.observableArrayList(new StockServices().filtrerProduit(s));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initColumns();
        table_produit.setItems(data);
    }

    @FXML
    private void showImage(MouseEvent Event) {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        //System.out.println(prod);
        StockServices s = new StockServices();
        int id = prod.getId();
        //System.out.println(id);
        String req = "select image from product where id="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                String title = rs.getString("image");
               // System.out.println(title);
                Image image = new Image("file:C:/xampp/htdocs/ImageStore/"+title);
                view.setImage(image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadPanier(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/userPanier.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void addToPane(ActionEvent actionEvent) throws SQLException {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        CartServices s = new CartServices();
        int id = prod.getId();
        s.fillCart(CurrentUser.id,id,1);
    }
}
