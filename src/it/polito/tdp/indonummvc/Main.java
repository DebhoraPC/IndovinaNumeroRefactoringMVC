package it.polito.tdp.indonummvc;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			// DOBBIAMO FARE QUESTA MODIFICA:
			FXMLLoader loader = new FXMLLoader(getClass().getResource("IndoNumeroRefactoringmvc.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// POSSIAMO FINALMENTE MANDARE AL CONTROLLER CIO' CHE GLI SERVE:
			Model model = new Model();
			((IndoNumeroRefactoringmvcController)loader.getController()).setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
