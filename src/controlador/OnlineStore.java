package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vista.AnadirArticuloController;
import vista.GestionOSController;
import vista.MostrarArticulosController;

public class OnlineStore extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Crea los controladores
		AnadirArticuloController addArticuloController = new AnadirArticuloController(); // Aquí está el error
		GestionOSController gestionOSController = new GestionOSController();
		MostrarArticulosController mostrarArticulosController = new MostrarArticulosController();

		// Crea el FXMLLoader y carga la GUI
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionOS.fxml"));
		Parent root = loader.load();

		// Establece las referencias a los controladores en el controlador de la GUI
		((GestionOSController) loader.getController()).setAnadirArticuloController(addArticuloController);
		((GestionOSController) loader.getController()).setGestionOSController(gestionOSController);
		((GestionOSController) loader.getController()).setMostrarArticulosController(mostrarArticulosController);

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