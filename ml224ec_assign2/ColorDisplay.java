package ml224ec_assign2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ColorDisplay extends Application {

	private Rectangle displayRectangle;
	private TextField rgbFieldRed;
	private TextField rgbFieldGreen;
	private TextField rgbFieldBlue;
	private Button displayButton;
	
	private GridPane fieldLayout; // for the three rgb fields
	private GridPane sceneLayout; // root
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		initialize();
		
		primaryStage.setScene(new Scene(sceneLayout));
		primaryStage.setTitle("RGB Color Display");
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	private void initialize()
	{
		displayRectangle = new Rectangle();
		displayRectangle.setHeight(150);
		displayRectangle.setWidth(300);
		displayRectangle.setFill(Color.BLACK);
		
		rgbFieldRed = new TextField("0");
		rgbFieldGreen = new TextField("0");
		rgbFieldBlue = new TextField("0");
		
		rgbFieldRed.setPrefWidth(75);
		rgbFieldGreen.setPrefWidth(75);
		rgbFieldBlue.setPrefWidth(75);
		
		fieldLayout = new GridPane();
		fieldLayout.setHgap(15);
		fieldLayout.setAlignment(Pos.CENTER);
		
		fieldLayout.add(new Text("Red:"), 0, 0);
		fieldLayout.add(new Text("Green:"), 1, 0);
		fieldLayout.add(new Text("Blue:"), 2, 0);
		
		fieldLayout.add(rgbFieldRed, 0, 1);
		fieldLayout.add(rgbFieldGreen, 1, 1);
		fieldLayout.add(rgbFieldBlue, 2, 1);
		
		displayButton = new Button("Display Color");
		displayButton.setOnMouseClicked(e -> onButtonClicked());
		GridPane.setHalignment(displayButton, HPos.CENTER); // setAlignment does not work
		
		sceneLayout = new GridPane();
		
		sceneLayout.add(displayRectangle, 0, 0);
		sceneLayout.add(fieldLayout, 0, 1);
		sceneLayout.add(displayButton, 0, 2);
		
		sceneLayout.setVgap(15);
		sceneLayout.setPadding(new Insets(0,0,20,0));
	}
	
	// or call it "onDisplayButtonMouseClicked"..
	private void onButtonClicked()
	{
		try {
			int r = Integer.parseInt(rgbFieldRed.getText());
			int g = Integer.parseInt(rgbFieldGreen.getText());
			int b = Integer.parseInt(rgbFieldBlue.getText());
			
			// if any of the colors values show as -1, then an error has occurred
			if (0 > r && r > 255)
				r = -1;
			if (0 > g && g > 255)
				g = -1;
			if (0 > b && b > 255)
				b = -1;
			
			if (r == -1 || g == -1 || b == -1)
				showErrorPrompt("RGB colors has to be between 0 and 255!");
			else
				displayRectangle.setFill(Color.rgb(r, g, b));
		}
		catch (Exception E)
		{
			showErrorPrompt("Please input positive intgers between 0 and 255!");
		}
	}
	
	// Also prints to the system output
	private void showErrorPrompt(String message)
	{
		// assignment also stated printing it, this exists just to be sure
		System.out.printf("Error: %s\n", message);
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Input Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

}
