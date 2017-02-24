package ml224ec_assign3.catch_the_creature;

import java.util.Random;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class CatchTheCreatureGame extends Pane {

	private Rectangle creature;
	
	private EventHandler<Event> onCaughtHandler;
	
	public CatchTheCreatureGame()
	{
		creature = new Rectangle(32,32);
		//creature.setFill(Color.BLUE);
		
		Image img = new Image("/ml224ec_assign3/catch_the_creature/bug.png");
		
		creature.setFill(new ImagePattern(img));
		
		creature.setStyle("-fx-background-color: #fff");
		
		this.getChildren().add(creature);
		this.setOnMouseClicked(e -> this.handleMouseClick(e));
		
		this.setStyle("-fx-background-color: #999");
	}
	
	public void setSize(double width, double height)
	{
		this.setMinSize(width, height);
		this.setPrefSize(width, height);
		relocateCreature();
	}
	
	public void setOnCreatureCaught(EventHandler<Event> handler)
	{
		onCaughtHandler = handler;
	}
	
	private void handleMouseClick(MouseEvent e)
	{
		if (isWithin(e.getX(), e.getY(), creature))
		{
			onCaughtHandler.handle(null);
			relocateCreature();
		}
	}
	
	private boolean isWithin(double x, double y, Rectangle rect)
	{
		double minX = rect.getX();
		double maxX = minX + rect.getWidth();
		
		double minY = rect.getY();
		double maxY = minY + rect.getHeight();
		
		return (minX <= x && x <= maxX) && (minY <= y && y <= maxY);
	}
	
	private double getVaildHeight()
	{
		double h = this.getHeight();
		if (h == 0)
			h = this.getMinHeight();
		return h;
	}
	
	private double getVaildWidth()
	{
		double w = this.getWidth();
		if (w == 0)
			w = this.getMinWidth();
		return w;
	}
	
	private void relocateCreature()
	{
		double maxX = this.getVaildWidth() - creature.getWidth();
		double maxY = this.getVaildHeight() - creature.getHeight();
		
		Random rand = new Random();
		
		double x = rand.nextInt((int) (maxX+1));
		double y = rand.nextInt((int) (maxY+1));
		
		creature.setX(x);
		creature.setY(y);
	}
}
