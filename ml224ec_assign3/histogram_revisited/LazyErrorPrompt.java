package ml224ec_assign3.histogram_revisited;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Why litter your code with like five lines of code you only need for one time?
 * When you can make an easy lazy instantiation of it for your one-time usage, saving you time?
 * @author Martin Lyrå
 *
 */
public class LazyErrorPrompt {

	public LazyErrorPrompt(String message)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Input Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
}
