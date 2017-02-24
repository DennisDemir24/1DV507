package ml224ec_assign3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpDownMain extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
	
		UpDownPane upDownPane = new UpDownPane();
		
		Scene scene = new Scene(upDownPane);
		scene.setOnKeyPressed(e -> upDownPane.handleInput(e) );
		
		arg0.setScene(scene);
		arg0.sizeToScene();
		
		arg0.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
