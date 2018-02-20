package screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bodies.Animation;
import bodies.Curve;
import bodies.MathBody;
import bodies.TransformBase;
import bodies.TransformHandler;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MidPane {
	
	// Screen setup
	private Group root;
	private Canvas canvas;
	private GraphicsContext graphic;
	
	private Slider scaler;
	private Slider time;
	
	private Color backgroundColor;
	private double scale;
	
	
	// World setup
	private Map<MathBody, DisplayVariables> fixedObjects;
	private Map<Animation, DisplayVariables> animationObjects;
	

	public MidPane() {
		root = new Group();
		canvas = new Canvas(1300,700);
		 graphic  = canvas.getGraphicsContext2D();

		 backgroundColor = new Color(0,0,0, 1);
		 drawBackground();
		 scale = 40; 
		 
		 fixedObjects = new HashMap<MathBody, DisplayVariables>();
		animationObjects = new HashMap<Animation, DisplayVariables>();
			

		 VBox vBox = new VBox();
		 scaler = new Slider(1, 350, scale);
		 scaler.valueProperty().addListener((observable, oldValue, newValue) -> {
			   scale = newValue.intValue();
			   draw();
			});
		 
		 time = new Slider(0,1, 0);
		 time.valueProperty().addListener((observable, oldValue, newValue) -> {
			   draw();
			});
		 vBox.getChildren().addAll(scaler, time);
		root.getChildren().addAll(canvas,vBox);
	}
	
	public Group getGroup() {
		return this.root;
	}
	
	public void addFixedBody(MathBody mb, DisplayVariables dv) {
		fixedObjects.put(mb, dv);
		draw();
	}
	
	public void addTransformableBody(MathBody mb, DisplayVariables dv) {
		animationObjects.put(new Animation(mb, TransformBase.I()), dv);
		draw();
	}
	
	/**
	 * Draw all the fixed and animations bodies
	 */
	public void draw() {
		 drawBackground();
		for (Map.Entry<MathBody, DisplayVariables> entry : fixedObjects.entrySet()) {
			entry.getValue().setGraphic(graphic);
			entry.getKey().draw(graphic, scale, canvas.getWidth()/2, canvas.getHeight()/2); 
		}
		for (Map.Entry<Animation, DisplayVariables> entry : animationObjects.entrySet()) {
			entry.getValue().setGraphic(graphic);
			entry.getKey().draw(graphic, scale, canvas.getWidth()/2, canvas.getHeight()/2, time.getValue());
		}
	}
	
	/**
	 * Transforms all the bodies from their original state.
	 * @param T Transform
	 */
	public void domainTransform(TransformHandler T) {
		for (Map.Entry<Animation, DisplayVariables> entry : animationObjects.entrySet()) {
			entry.getKey().domainTransform(T);
		}
		time.setValue(0);
		draw();
	}
	
	/**
	 * Transforms all the bodies from their transformed state.
	 * @param T Transform
	 */
	public void imageTransform(TransformHandler T) {
	for (Map.Entry<Animation, DisplayVariables> entry : animationObjects.entrySet()) {
		entry.getKey().imageTransform(T);
	}
	time.setValue(0);
	draw();
}

	public void clearAll() {
		fixedObjects.clear();
		animationObjects.clear();
		draw();
	}
	
	public void clearFixed() {
		fixedObjects.clear();
		draw();
	}
	
	public void clearTransformable() {
		animationObjects.clear();
		draw();
	}
	
	private void drawBackground() {
		 graphic.setFill(backgroundColor);
		 graphic.fillRect(0, 0, canvas.getWidth(), canvas.getHeight()); 
	}

}
