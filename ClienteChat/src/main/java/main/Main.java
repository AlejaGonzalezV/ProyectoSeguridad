package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Clase que contiene el método main de la aplicación
 */
public class Main extends Application{
	
public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/name.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Chat");
		primaryStage.show();
		//11197407524272841722848553729673529121
		
		//Yo-ControllerChat
		//Miguel-ConnectionChat
		//Angie-ConnectionChatSer
		//Melqui-Server
	}

	public static void main (String[] args) {
		launch(args);
	
	}

}
