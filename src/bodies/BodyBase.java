package bodies;

public class BodyBase {
	
	public static MathBody unitCircle() {
			Curve circle = new Curve(new Point(0,0), 0.01, new Point(2*Math.PI,0));
			circle.transform(p -> {
				p.setPoint(Math.cos(p.getX()), Math.sin(p.getX()));
				return p;
			});
			return circle;
	}
}
