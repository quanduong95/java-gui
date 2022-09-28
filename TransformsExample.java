import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
 * PongApp extends Application{}
 *
 * Pong extends Group{}
 *
 * Class Bat{
 *   public? void shrink(Parans?){};
 *
 *   public void activate(boolean isActive){};
 *
 *   public void deactivate(){};
 * }
 *
 * Class Ball{}
 * */

class Model extends Group{
    Rectangle r;

    public Model(){
        r = new Rectangle();
        r.setWidth(50);
        r.setHeight(50);
        r.setFill(Color.BLUE);
        getChildren().add(r);
        AnimationTimer loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                r.setRotate(r.getRotate() + 3);
            }
        };
        loop.start();
    }

    void handleMouseMove(MouseEvent e){
        r.setTranslateX(e.getX());
        r.setTranslateY(e.getY());
    }
}

public class TransformsExample<primaryStage> extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Model root = new Model();
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        scene.setOnMouseMoved(e -> root.handleMouseMove(e));
        primaryStage.show();
    }
}
