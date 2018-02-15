package bodies;

import javafx.scene.canvas.GraphicsContext;

public abstract class MathBody {
	protected int size;
	
	/**
	 * Transform all the point in the class according to the th argument.
	 * @param th Transformation descriptions.
	 */
	public abstract void transform(TransformHandler th);
	
	/**
	 * Creates a copy of this and transforms it with th and returns it.
	 * @param th is the transform that will be applied to the copy.
	 * @return A copy of the transformed version of the object
	 */
	public MathBody getTransform(TransformHandler th) {
		MathBody ret = this.copy();
		ret.transform(th);
		return ret;
	}
	
	
	/**
	 * Returns a string with a small description of the object.
	 */
	public abstract String toString();
	
	public int getSize() {
		return size;
	}
	
	public abstract MathBody copy();
	
	public abstract Point[] getArray();
	
	public abstract void draw(GraphicsContext gc, double scale, double x0, double y0) ;
	
	public Point worldToScene(Point p, double scale, double x0, double y0) {
		Point temp = new Point(0,0);
		temp.setX(x0+p.getX()*scale);
		temp.setY(y0-p.getY()*scale);
		return temp;
	}
	
}
