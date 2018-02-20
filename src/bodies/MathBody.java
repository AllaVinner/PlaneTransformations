package bodies;

import javafx.scene.canvas.GraphicsContext;

public abstract class MathBody {
	protected int size;	// Number of elements in the Body
	protected String name; // Name of the body (Arbitrary)
	protected Point [] points; // Array of all the elements in the Body
	
	/**
	 * Transform all the point in the class according to the th argument.
	 * @param th Transformation descriptions.
	 */
	public abstract void transform(TransformHandler th);
	
	public abstract MathBody copy();	
	
/**
 * Draw the MathBody on the screen
 * @param gc
 * @param scale Zoom of the screen
 * @param x0 Origin position on the screen (x)
 * @param y0 Origin position on the screen (y)
 */
	public abstract void draw(GraphicsContext gc, double scale, double x0, double y0) ;
	
	/**
	 * Converts coordinates (p.x, p.y) to screen coordinates.
	 * @param p Point to be converted
	 * @param scale The zoom of the screen
	 * @param x0 Origin position on the screen (x)
	 * @param y0 Origin position on the screen (y)
	 * @return Screen coordinates of the point
	 */
	public Point worldToScene(Point p, double scale, double x0, double y0) {
		Point temp = new Point(0,0);
		temp.setX(x0+p.getX()*scale);
		temp.setY(y0-p.getY()*scale);
		return temp;
	}
	
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
	
	public int getSize() {
		return size;
	}

	/**
	 * Returns the array of all elements in the MathBody
	 * @return
	 */
	public Point[] getArray() {
		return points;
	}
	
	/**
	 * Returns the name of the MathBody
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
}
