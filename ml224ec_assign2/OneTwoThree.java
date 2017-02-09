package ml224ec_assign2;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OneTwoThree extends Application {

	@Override
	public void start(Stage primaryStage)
	{
		GridPane grid = new GridPane();
		
		HBox upperBox = new HBox();
		Text upperText = new Text(20, 50, "One");
		upperText.setTextAlignment(TextAlignment.LEFT);
		upperText.setId("upper-text");

		upperBox.getChildren().add(upperText);
		upperBox.setId("upper-box");
		upperBox.setMinHeight(100);
		upperBox.setMinWidth(300);
		upperBox.setPadding(new Insets(20.0d));
		
		
		HBox centerBox = new HBox();
		Text centerText = new Text(20, 50, "Two");
		centerText.setTextAlignment(TextAlignment.CENTER);
		centerText.setId("center-text");

		centerBox.getChildren().add(centerText);
		centerBox.setId("center-box");
		centerBox.setMinHeight(100);
		centerBox.setMinWidth(300);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setPadding(new Insets(20.0d));
		
		HBox lowerBox = new HBox();
		Text lowerText = new Text(20, 50, "Three");
		lowerText.setTextAlignment(TextAlignment.RIGHT);
		lowerText.setId("lower-text");

		lowerBox.getChildren().add(lowerText);
		lowerBox.setId("lower-box");
		lowerBox.setMinHeight(100);
		lowerBox.setMinWidth(300);
		lowerBox.setAlignment(Pos.BOTTOM_RIGHT);
		lowerBox.setPadding(new Insets(20.0d));
		
		grid.add(upperBox, 0, 1);
		grid.add(centerBox,0, 2);
		grid.add(lowerBox, 0, 3);
		
		Group root = new Group();
		
		root.getChildren().addAll(grid);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("One Two Three");
		primaryStage.setScene(scene);
		scene.getStylesheets().add(
				"./ml224ec_assign2/onetwothree.css"
				);
		primaryStage.setResizable(false); // No, no. no resizing.
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		//Path path = Paths.get("./ml224ec_assign2")
		
		launch(args);
	}

}
