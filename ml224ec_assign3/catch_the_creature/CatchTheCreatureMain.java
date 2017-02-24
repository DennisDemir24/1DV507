package ml224ec_assign3.catch_the_creature;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CatchTheCreatureMain extends Application {

	private Label countLabel;
	
	private int count = 0;
	
	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
	
		countLabel = new Label("Times caught: 0");
		countLabel.setMaxWidth(200);
		countLabel.setTextFill(Color.WHITE);
		
		BorderPane root = new BorderPane();
		
		Label text = new Label("Click on the bug to catch it!");
		text.setAlignment(Pos.CENTER);
		
		CatchTheCreatureGame game = new CatchTheCreatureGame();
		
		game.setSize(300, 250);
		
		game.getChildren().addAll(countLabel);
		
		game.setOnCreatureCaught(e -> updateCount());
		
		root.setTop(text);
		root.setCenter(game);
		
		BorderPane.setAlignment(text, text.getAlignment());
		
		Scene scene = new Scene(root);
		
		stage.setTitle("Catch the Bug!");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		
		stage.show();
	}

	private void updateCount()
	{
		count++;
		countLabel.setText(String.format("Times caught: %d", count));
	}
}
