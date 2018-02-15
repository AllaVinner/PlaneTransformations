package bodies;

public class Tester {

	public static void main(String[] args) {
		BodyList list = new BodyList();
		
		MathBody sin = new Curve(-2, 1, 2, (x-> 0));
		list.add(sin);
		
		System.out.println(list.toString());
		list.domainTransform(k -> {
			k.setY(k.getY()-2);
			return k;
		});
		
		System.out.println("Efter transform");
		System.out.println(list.toString());
	}

}
