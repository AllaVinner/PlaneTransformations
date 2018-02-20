package bodies;

public class BodyBase {
	
	/**
	 * List of static function which return common MathBody:s
	 */
	
	public static MathBody unitCircle() {
			Curve circle = new Curve(new Point(0,0), 0.01, new Point(2*Math.PI,0));
			circle.transform(p -> {
				p.setPoint(Math.cos(p.getX()), Math.sin(p.getX()));
				return p;
			});
			circle.setName("Unit Circle");
			return circle;
	}
	
	public static MathBody xAxis() {
		Curve axis= new Curve(new Point(-10,0), 0.1, new Point(10,0));
		axis.setName("X-axis");
		return axis;
	}
	
	public static MathBody yAxis() {
		Curve axis = new Curve(new Point(0,-10), 0.1, new Point(0, 10));
		axis.setName("Y-axis");
		return axis;
	}
	
	public static MathBody randomPoints() {
		Point p = new Point(0,1);
		return p;
	}
	
}
