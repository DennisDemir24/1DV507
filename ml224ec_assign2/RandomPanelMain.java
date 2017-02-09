package ml224ec_assign2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RandomPanelMain extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		RandomPanel randpanel = new RandomPanel();
		
		Group root = new Group();
		root.getChildren().add(randpanel);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Random");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public static void main(String[] args) {

		launch(args);
		
	}
}
