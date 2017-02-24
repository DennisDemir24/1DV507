package ml224ec_assign3;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class UpDownPane extends BorderPane {

	private enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT,
	}
	
	private class Position {
		int x;
		int y;
		
		public Position(int x, int y)
		{
			this.x = x; this.y = y;
		}
	}
	
	private final GridPane grid;
	private final HBox icon;
	private final Label iconText;
	
	private static final int ROWS = 7;
	private static final int COLUMNS = 7;
	
	private static final Color BACKGROUND_COLOR = Color.rgb(10, 10, 10);
	
	private Position iconPosition = new Position(
			(ROWS - ROWS%2)/2, (COLUMNS - COLUMNS%2)/2);
	
	public UpDownPane()
	{
		grid = new GridPane();
		grid.setStyle("-fx-background-color: #202020");
		
		grid.setHgap(1);
		grid.setVgap(1);
		
		iconText = new Label("@");
		iconText.setFont(new Font(30));
		iconText.setMinSize(50, 50);
		iconText.setPrefSize(50, 50);
		iconText.setTextFill(Color.web("#eee"));
		iconText.setAlignment(Pos.CENTER);
		
		icon = new HBox(50);
		//icon.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, null, null)));
		String style = String.format("-fx-background-color: #%s", BACKGROUND_COLOR.toString().substring(2));
		icon.setStyle(style);
		
		icon.getChildren().add(iconText);
		
		for( int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; j++)
			{
				if (i == iconPosition.x && j == iconPosition.y)
					grid.add(icon, i, j);
				else
				{
					Rectangle rect = new Rectangle(50,50);
					rect.setFill(BACKGROUND_COLOR);
					grid.add(rect, i, j);
				}
			}
		
		this.setTop(grid);
	}
	
	/**
	 * Direct <code>KeyEvent</code>s into this function for it to move the icon.
	 * @param event
	 */
	public void handleInput(KeyEvent event)
	{
		KeyCode code = event.getCode();
		
		switch (code)
		{
		case KP_LEFT: // numpad arrows are prefixed with KP_
		case LEFT:
		{
			move(Direction.LEFT);
			break;
		}
		case KP_RIGHT:
		case RIGHT:
		{
			move(Direction.RIGHT);
			break;
		}
		case KP_UP:
		case UP:
		{
			move(Direction.UP);
			break;
		}
		case KP_DOWN:
		case DOWN:
		{
			move(Direction.DOWN);
			break;
		}
		default:
			break;
		}
	}
	
	/**
	 * Moves the icon in given <code>direction</code>
	 * @param direction
	 */
	private void move(Direction direction)
	{
		switch (direction)
		{
		case DOWN:
		{
			if (iconPosition.y == ROWS - 1)
				iconPosition.y = 0;
			else
				iconPosition.y++;
			break;
		}
		case LEFT:
		{
			if (iconPosition.x == 0)
				iconPosition.x = COLUMNS - 1;
			else
				iconPosition.x--;
			break;
		}
		case RIGHT:
		{
			if (iconPosition.x == COLUMNS - 1)
				iconPosition.x = 0;
			else
				iconPosition.x++;
			break;
		}
		case UP:
		{
			if (iconPosition.y == 0)
				iconPosition.y = ROWS - 1;
			else
				iconPosition.y--;
			break;
		}
		default:
			break;
		}
		
		grid.getChildren().removeAll(grid.getChildren());
		
		for( int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; j++)
			{
				if (i == iconPosition.x && j == iconPosition.y)
					grid.add(icon, i, j);
				else
				{
					Rectangle rect = new Rectangle(50,50);
					rect.setFill(BACKGROUND_COLOR);
					grid.add(rect, i, j);
				}
			}
	}
}
