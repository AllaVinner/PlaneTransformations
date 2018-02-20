package screen;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import bodies.BodyBase;
import bodies.Grid;
import bodies.MathBody;
import bodies.TransformBase;
import bodies.TransformHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Runs the program (Plane Transforms)
	 */
	
		private MidPane midPane;
		private MenuBar menu;
		
	public static void main(String[] args) {
		 setUserAgentStylesheet(STYLESHEET_MODENA);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane boardPane =new BorderPane();
		midPane = new MidPane();	
		boardPane.setCenter(midPane.getGroup());
		menu = new MenuBar();

		
		addTransforms();
		addBodies();
		addClear();
		boardPane.setTop(menu);
		

		Scene sc = new Scene(boardPane,800,600);	
		primaryStage.setScene(sc);
		primaryStage.setFullScreen(true);
		primaryStage.setTitle("Plane Transforms");
		primaryStage.show();	
	}
	
	/**
	 * Add menuItems for all the common bodies
	 */
	private void addBodies() {
		Menu addBody = new Menu("Add Body");
		Menu addFixed = new Menu("Fixed") ;
	
			MenuItem stdGrid = new MenuItem("Standard Grid");
				stdGrid.setOnAction(e ->{
					midPane.addFixedBody(new Grid(1, 0.1, 21,0,0 ),  DisplayVarialbleBase.white(1) );
					midPane.addFixedBody(new Grid(1, 0.1, 20,0.5, -0.5 ), DisplayVarialbleBase.grey(0.5)  );
				});
			MenuItem piGrid = new MenuItem("PI Grid");
				piGrid.setOnAction(e ->{
					midPane.addFixedBody(new Grid(Math.PI/2, 0.1, 17,0,0 ),  DisplayVarialbleBase.white(1));
					midPane.addFixedBody(new Grid(Math.PI/2, 0.1, 16,Math.PI/4, -Math.PI/4), DisplayVarialbleBase.grey(0.5) );
				});					
				MenuItem smallGrid = new MenuItem("Small Grid");
				smallGrid.setOnAction(e ->{
					midPane.addFixedBody(new Grid(0.1, 0.01, 21,0,0 ),  DisplayVarialbleBase.white(1));
					midPane.addFixedBody(new Grid(0.1, 0.01, 20,0.05, -0.05 ), DisplayVarialbleBase.white(0.5));
				});
				addFixed.getItems().addAll(stdGrid, piGrid, smallGrid);
				
					
			
	
		
		Menu addTransformable= new Menu("Transformable") ;
		MenuItem stdGridT = new MenuItem("Standard Grid");
			stdGridT.setOnAction(e ->{
				midPane.addTransformableBody(new Grid(1, 0.1, 21,0,0 ), DisplayVarialbleBase.blue(1.5) );
				midPane.addTransformableBody(new Grid(1, 0.1, 20,0.5, -0.5 ), DisplayVarialbleBase.green(1) );
			});
		MenuItem piGridT = new MenuItem("PI Grid");
			piGridT.setOnAction(e ->{
				midPane.addTransformableBody(new Grid(Math.PI/2, 0.1, 17,0,0 ),  DisplayVarialbleBase.blue(1.5) );
				midPane.addTransformableBody(new Grid(Math.PI/2, 0.1, 16, Math.PI/4, -Math.PI/4), DisplayVarialbleBase.green(1) );
			});
		
		MenuItem smallGridT = new MenuItem("Small Grid");
			smallGridT.setOnAction(e ->{
				midPane.addTransformableBody(new Grid(0.1, 0.01, 21,0,0 ), DisplayVarialbleBase.blue(1.5)  );
				midPane.addTransformableBody(new Grid(0.1, 0.01, 20,0.05,-0.05 ), DisplayVarialbleBase.green(1)  );
			});
			
		MenuItem bigGridT = new MenuItem("Big Grid");
			bigGridT.setOnAction(e ->{
				midPane.addTransformableBody(new Grid(0.2, 0.02, 51,0,0 ),DisplayVarialbleBase.blue(1.5)   );
				midPane.addTransformableBody(new Grid(0.2, 0.02, 50,0.1,-0.1 ),DisplayVarialbleBase.green(1) );
			});
			
	addTransformable.getItems().addAll(stdGridT, piGridT, smallGridT, bigGridT);
	LinkedHashMap< MathBody, DisplayVariables> mapBodies = new LinkedHashMap<MathBody, DisplayVariables>();
	mapBodies.put(BodyBase.unitCircle(), DisplayVarialbleBase.red(1.5) );
	mapBodies.put(BodyBase.xAxis(), DisplayVarialbleBase.white(2) );
	mapBodies.put(BodyBase.yAxis(), DisplayVarialbleBase.white(2) );
	mapBodies.put(BodyBase.randomPoints(), DisplayVarialbleBase.red(2) );
	
	MenuItem tempMenu;
	for(Map.Entry<MathBody, DisplayVariables> entry : mapBodies.entrySet()) {
		tempMenu = new MenuItem(entry.getKey().toString());
		tempMenu.setOnAction(e -> midPane.addFixedBody(entry.getKey(), entry.getValue()));
		addFixed.getItems().add(tempMenu);
		
		tempMenu = new MenuItem(entry.getKey().toString());
		tempMenu.setOnAction(e -> midPane.addTransformableBody(entry.getKey(), entry.getValue()));
		addTransformable.getItems().add(tempMenu);
	}	
	
	addBody.getItems().addAll(addFixed, addTransformable);
		menu.getMenus().addAll(addBody);
	}
	
	/**
	 * Add menuItems for all the common transforms
	 */
	private void addTransforms() {
		 Menu setTransform = new Menu("Transform");
		Menu domain = new Menu("Domain");
		Menu  image = new Menu("Image");
		HashMap<String, TransformHandler> arrayTransforms = new HashMap<String, TransformHandler>();
		arrayTransforms.put("exp(z)", TransformBase.expC());
		arrayTransforms.put("1/z", TransformBase.divideC());
		arrayTransforms.put("z^2", TransformBase.squareC());
		arrayTransforms.put("rot(pi/3)", TransformBase.rotM(Math.PI/3));
		arrayTransforms.put("Share", TransformBase.matris(1, 1,0,1));
		double v = 0.5;
		double g = 1/(Math.sqrt(1-v*v));
		arrayTransforms.put("Lorentz, v=0.5", TransformBase.matris(g, -v*g, -v*g,g));
		
		MenuItem tempMenu;
		for(Map.Entry<String, TransformHandler> entry : arrayTransforms.entrySet()) {
			tempMenu = new MenuItem(entry.getKey());
			tempMenu.setOnAction(e -> midPane.domainTransform(entry.getValue()));
			domain.getItems().add(tempMenu);
			tempMenu = new MenuItem(entry.getKey());
			tempMenu.setOnAction(e -> midPane.imageTransform(entry.getValue()));
			image.getItems().add(tempMenu);
		}				
		setTransform.getItems().addAll(domain, image);
		menu.getMenus().add(setTransform);
	}

	/**
	 * Add menuItems for different clear functions
	 */
	private void addClear() {
		Menu clear = new Menu("Clear");
		MenuItem clearFixed = new MenuItem("Clear Fixed");
			clearFixed.setOnAction(e -> midPane.clearFixed());
		MenuItem clearTransformable = new MenuItem("Clear Transformable");
			clearTransformable.setOnAction(e -> midPane.clearTransformable());
		MenuItem clearAll = new MenuItem("Clear All");
			clearAll.setOnAction(e -> midPane.clearAll());
	clear.getItems().addAll(clearAll, clearFixed, clearTransformable);
	
	Menu create= new Menu("Add Costume");
		MenuItem createBody = new MenuItem("Creat Item");
		createBody.setOnAction(a -> CreateBodyWindow.display());
	create.getItems().addAll(createBody);
	menu.getMenus().add(clear);
	}
}

