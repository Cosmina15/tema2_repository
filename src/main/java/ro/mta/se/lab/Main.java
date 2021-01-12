package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class is the Main of application.
 */
public class Main extends Application{
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * This method is used load the details we create in Scene Builder
     * @throws IOException
     */
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(this.getClass().getResource("/view/tema.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
