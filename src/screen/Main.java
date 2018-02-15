package screen;

import bodies.BodyBase;
import bodies.Grid;
import bodies.TransformBase;
import bodies.TransformHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
		
		
	public static void main(String[] args) {
		 setUserAgentStylesheet(STYLESHEET_MODENA);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane boardPane =new BorderPane();
		MidPane midPane = new MidPane();	
		boardPane.setCenter(midPane.getGroup());
		
		MenuBar menu = new MenuBar();
		
		Menu setTransform = new Menu("Transform");
			
				Menu domain = new Menu("Domain");
					MenuItem expD = new MenuItem("exp(z)");
					expD.setOnAction(e -> midPane.domainTransform(TransformBase.expC()) );
					MenuItem divideD = new MenuItem("1/z");
					divideD.setOnAction(e -> midPane.domainTransform(TransformBase.divideC()) );
					MenuItem squareD = new MenuItem("z^2");
					squareD.setOnAction(e -> midPane.domainTransform(TransformBase.squareC()) );
				domain.getItems().addAll(expD, divideD, squareD);
				
				Menu  image = new Menu("Image");
					MenuItem expI = new MenuItem("exp(z)");
					expI.setOnAction(e -> midPane.imageTransform(TransformBase.expC()) );
					MenuItem divideI = new MenuItem("1/z");
					divideI.setOnAction(e -> midPane.imageTransform(TransformBase.divideC()) );
					MenuItem squareI = new MenuItem("z^2");
					squareI.setOnAction(e -> midPane.imageTransform(TransformBase.squareC()) );
			image.getItems().addAll(expI,divideI, squareI);
				
			setTransform.getItems().addAll(domain, image);
		
		Menu addBody = new Menu("Add Body");
			Menu addFixed = new Menu("Fixed") ;
				MenuItem stdGrid = new MenuItem("Standard Grid");
					stdGrid.setOnAction(e ->{
						midPane.addFixedBody(new Grid(1, 0.1, 21,0,0 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.CORNFLOWERBLUE, 1.5) );
						midPane.addFixedBody(new Grid(1, 0.1, 20,0.5, -0.5 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.LIGHTGREEN, 1) );
					});
				MenuItem piGrid = new MenuItem("PI Grid");
					piGrid.setOnAction(e ->{
						midPane.addFixedBody(new Grid(Math.PI/2, 0.1, 17,0,0 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.CORNFLOWERBLUE, 1.5) );
						midPane.addFixedBody(new Grid(Math.PI/2, 0.1, 16,Math.PI/4, -Math.PI/4), new DisplayVariables(Color.CORNFLOWERBLUE, Color.LIGHTGREEN, 1) );
					});
				MenuItem unitCircle = new MenuItem("Unit Circle");
					unitCircle.setOnAction(e -> {
						midPane.addFixedBody(BodyBase.unitCircle(), new DisplayVariables(Color.ORANGERED, Color.ORANGERED, 2.5));
					});
				MenuItem smallGridF = new MenuItem("Small Grid");
					smallGridF.setOnAction(e ->{
						midPane.addFixedBody(new Grid(0.1, 0.01, 21,0,0 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.CORNFLOWERBLUE, 1.5) );
						midPane.addFixedBody(new Grid(0.1, 0.01, 20,0.05, -0.05 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.LIGHTGREEN, 1) );
					});
			addFixed.getItems().addAll(stdGrid, piGrid, unitCircle, smallGridF);
			
			Menu addTransformable= new Menu("Transformable") ;
			MenuItem stdGridT = new MenuItem("Standard Grid");
				stdGridT.setOnAction(e ->{
					midPane.addTransformableBody(new Grid(1, 0.1, 21,0,0 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.CORNFLOWERBLUE, 1.5) );
					midPane.addTransformableBody(new Grid(1, 0.1, 20,0.5, -0.5 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.LIGHTGREEN, 1) );
				});
			MenuItem piGridT = new MenuItem("PI Grid");
				piGridT.setOnAction(e ->{
					midPane.addTransformableBody(new Grid(Math.PI/2, 0.1, 17,0,0 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.CORNFLOWERBLUE, 1.5) );
					midPane.addTransformableBody(new Grid(Math.PI/2, 0.1, 16, Math.PI/4, -Math.PI/4), new DisplayVariables(Color.CORNFLOWERBLUE, Color.LIGHTGREEN, 1) );
				});
			MenuItem unitCircleT = new MenuItem("Unit Circle");
				unitCircleT.setOnAction(e -> {
					midPane.addTransformableBody(BodyBase.unitCircle(), new DisplayVariables(Color.ORANGERED, Color.ORANGERED, 2.5));
				});
			
			MenuItem smallGridT = new MenuItem("Small Grid");
				smallGridT.setOnAction(e ->{
					midPane.addTransformableBody(new Grid(0.1, 0.01, 21,0,0 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.CORNFLOWERBLUE, 1.5) );
					midPane.addTransformableBody(new Grid(0.1, 0.01, 20,0.05,-0.05 ), new DisplayVariables(Color.CORNFLOWERBLUE, Color.LIGHTGREEN, 1) );
				});
				
		addTransformable.getItems().addAll(stdGridT, piGridT, unitCircleT, smallGridT);
		addBody.getItems().addAll(addFixed, addTransformable);
		
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
		
		menu.getMenus().addAll(setTransform, addBody, clear, create);
		boardPane.setTop(menu);
		

		Scene sc = new Scene(boardPane,800,600);	
		primaryStage.setScene(sc);
		primaryStage.show();	
	}
}
