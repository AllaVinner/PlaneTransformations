package bodies;

import javafx.scene.canvas.GraphicsContext;

public class Point extends MathBody{
	private double x;
	private double y;
		
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.size = 1;
	}
	
	@Override
	public String toString() {
		return ("MathBody: Point");
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void transform(TransformHandler th) {
		Point p = th.transform(this);
		setX(p.getX());
		setY(p.getY());
	}

	@Override
	public void draw(GraphicsContext gc, double scale, double x0, double y0) {
		Point p = worldToScene(this, scale, x0 , y0);
		gc.fillOval(p.getX(), p.getY(), 5, 5);
		
	}
	
	@Override
	public Point copy() {
		return new Point(x,y);
	}

	@Override
	public Point[] getArray() {
		Point [] ret = {this};
		return ret;
	}

}
