package bodies;

import javafx.scene.canvas.GraphicsContext;

public class Grid extends MathBody {
	private Point [] points;
	private int lengthCurve; // Number of points in each curve
	private int numCurves; // Allways even
	
	public Grid(double spacing, double ds, int numLines, double x0, double y0) {
		this.numCurves = numLines*2;
		this.lengthCurve =(int) ((spacing* (numLines-1))/ds); // -1: To stop lines from exceding the grid
		this.size = numCurves*lengthCurve;
		points = new Point[size];
		double x;
		double y;
		for(int i = 0; i < numCurves / 2; i ++) {
			for(int j = 0; j < lengthCurve; j++) {
				x = x0 - numCurves/4*spacing+spacing*i;
				y = y0 + numCurves/4*spacing - ds*j;
				points[i*lengthCurve+j] = new Point(x,y);
			}
		}
		for(int i = 0; i < numCurves / 2; i ++) {
			for(int j = 0; j < lengthCurve; j++) {
				x = x0 - numCurves/4*spacing+ds*j;
				y = y0 + numCurves/4*spacing - spacing*i;
				points[numCurves/2*lengthCurve+i*lengthCurve+j] = new Point(x,y);
			}
		}
	}
	
	
	public Grid(Point [] points, int lengthCurve, int numCurves) {
		this.points = points;
		this.lengthCurve = lengthCurve;
		this.numCurves = numCurves;
		size =points.length;
	}

	
	@Override
	public void transform(TransformHandler th) {
		for(Point p : points) {
			p.transform(th);
		}
	}


	@Override
	public void draw(GraphicsContext gc, double scale, double x0, double y0) {
		Point p1;
		Point p2;
		for(int i = 0; i < this.numCurves; i ++) {
			for(int j = 0; j < this.lengthCurve-1; j ++) {
				p1 = worldToScene(points [i*lengthCurve + j], scale, x0,y0);
				p2 = worldToScene(points [i*lengthCurve + j+1], scale, x0,y0);	
				gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}
		
	}
	
	@Override
	public String toString() {
		return "MathBody: Grid ";
	}
	
	@Override 
	public Grid copy() {
		Point[] ret = new Point [points.length];
		for(int i = 0; i< ret.length; i++) {
			ret[i] = points[i].copy();
		}
		return new Grid(ret, this.lengthCurve, this.numCurves);
	}

	@Override
	public Point[] getArray() {
		return points;
	}

}
