package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        primaryStage.setTitle("Cite De La Culture");
        primaryStage.setScene(new Scene(root, 1150, 675));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
