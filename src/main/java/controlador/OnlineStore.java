package main.java.controlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.vista.AnadirArticuloController;
import main.java.vista.GestionOSController;

public class OnlineStore extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Crea los controladores

        AnadirArticuloController addArticuloController = new AnadirArticuloController();
        GestionOSController gestionOSController = new GestionOSController();

        // Crea el FXMLLoader y carga la GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/vista/GestionOS.fxml"));
         Parent root = loader.load();

        // Establece las referencias a los controladores en el controlador de la GUI
        ((GestionOSController) loader.getController()).setAnadirArticuloController(addArticuloController);
        ((GestionOSController) loader.getController()).setGestionOSController(gestionOSController);

        // Crea la Scene y muéstrala
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Online Store App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}