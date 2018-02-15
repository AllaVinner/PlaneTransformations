package test;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	public static void display(String title, String messege) {
		Stage primeStage = new  Stage();
		
		primeStage.initModality(Modality.APPLICATION_MODAL); // Du måste ta hand om denna ruta först
		primeStage.setTitle(title);
		 Label lab = new Label(messege);
		 Button close = new Button("Close");
		 close.setOnAction(a -> primeStage.close());
		 Button change = new Button("Change In the previous Stage");
		 change.setOnAction(e -> {
			 
		 });
		 VBox box = new VBox(10);
		 box.getChildren().addAll(lab,close, change);
		 box.setAlignment(Pos.CENTER);
		 
		 Scene s = new Scene(box);
		 primeStage.setScene(s);
		 primeStage.showAndWait();
	}
}
