package bodies;



import javafx.scene.canvas.GraphicsContext;

public class Curve extends MathBody{
	/**
	 * A set of points with a draw function defined on it to represent curves in 2-D space.
	 */
	
	/**
	 * @param xList Array of the x-coordinates in the created curve.
	 * @param yList Array of the y-coordinates in the created curve.
	 */
	public Curve(int [] xList, int [] yList){
		points  = new Point [xList.length];
		this.size = points.length;
		for(int i = 0; i < this.size; i++) {
			points [i] = new Point(xList[i], yList[i]);
		}
		size = points.length;
	}
	
	/**
	 * @param x0 Starting point
	 * @param ds spacing
	 * @param xs End point
	 */
	public Curve(Point x0, double ds, Point xs) {
		double deltaX = xs.getX() - x0.getX();
		double deltaY = xs.getY() - x0.getY();
		double norm = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		points = new Point[1+(int)((norm)/ds)];
		this.size = points.length;
		Point gradient = new Point( ds*deltaX/norm,ds*deltaY/norm);
		for(int i = 0; i < this.size; i++) {
			points [i] = new Point(x0.getX()+i*gradient.getX(), x0.getY()+i*gradient.getY());
		}
	}
	
	/**
	 * @param pList Array of points
	 */
	public Curve(Point [] pList) {
		points = pList;
		this.size = points.length;
	}
	
	/**
	 * Transforms the MathBody according to th
	 */
	public void transform(TransformHandler th) {
		for(Point p : points) {
			p = th.transform(p);
		}
	}

	@Override
	public Curve copy() {
		Point [] list = new Point [points.length];
		for(int i = 0; i < list.length; i++) {
			list [i] = points[i].copy();
		}
		return new Curve(list);
	}
	
	@Override
	public void draw(GraphicsContext gc, double scale, double x0, double y0) {
		Point p1;
		Point p2;
		for(int i = 0; i < size-1; i ++) {
			p1 = worldToScene(points [i], scale, x0,y0);
			p2 = worldToScene(points [i+1], scale, x0,y0);
			gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
		
	}



}
