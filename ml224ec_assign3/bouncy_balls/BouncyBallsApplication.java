package ml224ec_assign3.bouncy_balls;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BouncyBallsApplication extends Application {
	
	Stage currentStage;
	
	StackPane root;
	Button addBallButton;
	BouncyBallCanvas bouncyBallCanvas;
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		currentStage = stage;
		
		root = new StackPane();
		root.setPadding(new Insets(0));
		
		bouncyBallCanvas = new BouncyBallCanvas();
		bouncyBallCanvas.setHeight(300);
		bouncyBallCanvas.setWidth(300);
		
		addBallButton = new Button("Add Ball");
		addBallButton.setAlignment(Pos.CENTER);
		addBallButton.setOnAction(e -> bouncyBallCanvas.spawnBall());
		
		root.getChildren().addAll(bouncyBallCanvas, addBallButton);

		Scene scene = new Scene(root);
		
		currentStage.setScene(scene);
		currentStage.setTitle("Ball Simulator 2018");
		currentStage.setResizable(false);
		currentStage.sizeToScene();
		currentStage.show();
		
		new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				bouncyBallCanvas.updateAndAnimate();
			}
		}.start();
	}
	
}
