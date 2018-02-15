package bodies;

public interface AnimationHandler {
	default Point anmation(Point domain, Point image, double t) {
		double x0 =  domain.getX();
		double x1 = image.getX();
		double y0 = domain.getY();
		double y1 = image.getY();
		return new Point(x0 -(x1-x0)*t, y0 -(y1-y0)*t );
	};
}
