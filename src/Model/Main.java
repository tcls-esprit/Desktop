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
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
        //User khaled = new User("khaled","saidi","khaledtsu@gmail.com","loli12-*","1995-08-03",Gender.Male,"23375472",13626155,TypeUser.Gerant);
        //UserService x = new UserService();
        //x.insert(khaled);
    }
}
