package com.company;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AnimeThread extends  Thread {

    public Group group;

    public  AnimeThread( Group group){
        this.group = group;
    }

    @Override
    public void run() {
        try {
            sleep(100);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

                BorderPane RborderPane = new BorderPane();
                RborderPane.setMinHeight(900);
                RborderPane.setMinWidth(1200);
                Rectangle rectangle = new Rectangle(50,50);
                rectangle.setFill(Color.CRIMSON);
                Rectangle rectangle1 = new Rectangle(50,50);
                rectangle1.setFill(Color.CRIMSON);
                FlowPane rpane = new FlowPane();
                rpane.getChildren().add(rectangle1);
                rpane.setPadding(new Insets(850,0,0,575));
                Rectangle rectangle2 = new Rectangle(50,50);
                rectangle2.setFill(Color.CRIMSON);
                RborderPane.setLeft(rectangle);
                RborderPane.setRight(rectangle2);

                RotateTransition rotateTransition = new RotateTransition();
                rotateTransition.setNode(rectangle);
                rotateTransition.setDuration(Duration.millis(3000));
                rotateTransition.setByAngle(180f);
                rotateTransition.setCycleCount(Timeline.INDEFINITE);
                rotateTransition.setAutoReverse(true);
                rotateTransition.play();
                RotateTransition rotateTransition1 = new RotateTransition();
                rotateTransition1.setNode(rectangle1);
                rotateTransition1.setDuration(Duration.millis(3000));
                rotateTransition1.setByAngle(180f);
                rotateTransition1.setCycleCount(Timeline.INDEFINITE);
                rotateTransition1.setAutoReverse(true);
                rotateTransition1.play();
                RotateTransition rotateTransition2 = new RotateTransition();
                rotateTransition2.setNode(rectangle2);
                rotateTransition2.setDuration(Duration.millis(3000));
                rotateTransition2.setByAngle(180f);
                rotateTransition2.setCycleCount(Timeline.INDEFINITE);
                rotateTransition2.setAutoReverse(true);
                rotateTransition2.play();

                group.getChildren().addAll(RborderPane,
                        rpane
                );
    }
}
