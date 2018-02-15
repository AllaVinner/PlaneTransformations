package bodies;

public class TransformBase {
	
	public static TransformHandler I() {
		return (p -> p);
	}

	public static TransformHandler expC() {
		return p -> {
			double x = p.getX();
			double y = p.getY();
			p.setX(Math.exp(x)*Math.cos(y));
			p.setY(Math.exp(x)*Math.sin(y));
			return p;
		};
	}
	
	public static TransformHandler divideC() {
		return p -> {
			double x = p.getX();
			double y = p.getY();
			p.setX(x/(x*x+y*y+0.01));
			p.setY(-y/(x*x+y*y+0.01));
			return p;
		};
	}
	
	public static TransformHandler rotM(double angle) {
		return p -> {
			double x = p.getX();
			double y = p.getY();
			p.setX(Math.cos(angle)*x-Math.sin(angle)*y);
			p.setY(Math.sin(angle)*x + Math.cos(angle)*y);
			return p;
		};
	}
	
	public static TransformHandler scaleM(double scale) {
		return p -> {
			double x = p.getX();
			double y = p.getY();
			p.setX(scale*x);
			p.setY(scale*y);
			return p;
		};
	}
	
	public static TransformHandler matris(double M [][]) {
		return p -> {
			double x = p.getX();
			double y = p.getY();
			p.setX(M[0][0]*x+M[0][1]*y);
			p.setY(M[1][0]*x+M[1][1]*y);
			return p;
		};
	}
	
	public static TransformHandler squareC() {
		return p -> {
			double x = p.getX();
			double y = p.getY();
			p.setX((x*x-y*y));
			p.setY(2*x*y);
			return p;
		};
	}
	
	
	
}
