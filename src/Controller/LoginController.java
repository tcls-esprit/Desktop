/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.CurrentUser;
import Model.TypeUser;
import Model.User;
import Model.UserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.shader.FillCircle_Color_AlphaTest_Loader;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class LoginController implements Initializable {


    @FXML
    private Label verif;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pwdd;
    @FXML
    private MediaView playground;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        MediaPlayer oracleVid = new MediaPlayer(new Media());
//        playground.setMediaPlayer(oracleVid);
//        oracleVid.setMute(true);
//        oracleVid.setRate(20);
//        oracleVid.setCycleCount(MediaPlayer.INDEFINITE);
//        oracleVid.play();

        // TODO
    }   
    UserService us = new UserService() ;

    @FXML
    private void loginToMain(ActionEvent actionEvent) throws SQLException, IOException {
        Pattern p = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
        Matcher m = p.matcher(user.getText());
        if (user.getText().equals("")) {
            verif.setText("veuillez saisir votre email");
            verif.setStyle("-fx-text-fill: #ff1744; -fx-background-color: #26A69A;");
        } else if(!m.find()){
            verif.setText("veuillez saisir une adresse email correcte!");
            verif.setStyle("-fx-text-fill: #ff1744; -fx-background-color: #26A69A;");
        } else if (pwdd.getText().equals("")) {
            verif.setText("veuillez saisir votre mot de passe");
            verif.setStyle("-fx-text-fill: #ff1744; -fx-background-color: #26A69A;");
        } else if (!us.login(user.getText(), pwdd.getText())) {
            verif.setText("cordonnées invalides");
            verif.setStyle("-fx-text-fill: #ff1744; -fx-background-color: #26A69A;");
        } else {
            CurrentUser cu = new CurrentUser(us.getUserByEmail(user.getText()));

            if (cu.type.equals(TypeUser.Gerant)) {
                Parent root = FXMLLoader.load(getClass().getResource("../View/adminHomePage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Cite De La Culture");
                /*stage.initStyle(StageStyle.UNDECORATED);
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.setX(event.getScreenX() );
                        stage.setY(event.getScreenY() );
                    }
                });*/
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            else if (cu.type.equals(TypeUser.SimpleUser)) {
                Parent root = FXMLLoader.load(getClass().getResource("../View/HomePage.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Cite De La Culture");
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
        }
    }
    
    @FXML
    private void signup(ActionEvent event) throws IOException {
            Stage stage = new Stage();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("newaccount.fxml"));
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void closeProgram(ActionEvent actionEvent) {
        Platform.exit();
    }
}
