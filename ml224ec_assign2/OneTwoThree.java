package ml224ec_assign2;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OneTwoThree extends Application {

	@Override
	public void start(Stage primaryStage)
	{
		GridPane grid = new GridPane();
		
		Text upperText = new Text("One");
		upperText.setTextAlignment(TextAlignment.LEFT);
		upperText.setFont(new Font(20));
		upperText.setFill(Color.WHITE);

		HBox upperBox = new HBox();
		upperBox.getChildren().add(upperText);
		upperBox.setMinHeight(100);
		upperBox.setMinWidth(300);
		upperBox.setPadding(new Insets(20.0d));
		upperBox.setStyle("-fx-background-color: #211111;");
		
		
		Text centerText = new Text("Two");
		centerText.setTextAlignment(TextAlignment.CENTER);
		centerText.setFont(new Font(20));
		centerText.setFill(Color.WHITE);

		HBox centerBox = new HBox();
		centerBox.getChildren().add(centerText);
		centerBox.setMinHeight(100);
		centerBox.setMinWidth(300);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setPadding(new Insets(20.0d));
		centerBox.setStyle("-fx-background-color: #322222;");
		
		Text lowerText = new Text("Three");
		lowerText.setTextAlignment(TextAlignment.RIGHT);
		lowerText.setFont(new Font(20));
		lowerText.setFill(Color.WHITE);

		HBox lowerBox = new HBox();
		lowerBox.getChildren().add(lowerText);
		lowerBox.setMinHeight(100);
		lowerBox.setMinWidth(300);
		lowerBox.setAlignment(Pos.BOTTOM_RIGHT);
		lowerBox.setPadding(new Insets(20.0d));
		lowerBox.setStyle("-fx-background-color: #433333;");
		
		grid.add(upperBox, 0, 1);
		grid.add(centerBox,0, 2);
		grid.add(lowerBox, 0, 3);
		
		Group root = new Group();
		
		root.getChildren().addAll(grid);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("One Two Three");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false); // No, no. no resizing.
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		//Path path = Paths.get("./ml224ec_assign2")
		
		launch(args);
	}

}
