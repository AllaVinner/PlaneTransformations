package test;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Board extends Application {
	

	public static void main(String[] args) {
		Application.launch(args);
}

	@Override
	public void start(Stage board) throws Exception {
		BorderPane ground1 = new BorderPane();
		Scene scene1 = new Scene(ground1);
		board.setScene(scene1);
		board.show();
		
		Button but1 = new Button("Button 1");
		but1.setOnAction(a->{
			board.setTitle("Button 1 was pressed");
		});
		HBox hbox = new HBox(but1);
		
		ground1.setBottom(hbox);
		
	}
}
