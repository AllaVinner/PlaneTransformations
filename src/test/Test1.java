package test;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Test1 extends Application implements EventHandler<ActionEvent>{
	Button button;
	public static void main(String[] args) {
		launch(args);	// Sätter upp allt som en java application

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Hela grejen kallas stage
		// De innuti är scene
		primaryStage.setTitle("Title of the Stage");
		Button b2 = new Button("Go Back");
		Label lab = new Label("Welcome here");
		StackPane layout = new StackPane();
		VBox lay2 = new VBox(20);
		lay2.getChildren().addAll(b2, lab);
		Scene scene1 = new Scene(layout, 300, 250);
		Scene scene2 = new Scene(lay2, 700, 800);
		button = new Button();
		button.setText("Heeeey");
		button.setOnAction(  a -> {
			primaryStage.setScene(scene2);
	});
		
		b2.setOnAction(a -> {
		AlertBox.display("Yeaah","asdasdsad");
		});
		
		Line l = new Line(50,50,100,100);
		
	
		
		layout.getChildren().add(button);


		primaryStage.setScene(scene1);
		primaryStage.show();	
	}

}
