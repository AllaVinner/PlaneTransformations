package screen;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateBodyWindow {
	
	public static void display() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL); 
		
		Pane pane = new Pane();
		Scene scene = new Scene(pane,400,400);
		
		
		stage.setScene(scene);
		stage.show();
	}

}
