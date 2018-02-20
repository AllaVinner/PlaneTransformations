package bodies;

import javafx.scene.canvas.GraphicsContext;

public class Grid extends MathBody {
	/**
	 * Represents the mathematical notion of a grid. 
	 */
	private int lengthCurve; // Number of points in each curve
	private int numCurves; // Total number of curves (Always even)
	
	/**
	 * 
	 * @param spacing between each line in the grid.
	 * @param ds spacing within a line
	 * @param numLines number of vertical lines in the grid (= number of horizontal)
	 * @param x0 x-coordinate of the middle of the grid.
	 * @param y0 y-coordinate of the middle of the grid.
	 */
	public Grid(double spacing, double ds, int numLines, double x0, double y0) {
		this.numCurves = numLines*2;
		this.lengthCurve =(int) ((spacing* (numLines-1))/ds); // -1: To stop lines from exceeding the grid
		this.size = numCurves*lengthCurve;
		points = new Point[size];
		
		double x;
		double y;
		//Set all the vertical lines. Top to , left to right
		for(int i = 0; i < numCurves / 2; i ++) {
			for(int j = 0; j < lengthCurve; j++) {
				x = x0 - numCurves/4*spacing+spacing*i;
				y = y0 + numCurves/4*spacing - ds*j;
				points[i*lengthCurve+j] = new Point(x,y);
			}
		}
		// Set all horizontal lines. Left to right, top to bottom
		for(int i = 0; i < numCurves / 2; i ++) {
			for(int j = 0; j < lengthCurve; j++) {
				x = x0 - numCurves/4*spacing+ds*j;
				y = y0 + numCurves/4*spacing - spacing*i;
				points[numCurves/2*lengthCurve+i*lengthCurve+j] = new Point(x,y);
			}
		}
		name = "Grid:";
	}
	
	/**
	 * The first parameter yields the set of all the elements. 
	 * The second two parameters explain which element belongs to which line.
	 * @param points Array of all points in the grid
	 * @param lengthCurve Number of elements in each curve
	 * @param numCurves Total number of curves.
	 */
	public Grid(Point [] points, int lengthCurve, int numCurves) {
		this.points = points;
		this.lengthCurve = lengthCurve;
		this.numCurves = numCurves;
		size =points.length;
		name = "Grid: ";
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
	public Grid copy() {
		Point[] ret = new Point [points.length];
		for(int i = 0; i< ret.length; i++) {
			ret[i] = points[i].copy();
		}
		return new Grid(ret, this.lengthCurve, this.numCurves);
	}

}
