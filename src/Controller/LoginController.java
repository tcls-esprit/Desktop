/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.TypeUser;
import Entities.User;
import Services.UserService;
import com.sun.prism.shader.FillCircle_Color_AlphaTest_Loader;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txt_login;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label message;
    @FXML
    private Label lab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    UserService us = new UserService() ; 

    @FXML
    private void login(ActionEvent event) throws SQLException {
        if (us.isLogin(txt_login.getText(), txt_password.getText()))
      {
       if (UserService.LoggedUser.getType().equals(TypeUser.Admin))  
       {  
           System.out.println("hhhhhhhhhhhh");
       }
       else if (UserService.LoggedUser.getType().equals(TypeUser.SimpleUser))  
       {
           System.out.println("hhh");
       }
       else {
           System.out.println("h"); 
           //khaled
       }
      }
        else 
        {
         lab.setText("UserName ou Password Invalid");
         lab.setTextFill(Color.RED);
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
    private void close(MouseEvent event) {
        
    }
   

    
}
