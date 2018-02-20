package bodies;


import javafx.scene.canvas.GraphicsContext;

public class Animation {
	
	/**
	 * An instance of this class is constructed from a MathBody and a TransformHandler.
	 * This class keep track of the original body and the transformed version.
	 * It can then return a body in between the the original and transformed version (see the method draw) 
	 */
	
	private MathBody domain;	// Original body
	private MathBody image;	// transformed body
	private MathBody current;	//variable (in between body)
	
	
	public Animation(MathBody mb, TransformHandler th) {
		domain = mb;
		image = mb.copy();
		image.transform(th);
		current = domain.copy();
	}
	
	/**
	 * Redefines a transform on the original body.
	 * @param th Transformed to be performed
	 */
	public void domainTransform(TransformHandler th) {
		image = domain.getTransform(th);
	}
	
	/**
	 * Defines a transform on the already transformed body.
	 * @param th Transformed to be performed
	 */
	public void imageTransform(TransformHandler th) {
		domain = image.copy();
		image = domain.getTransform(th);
	}
	
	/**
	 * Draw the current body at time t.
	 * @param gc Graphics
	 * @param scale	- the relation between the appeared size of body and the window.
	 * @param x0 - X-window coordinate of the middle.
	 * @param y0- Y-window coordinate of the middle.
	 * @param t - How far gone is the transform? t in [0,1]
	 */
	public void draw(GraphicsContext gc, double scale, double x0, double y0, double t) {
		double xd;
		double xi;
		double yd;
		double yi;
		
		for(int i = 0; i < domain.getArray().length; i++) {
			xd = domain.getArray()[i].getX();
			xi = image.getArray()[i].getX();
			yd = domain.getArray()[i].getY(); 
			yi = image.getArray()[i].getY();
			 current.getArray()[i].setPoint(xd + (xi-xd)*t, yd + (yi-yd)*((1-Math.cos(Math.PI*t))/2));	
		}
		current.draw(gc, scale, x0, y0);
	}
}
