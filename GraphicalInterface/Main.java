package com.company;

import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


public class Main extends Application {
    public  SellPoint sellPoint;
    private ObservableList<SellPoint> sellPoints = FXCollections.observableArrayList();
    private ObservableList<SellPoint> newSell_points = FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane flowPane = new FlowPane();
        Label mainLabel = new Label();
        Image mainImage = new Image(new FileInputStream("E:\\\\Lab8\\Sell points.png"));
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setFitWidth(100);
        mainImageView.setFitHeight(90);

        mainLabel.setGraphic(mainImageView);

        mainLabel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainLabel.setScaleX(1.2);
                mainLabel.setScaleY(1.2);
            }
        });

        mainLabel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mainLabel.setScaleX(1);
                mainLabel.setScaleY(1);
            }
        });

        flowPane.getChildren().add(mainLabel);
        flowPane.setPadding(new Insets(10,0,0,550));
        Group root = new Group();
        HBox hBox = new HBox();
        Button add = new Button("Add");
        Tooltip add_tool = new Tooltip("Add a sell point");
        add.setTooltip(add_tool);
        hBox.getChildren().add(add);
        Button remove = new Button("Remove");
        Tooltip rem_tool = new Tooltip("Remove a sell point");
        remove.setTooltip(rem_tool);
        hBox.getChildren().add(remove);
        Button clear = new Button("Clear");
        Tooltip clear_tool = new Tooltip("Clear the database");
        clear.setTooltip(clear_tool);
        hBox.getChildren().add(clear);
        Button encoder = new Button("Serialize");
        Tooltip enc_tool = new Tooltip("Serialize the data");
        encoder.setTooltip(enc_tool);
        hBox.getChildren().add(encoder);
        Button decoder = new Button("Deserialize");
        Tooltip dec_tool = new Tooltip("Deserialize the data");
        decoder.setTooltip(dec_tool);
        hBox.getChildren().add(decoder);
        Button animation = new Button("Animation");
        Tooltip anim_tool = new Tooltip("Show some graphical animation");
        animation.setTooltip(anim_tool);
        hBox.getChildren().add(animation);
        Button exit = new Button("Exit");
        Tooltip ex_tool = new Tooltip("Exit from the database");
        exit.setTooltip(ex_tool);
        hBox.getChildren().add(exit);

        VBox vBox = new VBox();
        vBox.getChildren().add(hBox);

        BorderPane borderPane = new BorderPane();

        Pane pane = new Pane();


        TableView<SellPoint> tableView = new TableView<SellPoint>(sellPoints);
        TableColumn<SellPoint,String> Name = new TableColumn<SellPoint,String>("Name");
        Name.setCellValueFactory(new PropertyValueFactory<SellPoint,String>("pointName"));
        Name.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        TableColumn<SellPoint,String> Address = new TableColumn<SellPoint,String>("Address");
        Address.setCellValueFactory(new PropertyValueFactory<SellPoint,String>("pointAddress"));
        Address.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        TableColumn<SellPoint,String> Specialization = new TableColumn<SellPoint, String>("Specialization");
        Specialization.setCellValueFactory(new PropertyValueFactory<SellPoint,String>("specialization"));
        Specialization.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        TableColumn<SellPoint,Integer> ID = new TableColumn<SellPoint, Integer>("ID");
        ID.setCellValueFactory(new PropertyValueFactory<SellPoint,Integer>("ID"));
        ID.prefWidthProperty().bind(tableView.widthProperty().divide(4));
        tableView.getColumns().addAll(ID,Name,Address,Specialization);
        tableView.setMinWidth(453);
        tableView.setMinHeight(693);

        pane.getChildren().add(vBox);
        borderPane.setCenter(pane);
        borderPane.setMinWidth(450);
        GridPane gridPane = new GridPane();
        gridPane.getChildren().addAll(tableView,borderPane);
        ColumnConstraints constraints1 = new ColumnConstraints(150,520,Double.MAX_VALUE);
        constraints1.setHgrow(Priority.ALWAYS);
        ColumnConstraints constraints2 = new ColumnConstraints(150,530,Double.MAX_VALUE);
        constraints2.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(constraints1,constraints2);
        gridPane.setColumnIndex(tableView,0);
        gridPane.setColumnIndex(borderPane,1);
        gridPane.setPadding(new Insets(130,0,0,100));

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextField id_add = new TextField();
                TextField name_add = new TextField();
                TextField adr_add = new TextField();
                TextField spec_add = new TextField();
                Button save = new Button("Save data");
                HBox id = new HBox(id_add,new Label(" ID"));
                HBox name = new HBox(new Label(" Name", name_add));
                HBox adr = new HBox(new Label(" Address", adr_add));
                HBox spec = new HBox(new Label(" Specialization", spec_add));
                vBox.getChildren().addAll(id, name, adr, spec, save);

                save.setOnMouseClicked(event1 -> {

                    if (id_add.getText().length() > 0) {
                        SellPoint addable_point = new SellPoint();
                        addable_point.setID(id_add.getText());
                        addable_point.setPointName(name_add.getText());
                        addable_point.setPointAddress(adr_add.getText());
                        addable_point.setSpecialization(spec_add.getText());

                        sellPoints.add(addable_point);
                        vBox.getChildren().clear();
                        tableView.refresh();
                        vBox.getChildren().add(hBox);
                    }
                    else {
                        System.err.println("ID of a sell point must be confirmed!");
                    }

                    if(newSell_points.size() != 0){
                        if(id_add.getText().length() > 0){
                            SellPoint new_addable_point = new SellPoint();
                            new_addable_point.setID(id_add.getText());
                            new_addable_point.setPointName(name_add.getText());
                            new_addable_point.setSpecialization(spec_add.getText());
                            new_addable_point.setPointAddress(adr_add.getText());

                            newSell_points.add(new_addable_point);
                            vBox.getChildren().clear();
                            tableView.refresh();
                            vBox.getChildren().add(hBox);
                        }
                    }
                });
            }
        });

        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sellPoints.setAll();
                newSell_points.setAll();
                tableView.refresh();
            }
        });

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(sellPoint != null){
                    sellPoints.remove(sellPoint);
                    vBox.getChildren().clear();
                    tableView.refresh();
                    vBox.getChildren().add(hBox);
                }
                else{
                    System.err.println("Wrong input!");
                }
            }
        });

        encoder.setOnMouseClicked(event -> {
            final FileChooser encoder_fileChooser = new FileChooser();
            encoder_fileChooser.setTitle("Select any file to serialize data");
            encoder_fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            encoder_fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML","*.xml"));
            File encod_file = encoder_fileChooser.showOpenDialog(primaryStage);
            if(encod_file != null) {
                try {
                    XMLEncoder xmlEncoder = new XMLEncoder(
                            new BufferedOutputStream(
                                    new FileOutputStream(encod_file.getPath())));
                    Integer size = sellPoints.size();
                    xmlEncoder.writeObject(size);
                    for (SellPoint s: sellPoints) {
                        xmlEncoder.writeObject(s);
                    }
                    xmlEncoder.close();
                }
                catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }
            }
        });

        decoder.setOnMouseClicked(event -> {
            final FileChooser decoder_fileChooser = new FileChooser();
            decoder_fileChooser.setTitle("Select any file to deserialize data");
            decoder_fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML","*.xml"));
            decoder_fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File decod_file = decoder_fileChooser.showOpenDialog(primaryStage);
            if(decod_file != null){
                try {
                    XMLDecoder xmlDecoder = new XMLDecoder(
                            new BufferedInputStream(
                                    new FileInputStream(decod_file.getPath())));
                    Integer size = (Integer) xmlDecoder.readObject();
                    for (int i = 0; i < size ; i++) {
                        newSell_points.add((SellPoint)xmlDecoder.readObject());
                    }
                }
                catch (FileNotFoundException exception){
                    exception.printStackTrace();
                }
                tableView.setItems(newSell_points);
                tableView.refresh();
            }
        });

        animation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                root.getChildren().clear();
                Canvas canvas = new Canvas(700, 500);
                Canvas canvas1 = new Canvas(700, 500);
                Canvas canvas2 = new Canvas(700, 500);
                Canvas canvas3 = new Canvas(700, 500);
                Canvas canvas4 = new Canvas(700, 500);
                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                GraphicsContext graphicsContext1 = canvas1.getGraphicsContext2D();
                GraphicsContext graphicsContext2 = canvas2.getGraphicsContext2D();
                GraphicsContext graphicsContext3 = canvas3.getGraphicsContext2D();
                GraphicsContext graphicsContext4 = canvas4.getGraphicsContext2D();
                graphicsContext.setStroke(Color.DARKORANGE);
                graphicsContext1.setFill(Color.DARKORANGE);
                graphicsContext2.setStroke(Color.DARKORANGE);
                graphicsContext3.setFill(Color.DARKORANGE);
                graphicsContext4.setStroke(Color.DARKORANGE);
                graphicsContext.strokePolygon(new double[]{80, 110, 80, 110}, new double[]{230, 230, 260, 260}, 4);
                graphicsContext1.fillArc(150, 100, 30, 30, 45, 240, ArcType.ROUND);
                graphicsContext2.strokeArc(80, 100, 30, 30, 45, 240, ArcType.ROUND);
                graphicsContext3.fillPolygon(new double[]{60, 90, 60, 90}, new double[]{210, 210, 240, 240}, 4);
                graphicsContext4.strokeOval(120, 120, 30, 30);
                Path path = new Path();
                Path path1 = new Path();
                Path path2 = new Path();
                Path path3 = new Path();
                Path path4 = new Path();
                path.getElements().add(new MoveTo(300, 350));
                path1.getElements().add(new MoveTo(320, 370));
                path2.getElements().add(new MoveTo(340, 390));
                path3.getElements().add(new MoveTo(360, 310));
                path4.getElements().add(new MoveTo(380, 330));
                path.getElements().add(new CubicCurveTo(380, 200, 350, 120, 200, 120));
                path1.getElements().add(new CubicCurveTo(200, 120, 1800, 240, 380, 240));
                path2.getElements().add(new CubicCurveTo(240, 100, 240, 0, 90, 80));
                path3.getElements().add(new CubicCurveTo(200, 100, 50, 200, 0, 0));
                path4.getElements().add(new CubicCurveTo(120, 200, 100, 170, 120, 170));

                PathTransition pathTransition = new PathTransition();
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), canvas);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0.8);
                fadeTransition.setCycleCount(Timeline.INDEFINITE);
                fadeTransition.setAutoReverse(true);
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(3000), canvas);
                scaleTransition.setToX(3.0);
                scaleTransition.setToY(3.0);
                scaleTransition.setCycleCount(Timeline.INDEFINITE);
                scaleTransition.setAutoReverse(true);

                PathTransition pathTransition1 = new PathTransition();
                FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(2000), canvas1);
                fadeTransition1.setFromValue(1.0);
                fadeTransition1.setToValue(0.4);
                fadeTransition1.setCycleCount(Timeline.INDEFINITE);
                fadeTransition1.setAutoReverse(true);
                ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(3000), canvas1);
                scaleTransition1.setToX(3.0);
                scaleTransition1.setToY(3.0);
                scaleTransition1.setCycleCount(Timeline.INDEFINITE);
                scaleTransition1.setAutoReverse(true);

                PathTransition pathTransition2 = new PathTransition();
                FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(2000), canvas2);
                fadeTransition2.setFromValue(1.0);
                fadeTransition2.setToValue(0.8);
                fadeTransition2.setCycleCount(Timeline.INDEFINITE);
                fadeTransition2.setAutoReverse(true);
                ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(3000), canvas2);
                scaleTransition2.setToX(3.0);
                scaleTransition2.setToY(3.0);
                scaleTransition2.setCycleCount(Timeline.INDEFINITE);
                scaleTransition2.setAutoReverse(true);

                PathTransition pathTransition3 = new PathTransition();
                FadeTransition fadeTransition3 = new FadeTransition(Duration.millis(2000), canvas3);
                fadeTransition3.setFromValue(1.0);
                fadeTransition3.setToValue(0.4);
                fadeTransition3.setCycleCount(Timeline.INDEFINITE);
                fadeTransition3.setAutoReverse(true);
                ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(3000), canvas3);
                scaleTransition3.setToX(3.0);
                scaleTransition3.setToY(3.0);
                scaleTransition3.setCycleCount(Timeline.INDEFINITE);
                scaleTransition3.setAutoReverse(true);

                PathTransition pathTransition4 = new PathTransition();
                FadeTransition fadeTransition4 = new FadeTransition(Duration.millis(2000), canvas4);
                fadeTransition4.setFromValue(1.0);
                fadeTransition4.setToValue(0.8);
                fadeTransition4.setCycleCount(Timeline.INDEFINITE);
                fadeTransition4.setAutoReverse(true);
                ScaleTransition scaleTransition4 = new ScaleTransition(Duration.millis(3000), canvas4);
                scaleTransition4.setToX(3.0);
                scaleTransition4.setToY(3.0);
                scaleTransition4.setCycleCount(Timeline.INDEFINITE);
                scaleTransition4.setAutoReverse(true);

                pathTransition.setPath(path);
                pathTransition1.setPath(path1);
                pathTransition2.setPath(path2);
                pathTransition3.setPath(path3);
                pathTransition4.setPath(path4);
                pathTransition.setDuration(Duration.millis(5000));
                pathTransition1.setDuration(Duration.millis(4000));
                pathTransition2.setDuration(Duration.millis(3500));
                pathTransition3.setDuration(Duration.millis(3000));
                pathTransition4.setDuration(Duration.millis(2500));
                pathTransition.setNode(canvas);
                pathTransition1.setNode(canvas1);
                pathTransition2.setNode(canvas2);
                pathTransition3.setNode(canvas3);
                pathTransition4.setNode(canvas4);
                pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition1.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
                pathTransition.setCycleCount(Timeline.INDEFINITE);
                pathTransition1.setCycleCount(Timeline.INDEFINITE);
                pathTransition2.setCycleCount(Timeline.INDEFINITE);
                pathTransition3.setCycleCount(Timeline.INDEFINITE);
                pathTransition4.setCycleCount(Timeline.INDEFINITE);
                pathTransition.setAutoReverse(true);
                pathTransition1.setAutoReverse(true);
                pathTransition2.setAutoReverse(true);
                pathTransition3.setAutoReverse(true);
                pathTransition4.setAutoReverse(true);


                ParallelTransition parallelTransition = new ParallelTransition();
                parallelTransition.getChildren().addAll(pathTransition,
                        fadeTransition,
                        scaleTransition
                );
                parallelTransition.setCycleCount(Timeline.INDEFINITE);
                parallelTransition.play();

                ParallelTransition parallelTransition1 = new ParallelTransition();
                parallelTransition1.getChildren().addAll(pathTransition1,
                        fadeTransition1,
                        scaleTransition1
                );
                parallelTransition1.setCycleCount(Timeline.INDEFINITE);
                parallelTransition1.play();

                ParallelTransition parallelTransition2 = new ParallelTransition();
                parallelTransition2.getChildren().addAll(scaleTransition2,
                        fadeTransition2,
                        pathTransition2
                );
                parallelTransition2.setCycleCount(Timeline.INDEFINITE);
                parallelTransition2.play();

                ParallelTransition parallelTransition3 = new ParallelTransition();
                parallelTransition3.getChildren().addAll(fadeTransition3,
                        scaleTransition3,
                        pathTransition3
                );
                parallelTransition3.setCycleCount(Timeline.INDEFINITE);
                parallelTransition3.play();

                ParallelTransition parallelTransition4 = new ParallelTransition();
                parallelTransition4.getChildren().addAll(scaleTransition4,
                        fadeTransition4,
                        pathTransition4
                );
                parallelTransition4.setCycleCount(Timeline.INDEFINITE);
                parallelTransition4.play();

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


                root.getChildren().addAll(canvas,
                        canvas1,
                        canvas2,
                        canvas3,
                        canvas4,
                        RborderPane,
                        rpane
                );
                Button stop = new Button("Stop");
                Pane pane1 = new Pane();
                pane1.getChildren().add(stop);
                BorderPane borderPane1 = new BorderPane();
                borderPane1.setMinWidth(1200);
                borderPane1.setMinHeight(900);
                borderPane1.setBottom(pane1);
                root.getChildren().add(borderPane1);

                stop.setOnMouseClicked(event1 -> {
                    root.getChildren().clear();
                    root.getChildren().addAll(gridPane,
                            flowPane
                    );
                });
            }
        });

        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("Bye! Thanks for using this interface.");
                Image image;
                try {
                    image = new Image(new FileInputStream("E:\\\\Lab8\\frog.jpg"));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(700);
                    imageView.setFitWidth(700);
                    FlowPane flowPane1 = new FlowPane();
                    flowPane1.setPadding(new Insets(150,0,0,250));
                    flowPane1.getChildren().add(imageView);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(3000),imageView);
                    fadeTransition.setFromValue(1.0);
                    fadeTransition.setToValue(0.0);
                    fadeTransition.setCycleCount(Timeline.INDEFINITE);
                    fadeTransition.setAutoReverse(true);
                    fadeTransition.play();
                    root.getChildren().clear();
                    root.getChildren().add(flowPane1);
                    ExitThread exitThread = new ExitThread();
                    exitThread.start();
                }
                catch (FileNotFoundException exc){
                    exc.printStackTrace();
                }
            }
        });

        root.getChildren().addAll(
                gridPane,
                flowPane
        );

        Scene scene = new Scene(root,1200,900);
        scene.setFill(Color.AZURE);
        scene.getStylesheets().add((getClass().getResource("main.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("~~~ Sell points ~~~");
        primaryStage.getIcons().add(new Image(new FileInputStream("E:\\\\Lab8\\sell points-logo.png")));

        AnimeThread thread = new AnimeThread(root);
        thread.start();

        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
        launch(args);
    }

}