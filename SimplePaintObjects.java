import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

interface ShapeObject {

    void draw(GraphicsContext g);

    public void dragUpdate();
}

class LineSegmentShape implements ShapeObject {


    @Override
    public void draw(GraphicsContext g) {

    }

    @Override
    public void dragUpdate() {

    }
}

class LineShape implements ShapeObject {
    private final Point2D start;
    private final Point2D end;
    private final Color color;


    public LineShape(Point2D start, Point2D end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public void dragUpdate() {

    }
}

abstract class  FilledPolyShape implements ShapeObject {

}

class RectangeShape extends FilledPolyShape {


    @Override
    public void draw(GraphicsContext g) {

    }

    @Override
    public void dragUpdate() {

    }
}

class OvalShape extends FilledPolyShape {
    public void draw(GraphicsContext g) {

    }

    @Override
    public void dragUpdate() {

    }
}

class RoundedRectangleShape extends FilledPolyShape {

    public void draw(GraphicsContext g) {

    }

    @Override
    public void dragUpdate() {

    }
}

abstract class AbstractTool extends StackPane{
    private final int CELL_W = 60;

    public int getCELL_W() {
        return CELL_W;
    }

    public Color getTOOL_RECT_FG() {
        return TOOL_RECT_FG;
    }

    public Color getTOOL_RECT_BG() {
        return TOOL_RECT_BG;
    }

    public Color getTOOL_FG() {
        return TOOL_FG;
    }

    final Color TOOL_RECT_FG = Color.LIGHTCORAL;
    final Color TOOL_RECT_BG = Color.WHITE;
    final Color TOOL_FG = Color.LEMONCHIFFON;

    public AbstractTool(Color color){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(this.CELL_W);
        rectangle.setHeight(this.CELL_W);
        rectangle.setFill(color);
        rectangle.setStroke(Color.WHITE);
        this.getChildren().add(rectangle);
    }
}

class ColorTool extends AbstractTool{
    private final Color color;
    public ColorTool(Color color) {
        super(color);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void deactivate() {
        this.setBorder(Border.EMPTY);

    }

    public void activate() {
        this.setBorder(Border.stroke(Color.BLACK));
    }
}

class ActionTool extends AbstractTool{
    public ActionTool(Color color) {
        super(color);
    }

    public ActionTool(String text) {
        super(Color.LIGHTCORAL);
        Label cmdName = new Label(text);
        cmdName.setTextFill(Color.LEMONCHIFFON);
        cmdName.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        this.getChildren().add(cmdName);
    }
}

abstract class ShapeTool extends AbstractTool{
    private ShapeObject Null;

    public ShapeTool(Color color) {
        super(color);
    }

    public ShapeObject getPaintShape() {
        return Null;
    }

    public void deactivate() {
        this.setBorder(Border.EMPTY);

    }

    public void draw(GraphicsContext g, Color color, Point2D start, Point2D end) {

    }

    public void activate() {
        this.setBorder(Border.stroke(Color.BLACK));
    }
}

class PointTool extends ShapeTool{
    double penWidth;
    public PointTool(int penWidth){
        super(Color.LIGHTCORAL);
        this.penWidth = penWidth;
        Ellipse toolImage = new Ellipse(penWidth, penWidth);
        toolImage.setStroke(Color.LEMONCHIFFON);
        toolImage.setFill(Color.LEMONCHIFFON);

        this.getChildren().add(toolImage);
    }

    public void deactivate(String toolName) {
        this.setBorder(Border.EMPTY);
        if(!toolName.equals("LINE")){
            this.penWidth = 2;
        }
    }



    public void activate() {
        this.setBorder(Border.stroke(Color.BLACK));
    }
}



class LineTool extends ShapeTool{
    private final String toolName = "LINE";
    private LineShape currentLineShape;

    public LineTool() {
        super(Color.LIGHTCORAL);
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(40);
        rectangle.setHeight(40);
        rectangle.setFill(super.getTOOL_FG());
        this.getChildren().add(rectangle);
    }

    public String getToolName(){
        return this.toolName;
    }

    public void draw (GraphicsContext g, Color color, Point2D start, Point2D end){
        currentLineShape = new LineShape(start, end, color);
        currentLineShape.draw(g);
    }


}

class RectangleTool extends ShapeTool{
    private final String toolName = "RECT";

    public RectangleTool() {
        super(Color.LIGHTCORAL);
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(40);
        rectangle.setHeight(40);
        rectangle.setFill(super.getTOOL_FG());
        this.getChildren().add(rectangle);
    }

    public String getToolName(){
        return this.toolName;
    }

}

class OvalTool extends ShapeTool{
    private final String toolName = "OVAL";


    public OvalTool() {
        super(Color.LIGHTCORAL);
        Circle circle = new Circle();
        circle.setRadius(23);
        circle.setFill(super.getTOOL_FG());
        this.getChildren().add(circle);
    }

    public String getToolName() {
        return this.toolName;
    }
}

class RoundedRectangleTool extends ShapeTool{
    private final String toolName = "ROUNDED_RECT";


    public RoundedRectangleTool() {
        super(Color.LIGHTCORAL);
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(40);
        rectangle.setHeight(40);
        rectangle.setArcHeight(10);
        rectangle.setArcWidth(10);
        rectangle.setFill(Color.LEMONCHIFFON);
        this.getChildren().add(rectangle);
    }

    public String getToolName(){
        return this.toolName;
    }

}

public class SimplePaintObjects<primaryStage> extends Application {
    private ColorTool currentColorTool;
    private PointTool currentPointTool;
    private ShapeTool currentShapeTool;
    private String nameOfCurrentShapeTool;
    private ArrayList<ShapeTool> listOfShapes;
    private double currentPenWidth;
    private GraphicsContext g;  // For drawing on the canvas.
    static final Color TOOL_RECT_FG = Color.LIGHTCORAL;
    static final Color TOOL_RECT_BG = Color.WHITE;
    static final Color TOOL_FG = Color.LEMONCHIFFON;
    static final int PADDING = 5;
    static final int WIDTH = 800;
    static final int HEIGHT = 550;
    private boolean dragging;
    private double prevX, prevY;   // The previous location of the mouse, when
    // the user is drawing by dragging the mouse.
    private final double SMALL_PW =2;
    private final double MEDIUM_PW = 4;
    private final double LARGE_PW = 6;
    private final double XLARGE_PW = 8;
    private Stage primaryStage;

    public SimplePaintObjects() {
        primaryStage = null;
    }

    public static void main(String[] args){
        launch(args);
    }



    private Parent makeRootPane() {
        HBox root = new HBox();
        root.getChildren().add(makeCanvas());
        root.getChildren().add(makeToolPane());
        root.getChildren().add(makeColorPane());
        return root;
    }

    private final Color[] palette = {
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE,
            Color.CYAN, Color.MAGENTA, Color.YELLOW
    };
    private Node makeColorPane() {
        VBox colorPane = new VBox();

        for (int i = 0; i<7; i++) {
            colorPane.getChildren().add(
                    addMouseHandlerToColorTool(
                            new ColorTool(palette[i])
                    ));
        }

        colorPane.getChildren().add(addMouseHandlerToActionTool(new ActionTool("Clear")));
        colorPane.setSpacing(6);
        colorPane.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        String cssLayout = "-fx-border-color: gray;\n" +
                "-fx-border-width: 3;\n";
        colorPane.setStyle(cssLayout);
        return colorPane;
    }

    private Node addMouseHandlerToActionTool(ActionTool clear) {
        clear.setOnMousePressed((e)->{
            try {
                clearCanvas();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        return clear;
    }

    private ColorTool addMouseHandlerToColorTool(ColorTool tool){
        tool.setOnMousePressed((e) -> {
            if(this.currentColorTool != null) {
                this.currentColorTool.deactivate();
            }
            this.currentColorTool = tool;
            g.setLineWidth(2);
            g.setStroke(this.currentColorTool.getColor());
            tool.activate();
        });
        return tool;
    }

    private Node addMouseHandlerToPointTool(PointTool tool) {
        tool.setOnMousePressed((e) -> {
            if(this.currentPointTool != null) {
                this.currentPointTool.deactivate();
                this.currentPointTool = tool;
            }
            if(this.currentShapeTool != null){
                this.currentShapeTool.deactivate();
                this.currentShapeTool = null;
            }
            this.currentPointTool = tool;
            this.currentPenWidth = tool.penWidth;
            tool.activate();
        });

        return tool;
    }




    private Node makeToolPane() {
        VBox toolPane = new VBox();
        for(int i =2;i<=8;i+=2) {
            toolPane.getChildren().add(addMouseHandlerToPointTool(
                    new PointTool(i)));
        }

        toolPane.getChildren().add(addMouseHandlerToLineTool(new LineTool()));
        toolPane.getChildren().add(addMouseHandlerToRectTool(
                new RectangleTool()));
        toolPane.getChildren().add(addMouseHandlerToOvalTool(new OvalTool()));
        toolPane.getChildren().add(addMouseHandlerToRoundedRectTool(
                new RoundedRectangleTool()));



        toolPane.setSpacing(6);
        toolPane.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        String cssLayout = "-fx-border-color: gray;\n" +
                "-fx-border-width: 3;\n";
        toolPane.setStyle(cssLayout);
        return toolPane;
    }

    private ShapeTool changeActivate(ShapeTool shapeTool,String toolName){
        shapeTool.setOnMousePressed((e)->{
            if(this.currentShapeTool != null){
                this.currentShapeTool.deactivate();
            }
            if(this.currentPointTool != null){
                this.currentPointTool.deactivate();
            }
            this.nameOfCurrentShapeTool = toolName;
            this.currentShapeTool = shapeTool;
            this.currentPenWidth = 2;
            if(this.currentColorTool != null){
                g.setLineWidth(2);
                g.setStroke(this.currentColorTool.getColor());
            }
            shapeTool.activate();

        });
        return shapeTool;
    }

    private Node addMouseHandlerToOvalTool(OvalTool ovalTool) {
        return changeActivate(ovalTool,ovalTool.getToolName());
    }

    private Node addMouseHandlerToRectTool(RectangleTool rectangleTool) {
        return changeActivate(rectangleTool,rectangleTool.getToolName());
    }

    private Node addMouseHandlerToLineTool(LineTool lineTool) {
        return changeActivate(lineTool,lineTool.getToolName());
    }

    private Node addMouseHandlerToRoundedRectTool(RoundedRectangleTool roundedRectangleTool) {
        return changeActivate(roundedRectangleTool,roundedRectangleTool.getToolName());
    }

    private Node makeCanvas(){
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0,(int)canvas.getWidth(),(int)canvas.getHeight());
        g.setStroke(Color.GRAY);
        g.setLineWidth(3);
        g.strokeRect(1.5,1.5,(int)canvas.getWidth()-3,(int)canvas.getHeight()-3);
        canvas.setOnMousePressed(this::mousePressed);
        canvas.setOnMouseDragged(this::mouseDragged);
        canvas.setOnMouseReleased(this::mouseReleased);
        return canvas;
    }

    private void mousePressed(MouseEvent e) {
        if (dragging)  // Ignore mouse presses that occur
            return;
        int x = (int)e.getX();   // x-coordinate where the user clicked.
        int y = (int)e.getY();
        if(currentPenWidth == SMALL_PW) {
            setPenWidth(x,y,SMALL_PW);
        } else if (currentPenWidth == MEDIUM_PW){
            setPenWidth(x,y,MEDIUM_PW);
        } else if (currentPenWidth == LARGE_PW){
            setPenWidth(x,y,LARGE_PW);
        } else if (currentPenWidth == XLARGE_PW){
            setPenWidth(x,y,XLARGE_PW);
        } else{

        }
    }
    private void setPenWidth(int x, int y,double pw) {
        prevX = x;
        prevY = y;
        dragging = true;
        g.setLineWidth(pw);
    }

    private void mouseDragged(MouseEvent e) {
        if (!dragging)
            return;  // Nothing to do because the user isn't drawing.
        double x = e.getX();   // x-coordinate of mouse.
        double y = e.getY();
        Point2D start = new Point2D(prevX,prevY);
        Point2D end = new Point2D(x,y);

        currentShapeTool.draw(g,currentColorTool.getColor(), start,end);
        if(!nameOfCurrentShapeTool.equals("LINE")) {
            prevX = x;
            prevY = y;
        }


    }

    private void mouseReleased(MouseEvent e) {
        dragging = false;
        double x = e.getX();   // x-coordinate of mouse.
        double y = e.getY();
        g.strokeLine(prevX, prevY, x, y);  // Draw the line.

    }

    private void clearCanvas() throws Exception {
        start(this.primaryStage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(makeRootPane()));
        primaryStage.setTitle("Simple Paint Object Tool");
        primaryStage.show();
    }
}