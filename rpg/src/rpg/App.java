package rpg;

import java.lang.reflect.Constructor;

import Item.Item;
import User.User;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{
	public static void main(String[] args) {
		launch(args);	
	}
	
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("town.fxml"));	
		Parent pt = loader.load();
		
		Scene scene = new Scene(pt);
		scene.getStylesheets().add(App.class.getResource("interface.css").toExternalForm());
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	
	public void stop() throws Exception {
		
	}
}
