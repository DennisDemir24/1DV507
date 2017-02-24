package ml224ec_assign3.bouncy_balls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BouncyBallCanvas extends Canvas {

	ObservableList<BouncyBall> balls;
	
	public BouncyBallCanvas()
	{
		balls = FXCollections.observableArrayList();
	}
	
	public void spawnBall()
	{
		BouncyBall ball = new BouncyBall();
		ball.setCenterX(this.getWidth()/2);
		ball.setCenterY(this.getHeight()/2);
		ball.setRadius(10);
		
		ball.setFill(Color.RED);
		
		balls.add(ball);
	}
	
	public void updateAndAnimate()
	{
		// Get the context and start a new frame
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.clearRect(0, 0, this.getHeight(), this.getWidth());
		
		for (int i = 0; i < balls.size(); i++)
		{
			BouncyBall ball = balls.get(i);
			// Check and handle ball collision
			for (int j = i + 1; j < balls.size(); j++)
				ball.handleCollision(balls.get(j));
			
			// Update boundaries and move the ball
			ball.setBoundaries(this.getWidth(), this.getHeight());
			ball.update();
			
			// Draw the balls on the canvas
			gc.setFill(ball.getFill());
			gc.fillOval(ball.getX(), ball.getY(), ball.getRadius()*2, ball.getRadius()*2);
		}
	}
	
}
