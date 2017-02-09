package ml224ec_assign2;

import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class RandomPanel extends BorderPane {

	private final Text label;
	private final Button button;
	
	public RandomPanel ()
	{
		HBox labelBox = new HBox();
		labelBox.setPadding(new Insets(10.0d));
		labelBox.setAlignment(Pos.CENTER);
		
		label = new Text("-");
		label.setFont(new Font(20.0d));
		label.setTextAlignment(TextAlignment.CENTER);
		
		labelBox.getChildren().add(label);

		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		
		button = new Button();
		button.setText("New Random");
		button.setOnMouseClicked( e -> label.setText(new Random().nextInt(100)+1+""));
		
		buttonBox.getChildren().add(button);
		
		this.setTop(labelBox);
		this.setBottom(buttonBox);
		RandomPanel.setAlignment(labelBox, Pos.CENTER);
		RandomPanel.setAlignment(buttonBox, Pos.CENTER);
		
		this.setPadding(new Insets(20.0f));
	}
	
}
