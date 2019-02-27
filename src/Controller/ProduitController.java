package Controller;

import Model.ProduitStock;
import Model.StockServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProduitController {

    @FXML
    private JFXTextField name_produit;

    @FXML
    private JFXTextField price_store;

    @FXML
    private JFXTextField description_store;

    @FXML
    private JFXTextField category_store;

    @FXML
    private JFXTextField quantity_store;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private JFXTextField fileName;

    @FXML
    void saveProduct(ActionEvent event) {

        StockServices st= new StockServices();
        ProduitStock product = new ProduitStock();
        product.setName(name_produit.getText());
        product.setPrice(Double.parseDouble(price_store.getText()));
        product.setDescription(description_store.getText());
        product.setCategory(category_store.getText());
        product.setQuantity(Integer.parseInt(quantity_store.getText()));
        product.setImage(fileName.getText());
        st.ajouterProduit(product);
        saveAlert(product);
        clearFields();
    }



    private void saveAlert(ProduitStock p){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produit enregistré avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Le produit "+p.getName()+ " a été créé.");
        alert.showAndWait();
    }
    private void clearFields() {
        //Prod_id.setText("Prod ID:");
        name_produit.clear();
        price_store.clear();
        description_store.clear();
        category_store.clear();
        quantity_store.clear();
        fileName.clear();
    }

    @FXML
    private void resetAjouter(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    private void showList(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/listproduits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);

    }

    @FXML
    private void openFile(ActionEvent actionEvent) {
        Stage primary = new Stage();
        //primary.isAlwaysOnTop();
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Selectionner Une Image");
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = filechooser.showOpenDialog(primary);
        String path ="C:\\xampp\\htdocs\\ImageStore";
        fileName.setText(file.getName());
        if(file!=null)
        {
            //System.out.println("Chosen file : "+file.getName());
            try {
                Files.copy(file.toPath(),new File(path+"\\"+file.getName()).toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void backHome(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/adminHomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Cite De La Culture");
        stage.setScene(scene);
        stage.show();
        ((Node) (mouseEvent.getSource())).getScene().getWindow().hide();
    }
}



