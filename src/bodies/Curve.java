package bodies;



import javafx.scene.canvas.GraphicsContext;

public class Curve extends MathBody{
	Point [] points;	
	
	public Curve(int [] xList, int [] yList){
		points  = new Point [xList.length];
		this.size = points.length;
		for(int i = 0; i < this.size; i++) {
			points [i] = new Point(xList[i], yList[i]);
		}
		size = points.length;
	}
	
	public Curve(double x0, double dx, double xs, FunctionHandler f) {
		points = new Point[1+(int)((xs-x0)/dx)];
		this.size = points.length;
		double x;
		for(int i = 0; i < this.size; i++) {
			x = x0+i*dx;
			points [i] = new Point(x, f.function(x));
		}
	}
	
	public Curve(Point x0, double ds, Point xs) {
		double deltaX = xs.getX() - x0.getX();
		double deltaY = xs.getY() - x0.getY();
		double norm = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		points = new Point[1+(int)((norm)/ds)];
		this.size = points.length;
		double gradient = deltaY/deltaX;
		double dx = ds/(Math.sqrt(1+gradient*gradient));
		double dy = gradient*dx;
		
		for(int i = 0; i < this.size; i++) {
			points [i] = new Point(x0.getX()+i*dx, x0.getY()+i*dy);
		}
	}
	
	public Curve(Point [] pList) {
		points = pList;
		this.size = points.length;
	}

	
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
	public String toString() {
		return ("MathBody: Curve");
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

	@Override
	public Point[] getArray() {
		return points;
	}



}
