import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;


public class SimpleToolPaint2<options> extends Application {

    public static void main(String[] args) {
        launch();
    }

    //-----------------------------------------------------------------
    private Canvas canvas;  // The canvas on which everything is drawn.
    private GraphicsContext g;  // For drawing on the canvas.
    static final Color TOOL_RECT_FG = Color.LIGHTCORAL;
    static final Color TOOL_RECT_BG = Color.WHITE;
    static final Color TOOL_FG = Color.LEMONCHIFFON;
    private final Color[] palette = {
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
            Color.CYAN, Color.MAGENTA, Color.color(0.95,0.9,0)
    };
//    static final Color currentColor = Colo;
    static final int CELL_W = 60;
    static final int PADDING = 5;

    /**
     * The start() method creates the GUI, sets up event listening, and
     * shows the window on the screen.
     */
    static class AbstractTool extends StackPane {
        private final Rectangle rect = new Rectangle(CELL_W,CELL_W);

        public AbstractTool(){

        }
    }
    static class ColorTool extends AbstractTool {
        private final Color color;

        public ColorTool(Color color) {
            this.color = color;
        }

        Color getColor(){
            return this.color;
        }

    }

    public void start(Stage stage) {

        /* Configure the GUI and show the window. */
        HBox hbox = new HBox();
        hbox.setBackground(Background.fill(Color.WHITE));
        
        //canvas section
        canvas = new Canvas(800,565);
        g = canvas.getGraphicsContext2D();
        int width = (int)canvas.getWidth();    // Width of the canvas.
        int height = (int)canvas.getHeight();  // Height of the canvas.
        g.setFill(Color.WHITE);
        g.fillRect(0,0,width,height);
        g.setStroke(Color.LIGHTGREY);
        g.setLineWidth(3);
        g.strokeRect(1.5, 1.5, width-3, height-3);


        //tool section
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        vbox1.setSpacing(10);
        vbox2.setSpacing(10);
        vbox1.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        vbox2.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        String cssLayout = "-fx-border-color: lightgrey;\n" +
                "-fx-border-width: 3;\n";
        vbox1.setStyle(cssLayout);
        vbox2.setStyle(cssLayout);



        StackPane s1 = new StackPane();
        Rectangle rect1 = new Rectangle(CELL_W,CELL_W);
        Circle circ1 = new Circle();
        circ1.setRadius(3);
        circ1.setFill(TOOL_FG);
        s1.getChildren().addAll(rect1,circ1);


        StackPane s2 = new StackPane();
        Rectangle rect2 = new Rectangle(CELL_W,CELL_W);
        Circle circ2 = new Circle();
        circ2.setRadius(5);
        circ2.setFill(TOOL_FG);
        s2.getChildren().addAll(rect2,circ2);

        StackPane s3 = new StackPane();
        Rectangle rect3 = new Rectangle(CELL_W,CELL_W);
        Circle circ3 = new Circle();
        circ3.setRadius(7);
        circ3.setFill(TOOL_FG);
        s3.getChildren().addAll(rect3,circ3);

        StackPane s4 = new StackPane();
        Rectangle rect4 = new Rectangle(CELL_W,CELL_W);
        Circle circ4 = new Circle();
        circ4.setRadius(9);
        circ4.setFill(TOOL_FG);
        s4.getChildren().addAll(rect4,circ4);

        StackPane s5 = new StackPane();
        Rectangle rect5 = new Rectangle(CELL_W,CELL_W);
        Line line = new Line(10,10,10,10);
        line.setStrokeWidth(5);
        line.setStroke(Color.BLACK);
        s5.getChildren().addAll(rect5,line);

        StackPane s6 = new StackPane();
        Rectangle rect6 = new Rectangle(CELL_W,CELL_W);
        Rectangle rectangle6 = new Rectangle();
        rectangle6.setWidth(40);
        rectangle6.setHeight(40);
        rectangle6.setFill(TOOL_FG);
        s6.getChildren().addAll(rect6,rectangle6);

        StackPane s7 = new StackPane();
        Rectangle rect7 = new Rectangle(CELL_W,CELL_W);
        Circle circ7 = new Circle();
        circ7.setRadius(23);
        circ7.setFill(TOOL_FG);
        s7.getChildren().addAll(rect7,circ7);

        StackPane s8 = new StackPane();
        Rectangle rect8 = new Rectangle(CELL_W,CELL_W);
        Rectangle rectangle8 = new Rectangle();
        rectangle8.setWidth(40);
        rectangle8.setHeight(40);
        rectangle8.setArcHeight(10);
        rectangle8.setArcWidth(10);
        rectangle8.setFill(TOOL_FG);
        s8.getChildren().addAll(rect8,rectangle8);

        StackPane s11 = new StackPane();
        Rectangle rect11 = new Rectangle(CELL_W,CELL_W);
        s11.getChildren().add(rect11);
        Rectangle rect22 = new Rectangle(CELL_W,CELL_W);
        Rectangle rect33 = new Rectangle(CELL_W,CELL_W);
        Rectangle rect44 = new Rectangle(CELL_W,CELL_W);
        Rectangle rect55 = new Rectangle(CELL_W,CELL_W);
        Rectangle rect66 = new Rectangle(CELL_W,CELL_W);
        Rectangle rect77 = new Rectangle(CELL_W,CELL_W);
        Rectangle rect88 = new Rectangle(CELL_W,CELL_W);

        rect1.setFill(TOOL_RECT_FG);
        rect2.setFill(TOOL_RECT_FG);
        rect3.setFill(TOOL_RECT_FG);
        rect4.setFill(TOOL_RECT_FG);
        rect5.setFill(TOOL_RECT_FG);
        rect6.setFill(TOOL_RECT_FG);
        rect7.setFill(TOOL_RECT_FG);
        rect8.setFill(TOOL_RECT_FG);

        rect11.setFill(palette[0]);
        rect22.setFill(palette[1]);
        rect33.setFill(palette[2]);
        rect44.setFill(palette[3]);
        rect55.setFill(palette[4]);
        rect66.setFill(palette[5]);
        rect77.setFill(palette[6]);

        StackPane s88 = new StackPane();
        Text clear = new Text("CLEAR");
        clear.setFill(TOOL_FG);
        rect88.setFill(TOOL_RECT_FG);
        s88.getChildren().addAll(rect88,clear);


        vbox1.getChildren().addAll(s1,s2,s3,s4,rect5,s6,s7,s8);
        vbox2.getChildren().addAll(s11,rect22,rect33,rect44,rect55,rect66,rect77,s88);


        hbox.getChildren().addAll(canvas, vbox1, vbox2);

        Pane root = new Pane(hbox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Simple Paint Program");
        stage.show();
    }








} // end class SimplePaint
