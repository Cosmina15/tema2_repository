package ro.mta.se.lab;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import ro.mta.se.lab.model.CountryTest;

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
            Result result = JUnitCore.runClasses(CountryTest.class);
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
            System.out.println("Country methods are ok " + result.wasSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
