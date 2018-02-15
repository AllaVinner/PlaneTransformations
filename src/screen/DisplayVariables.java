package screen;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayVariables {
	Color fill;
	Color stroke;
	double width;
	
	public DisplayVariables() {
		fill = Color.WHITE;
		stroke = Color.BLACK;
		width = 2;	
	}
	public DisplayVariables(Color fill, Color stroke, double width) {
		this.fill = fill;
		this.stroke = stroke;
		this.width = width;
	}
	
	
	public void setGraphic(GraphicsContext gc) {
		gc.setFill(fill);
		gc.setStroke(stroke);
		gc.setLineWidth(width);
	}
}
