package garden_planner.gui;

import garden_planner.model.GardenPlanner;
import garden_planner.model.RectBed;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * NOTE: Do NOT run this class in IntelliJ.  Run 'RunGui' instead.
 */
public class GuiMain extends Application {

    // 1-1 association with GardenPlanner
    private GardenPlanner planner;
    private TextField widthField;

    // Constructor: set up planner and default layout
    public GuiMain(){
        planner = new GardenPlanner();
        planner.createBasicDesign();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Scale factor to convert meters to pixels
        final double SCALE = 100.0;

        //Create the main layout as a BorderPane
        BorderPane root = new BorderPane();

        //Create the garden layout area using a Pane
        Pane gardenPane = new Pane();
        root.setStyle("-fx-background-color: #007700;");
        root.setCenter(gardenPane);


        //Loop through all garden beds and add them as rectangles to the GUI
        for(RectBed bed : planner.getBeds()){
            Rectangle rect = new Rectangle(bed.getWidth() * SCALE, bed.getHeight() * SCALE);
            rect.setX(bed.getLeft() * SCALE);
            rect.setY(bed.getTop() * SCALE);
            rect.setFill(Color.SADDLEBROWN);
            rect.setStroke(Color.BLACK);
            root.getChildren().add(rect);
        }


        //Create and configure TextField to display width
        widthField = new TextField("???");
        root.setBottom(widthField);

        //Set width of first be into the text field
        double width = planner.getBeds().get(0).getWidth();
        String str = Double.toString(width);
        widthField.setText(str);


        // Parent root = FXMLLoader.load(getClass().getResource("garden_gui.fxml"));
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Garden Planner");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
