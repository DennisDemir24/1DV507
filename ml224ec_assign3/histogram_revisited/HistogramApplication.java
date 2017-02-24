package ml224ec_assign3.histogram_revisited;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HistogramApplication extends Application {
	
	Stage currentStage;
	
	GridPane toolbar;
	TextField fileField;
	Button browseButton;
	Button reloadButton;
	Label presentationInfo;
	
	HistogramPresentation presentation;
	
	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		currentStage = arg0;
		
		BorderPane root = new BorderPane();
		
		toolbar = new GridPane();
		fileField = new TextField();
		browseButton = new Button("Browse");
		reloadButton = new Button("Reload");
		presentationInfo = new Label("");
		
		fileField.setMinWidth(250);
		fileField.setPrefWidth(500);
		
		browseButton.setOnAction(e -> onActionBrowseButton(e));
		reloadButton.setOnAction(e -> onActionReloadButton(e));
		
		toolbar.add(fileField, 0, 0);
		toolbar.add(browseButton, 1, 0);
		toolbar.add(reloadButton, 2, 0);
		toolbar.add(presentationInfo, 3, 0);
		toolbar.setHgap(10);
		toolbar.setPadding(new Insets(5, 10, 5, 10));
		
		root.setTop(toolbar);
		
		presentation = new HistogramPresentation();
		
		root.setCenter(presentation);
		
		Scene scene = new Scene(root);
		
		currentStage.setScene(scene);
		
		currentStage.sizeToScene();
		currentStage.setResizable(false);
		
		currentStage.show();
		
	}

	private void onActionBrowseButton(ActionEvent e)
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Open integer data file");
		String path = fc.showOpenDialog(currentStage).getAbsolutePath();
		fileField.setText(path);
		
		onActionReloadButton(e); // reload the data via proxy
	}
	
	private void onActionReloadButton(ActionEvent e)
	{
		try {
			presentation.updateDataFrom(fileField.getText());
			presentationInfo.setText(String.format("Total integers read: %d | Displaying %d interval%s (%d culled)",
					presentation.getTotalReadIntegers(),
					presentation.getDisplayedIntervals(),
					(presentation.getDisplayedIntervals() == 1 ? "" : "s"),
					presentation.getCulledIntervals()));
		} catch (Exception ex) {
			new LazyErrorPrompt(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
