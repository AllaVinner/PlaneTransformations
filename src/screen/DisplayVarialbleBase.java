package screen;

import javafx.scene.paint.Color;

public class DisplayVarialbleBase {
	/**
	 * List of  static methods which return common DisplayVariables
	 */
	
	public static DisplayVariables blue(double width) {
		DisplayVariables ret = new DisplayVariables();
		ret.fill = Color.color(0.2, 0.2, 0.7, 0.7);
		ret.stroke = Color.color(0.2, 0.2, 0.7, 0.7);
		ret.width = width;
		return ret;
	}
	
	public static DisplayVariables green(double width) {
		DisplayVariables ret = new DisplayVariables();
		ret.fill = Color.color(0.2, 0.7, 0.2, 0.7);
		ret.stroke = Color.color(0.2, 0.7, 0.2, 0.7);
		ret.width = width;
		return ret;
	}
	
	public static DisplayVariables red(double width) {
		DisplayVariables ret = new DisplayVariables();
		ret.fill = Color.color(0.7, 0, 0.1, 0.7);
		ret.stroke = Color.color(0.7, 0, 0.1, 0.7);
		ret.width = width;
		return ret;
	}
	
	public static DisplayVariables grey(double width) {
		DisplayVariables ret = new DisplayVariables();
		ret.fill = Color.color(0.3, 0.3, 0.3, 0.7);
		ret.stroke = Color.color(0.2, 0.2, 0.2, 0.7);
		ret.width = width;
		return ret;
	}
	
	public static DisplayVariables white(double width) {
		DisplayVariables ret = new DisplayVariables();
		ret.fill = Color.color(1,1,1, 0.7);
		ret.stroke = Color.color(1,1,1, 0.7);
		ret.width = width;
		return ret;
	}
	
}
