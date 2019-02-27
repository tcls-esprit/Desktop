package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
        primaryStage.setTitle("Cite De La Culture");
        primaryStage.setScene(new Scene(root, 1450, 675));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
//        User khaled = new User("saleh","saidi","saleh@gmail.com","loli12-*","1995-08-03",Gender.Male,"22134232",13623455,TypeUser.Admin,"zabeba");
//        UserService x = new UserService();
//        x.insert(khaled);
    }
}
