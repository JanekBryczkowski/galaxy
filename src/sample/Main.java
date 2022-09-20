package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static final int SIZE = 700;
    public static final int STARS = 10000;
    public static final int MAX_WEIGHT = 10;
    AnchorPane root = new AnchorPane();
    {
        for (int i = 0; i < STARS; i++) {
            root.getChildren().add(new Star());
        }
    }

    //Math.random() * Main.SIZE

    class Star extends Circle {
        double m,vx,vy;

        public Star() {
            super(100, 100, 1);
            this.m = Math.random() * Main.MAX_WEIGHT;
            this.setFill(new Color(m/Main.MAX_WEIGHT,0.4,1,1));
            this.vx = Math.random() * 5 - 2.5;
            this.vy = Math.random() * 5 - 2.5;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            iterate();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(root, SIZE, SIZE);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void iterate(){
        double totalMass = 0;
        double totalX = 0;
        double totalY = 0;
        for(Node node : root.getChildren()) {
            if(node instanceof Star) {
                Star star = (Star) node;
                totalMass += star.m;
                totalX += star.getCenterX() * star.m;
                totalY += star.getCenterY() * star.m;
            }
        }
        double centerX = totalX / totalMass;
        double centerY = totalY / totalMass;
        for(Node node : root.getChildren()){
            if(node instanceof Star){

                ((Star)node).setCenterX(((Star)node).getCenterX()+((Star)node).vx);
                ((Star)node).setCenterY(((Star)node).getCenterY()+((Star)node).vy);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
