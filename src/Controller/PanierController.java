package Controller;

import Model.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @FXML
    private Label total;
    @FXML
    private Label euro;
    @FXML
    private Label dinar;
    @FXML
    private Label transactionDate;
    @FXML
    private Label lastname;
    @FXML
    private Label name;
    @FXML
    private Label liveEuro;
    @FXML
    private Label liveDinar;
    @FXML
    private Label imgdinar;
    @FXML
    private Label imgeuro;
    @FXML
    private Label rateLive;
    @FXML
    private Label imgusd;
    @FXML
    private Label basket;
    @FXML
    private JFXButton cashOut;


    public PanierController() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(CurrentUser.nom);
        lastname.setText(CurrentUser.prenom);
        table_produit.setEditable(true);
        table_nom.setEditable(false);
        table_total.setEditable(false);
        table_prix.setEditable(false);
        table_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        table_prix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_total.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        initColumns();
        try {
            loadData();
        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
        String path= "C:\\Users\\PlusUltra\\Documents\\GitHub\\Desktop\\src\\View\\img\\";
        Image image = new Image("file:"+path+"icons8-tunisia-48.png");
        imgdinar.setGraphic(new ImageView(image));
        Image image1 = new Image("file:C:\\Users\\PlusUltra\\Documents\\GitHub\\Desktop\\src\\View\\img\\europe-48.png");
        imgeuro.setGraphic(new ImageView(image1));
        Image usa = new Image("file:C:\\Users\\PlusUltra\\Documents\\GitHub\\Desktop\\src\\View\\img\\shop.png");
        imgusd.setGraphic(new ImageView(usa));
        Image live = new Image("file:C:\\Users\\PlusUltra\\Documents\\GitHub\\Desktop\\src\\View\\img\\exchange.png");
        rateLive.setGraphic(new ImageView(live));
        Image Basket = new Image("file:"+path+"bag.png");
        basket.setGraphic(new ImageView(Basket));
        Image pay = new Image("file:"+path+"pay.png");
        cashOut.setGraphic(new ImageView(pay));

    }

    private void initColumns() {
        table_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        table_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        table_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        table_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));


    }

    public void loadData() throws SQLException, JSONException {
        ObservableList<ShoppingCart> data = null;
        try {
            data = FXCollections.observableArrayList(new CartServices().showCart(CurrentUser.id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table_produit.setItems(data);
        CartServices c = new CartServices();
        Double tot = c.showCart(CurrentUser.id).stream().mapToDouble(e -> e.getTotal()).sum();
        total.setText(String.valueOf(tot));
        JSONObject currency = CurrencyConversion.sendLiveRequest();
        Double dinars = currency.getJSONObject("quotes").getDouble("USDTND") * tot;
        Double euros = (currency.getJSONObject("quotes").getDouble("USDEUR") * tot);
        euro.setText(String.format("%.3f", euros));
        dinar.setText(String.format("%.3f", dinars));
        Date timeStampDate = new Date((long)(currency.getLong("timestamp")*1000));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(timeStampDate);
        liveEuro.setText("1 " + currency.getString("source") + " in EUR : " + currency.getJSONObject("quotes").getDouble("USDEUR") + " (Date: " + formattedDate + ")");
        liveDinar.setText("1 " + currency.getString("source") + " in TND : " + currency.getJSONObject("quotes").getDouble("USDTND") + " (Date: " + formattedDate + ")");

        //System.out.println(currency);

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
    private void editableQuantity(TableColumn.CellEditEvent editedCell) throws SQLException, JSONException {
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
    private void removeItem(ActionEvent actionEvent) throws SQLException, JSONException {
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
    private void payItems(ActionEvent actionEvent) throws SQLException, JSONException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/CreditCard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Cite De La Culture");
        stage.setScene(scene);
        stage.showAndWait();
        loadData();
        /*PaymentServices pay = new PaymentServices();
        CartServices c = new CartServices();
        Double tot = c.showCart(CurrentUser.id).stream().mapToDouble(e -> e.getTotal()).sum();
        //System.out.println(tot);
        if(!tot.equals(0.0)){
        Charge y = pay.chargeCreditCard(tot);
        result.setText("Payment Accepted! =D, Total of = "+tot +" USD");
        addToHistroy(y);
        }

        c.showCart(CurrentUser.id).stream().forEach(e ->
    {
        int id = e.getId();
        String req1 = "delete from cart where id=" + id;
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    });*/

    //loadData();
}

    @FXML
    private void loadHistory(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/History.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
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