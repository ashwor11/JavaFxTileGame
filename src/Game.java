import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.awt.geom.Curve;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class Game extends Application {

    Tile[] tiles = new Tile[16];
    ArrayList<Shape> shapes = new ArrayList<>();
    GridPane gridPane = new GridPane();
    StackPane centerPane;

    BorderPane borderPane = new BorderPane();
    boolean isSolved;
    private static int numberOfMoves = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {


        //TAKING THE INPUT FROM TXT


        String path = new File(".").getAbsolutePath();
        File file = new File("CSE1242_spring2022_project_level4.txt");

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals(""))
                    continue;
                String values[] = line.split(",");

                String boxNumber = values[0];
                String tileType = values[1];
                String property = values[2];  //property means "hoziontal","vertical","none","free" and "curved pipe type"

                int x;  //Coordinates of current tile
                int y;

                y = (Integer.parseInt(boxNumber) - 1) / 4;
                x = (Integer.parseInt(boxNumber) - 1) % 4;


                if (tileType.equals("Starter")) {
                    tiles[Integer.parseInt(boxNumber) - 1] = new Starter(x, y, property);
                } else if (tileType.equals("Empty")) {  //Empty free mi yoksa none mı kontrol edip ona göre yapmak gerek

                    if (property.equals("none"))
                        tiles[Integer.parseInt(boxNumber) - 1] = new Empty(x, y);
                    else
                        tiles[Integer.parseInt(boxNumber) - 1] = new EmptyFree(x, y);

                } else if (tileType.equals("Pipe")) {

                    if (property.equals("Horizontal") || property.equals("Vertical"))  //Normal pipe
                        tiles[Integer.parseInt(boxNumber) - 1] = new Pipe(x, y, property);
                    else  //Curved pipe
                        tiles[Integer.parseInt(boxNumber) - 1] = new CurvedPipe(x, y, property);

                } else if (tileType.equals("PipeStatic")) {
                    tiles[Integer.parseInt(boxNumber) - 1] = new PipeStatic(x, y, property);
                } else if (tileType.equals("End")) {
                    tiles[Integer.parseInt(boxNumber) - 1] = new End(x, y, property);
                }
                //Tile türü bulundu ve oluşturuldu.

            }
        }


        //GUI DESIGN


        //Center design

        for (Tile tile : tiles) {
            gridPane.add(tile, tile.getXCoordinate(), tile.getYCoordinate());

        }
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: white");  //Bunu sihay yapcaz resimler düzeldiğinde


        centerPane = new StackPane();
        centerPane.setStyle("-fx-background-color: white;");
        centerPane.getChildren().add(gridPane);
        centerPane.setPadding(new Insets(0, 50, 0, 50));

        Pane generalPane = new Pane();
        generalPane.getChildren().add(centerPane);

        Circle circle = new Circle((50 + 125 / 2), (125 / 2), 15);
        circle.setFill(Color.ORANGE);
        circle.setStroke(Color.BLACK);
        generalPane.getChildren().add(circle);


        //Center design


        //Bottom design
        Label movesLabel = new Label();

        movesLabel.setText("Moves: " + numberOfMoves); //Her hamle action sonrası bunu yazdırmayı unutma
        movesLabel.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));
        Pane pane = new Pane();
        pane.getChildren().add(movesLabel);

/*
        Button temp = new Button("dene");  //Deney amaçlı sonra silinecek
        temp.setOnAction(e -> {
            // look the second variable is EmptyFree object or not
            
            System.out.println(checkSolution(tiles));
            dragTile(tiles[9],(EmptyFree) tiles[13]);
            System.out.println(checkSolution(tiles));
        });
*/


        for (Tile tile : tiles) {
            tile.setOnMouseReleased(e -> {
                Tile tile1 = (Tile) e.getTarget();
                Tile tile2 = (Tile) e.getPickResult().getIntersectedNode();
                dragTile(tile1, tile2);

                movesLabel.setText("Moves: " + numberOfMoves);

               isSolved = checkSolution(tiles);
                System.out.println(isSolved);


                //path transition yap


                if (isSolved) {


                    SequentialTransition sequentialTransition = new SequentialTransition();

                    for (Shape shape : shapes) {
                        PathTransition temp = new PathTransition();
                        temp.setNode(circle);
                        temp.setDuration(Duration.millis(1000));
                        temp.setPath(shape);

                        sequentialTransition.getChildren().add(temp);
                    }
                    sequentialTransition.play();





                }


                if (isSolved) {  //Burası deneme amaçlı
                    for (int i = 0; i < shapes.size(); i++) {
                        generalPane.getChildren().add(shapes.get(i));
                    }
                }


                primaryStage.show();
            });
        }


        // pane.getChildren().add(temp);
        //Bottom design end


        //Top design
        Label title = new Label();
        title.setText("GameName");
        title.setTextFill(Color.DARKBLUE);
        title.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));

        StackPane titlePane = new StackPane();
        titlePane.getChildren().add(title);
        //Top design end


        borderPane.setStyle("-fx-background-color: white;");
        borderPane.setTop(titlePane);
        borderPane.setCenter(generalPane);
        borderPane.setBottom(pane);


        Scene scene = new Scene(borderPane, 600, 600); //kutular 500*500  + sağdan ve soldan 50 piksel
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }


    public boolean checkSolution(Tile[] tiles) {

        /*
        Her hamle action sonrası bu metodu çağırcaz. Eğer true gönderirse Animasyonu başlatıp topu borulardan geçirçez ve..
        level tamamlanmış olacak.
         */


        for (int i = shapes.size() - 1; i >= 0; i--) {
            shapes.remove(i);
        }

        Point2D currentPoint = null;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] instanceof Starter) {
                shapes.add(((Starter) tiles[i]).getShape());
                currentPoint = ((Starter) tiles[i]).getPoints().get(0);  //currentPoint starterın çıkışı olarak belirlendi
                break;
            }
        }

        for (int i = 0; i < tiles.length; i++) { //Genel loop

            //Oyun bitti mi kontrolü
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[j] instanceof End) {
                    if (((End) tiles[j]).points.get(0).equals(currentPoint)) {
                        shapes.add(((End) tiles[j]).getShape());
                        return true; //Eğer current point end ile aynı noktadaysa oyun tamam
                    }
                }
            }


            //Eğer oyun tamamlanmadıysa bir sonraki currentnoktayı bulacağız


            for (int j = 0; j < tiles.length; j++) {

                if ((tiles[j] instanceof CurvedPipe) || (tiles[j] instanceof Pipe) || (tiles[j] instanceof PipeStatic)) {
                    if (tiles[j].points.get(0).equals(currentPoint)) {

                        currentPoint = tiles[j].points.get(1);

                        if (tiles[j] instanceof CurvedPipe)
                            shapes.add(((CurvedPipe) tiles[j]).getShape());
                        if (tiles[j] instanceof Pipe)
                            shapes.add(((Pipe) tiles[j]).getShape());
                        if (tiles[j] instanceof PipeStatic)
                            shapes.add(((PipeStatic) tiles[j]).getShape());

                    } else if (tiles[j].points.get(1).equals(currentPoint)) {

                        currentPoint = tiles[j].points.get(0);
                        if (tiles[j] instanceof CurvedPipe)
                            shapes.add(((CurvedPipe) tiles[j]).getShape());
                        if (tiles[j] instanceof Pipe)
                            shapes.add(((Pipe) tiles[j]).getShape());
                        if (tiles[j] instanceof PipeStatic)
                            shapes.add(((PipeStatic) tiles[j]).getShape());
                    }


                }
            }
        }


        return false; //Eğer çözüm yoksa false gönderiyor.

    }

    public void dragTile(Tile current, Tile target) {


        if (!(target instanceof EmptyFree))
            return;
        if (!((current.isMoveable) && target.isMoveable))
            return;

        centerPane.layout();
        double currentx = target.getLayoutX();
        double currenty = target.getLayoutY();
        System.out.println(currentx + " " + currenty);
        double targetx = current.getLayoutX();
        double targety = current.getLayoutY();
        System.out.println(targetx + " " + targety);

        //giden yerin koordinatı
        int currentXCoordinatexCoordinate = target.getXCoordinate();
        int currentYCoordinateyCoordinate = target.getYCoordinate();

        //gelen yerin kordinatı
        int targetXCoordinate = current.getXCoordinate();
        int targetYCoordinate = current.getYCoordinate();

        //looking for the tile that are attached
        if (!(currentXCoordinatexCoordinate - targetXCoordinate == 0 && Math.abs(currentYCoordinateyCoordinate - targetYCoordinate) == 1) && !(Math.abs(currentXCoordinatexCoordinate - targetXCoordinate) == 1 && currentYCoordinateyCoordinate - targetYCoordinate == 0))
            return;


        //y = (Integer.parseInt(boxNumber)-1) /4;

        int currentNewMatrixIndex = currentYCoordinateyCoordinate * 4 + currentXCoordinatexCoordinate;
        int targetNewMatrixIndex = targetYCoordinate * 4 + targetXCoordinate;


        gridPane.getChildren().remove(current);
        gridPane.getChildren().remove(target);

        if (current instanceof CurvedPipe) {
            ((CurvedPipe) current).setCoordinates(currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setCoordinates(targetXCoordinate, targetYCoordinate);
            ((CurvedPipe) current).setShape(((CurvedPipe) current).getStatus(), currentXCoordinatexCoordinate, currentYCoordinateyCoordinate, ((CurvedPipe) current).isEnter1ReallyEnter());
        } else if (current instanceof Pipe) {
            ((Pipe) current).setCoordinates(currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setCoordinates(targetXCoordinate, targetYCoordinate);
            ((Pipe) current).setShape(((Pipe) current).getStatus(), currentXCoordinatexCoordinate, currentYCoordinateyCoordinate, ((Pipe) current).isEnter1ReallyEnter());

        } else {
            current.setCoordinates(currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setCoordinates(targetXCoordinate, targetYCoordinate);
        }

        tiles[currentNewMatrixIndex] = current;
        tiles[targetNewMatrixIndex] = target;

        gridPane.add(current, targetXCoordinate, targetYCoordinate);
        gridPane.add(target, currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);


        TranslateTransition currentTransition = new TranslateTransition();
        currentTransition.setDuration(Duration.millis(400));
        System.out.println(currentx);
        currentTransition.setByX(currentx - targetx);
        currentTransition.setByY(currenty - targety);
        TranslateTransition targetTransition = new TranslateTransition();
        targetTransition.setDuration(Duration.millis(400));
        targetTransition.setFromX(0);
        targetTransition.setFromY(0);
        targetTransition.setToY(targety - currenty);
        targetTransition.setToX(targetx - currentx);
        currentTransition.setNode(current);
        targetTransition.setNode(target);
        targetTransition.play();
        currentTransition.play();

        targetTransition.setOnFinished(e -> {
            gridPane.getChildren().remove(target);
            target.setTranslateX(0);
            target.setTranslateY(0);
            gridPane.add(target, targetXCoordinate, targetYCoordinate);
        });


        currentTransition.setOnFinished(e -> {
            gridPane.getChildren().remove(current);
            current.setTranslateX(0);
            current.setTranslateY(0);
            gridPane.add(current, currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
        });


        numberOfMoves++;

    }



    public void animate(ArrayList<PathTransition> pathTransitions, int index) {
        for (int i = index; i < pathTransitions.size(); i++) {
            pathTransitions.get(i).play();
            index++;
            System.out.println(i);
            int finalIndex = index;
            pathTransitions.get(i).setOnFinished(e -> {
                System.out.println(finalIndex + "   " + pathTransitions.get(finalIndex));
                animate(pathTransitions, finalIndex);
            });
        }

    }
}