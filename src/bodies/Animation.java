package bodies;


import javafx.scene.canvas.GraphicsContext;

public class Animation {
	private MathBody domain;
	private MathBody image;
	private MathBody current;
	
	
	public Animation(MathBody mb, TransformHandler th) {
		domain = mb;
		image = mb.copy();
		image.transform(th);
		current = domain.copy();
	}
	
	public void domainTransform(TransformHandler th) {
		image = domain.getTransform(th);
	}
	
	public void imageTransform(TransformHandler th) {
		domain = image.copy();
		image = domain.getTransform(th);
	}
	
	
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
