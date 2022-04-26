import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game extends Application {

    int level = 1;


    Circle circle;

    GridPane gridPane;
    StackPane centerPane;

    boolean levelFinished;

    BorderPane borderPane;
    boolean isSolved;
    private static int numberOfMoves;
    ComboBox unlockedLevels = new ComboBox();





    @Override
    public void start(Stage primaryStage) throws Exception {
                unlockedLevels.setValue(level);


                startGame(primaryStage);



        }

    private void playAnimation(ArrayList<Shape> shapes, Circle circle) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (Shape shape : shapes) {
            PathTransition temp = new PathTransition();
            temp.setNode(circle);
            temp.setDuration(Duration.millis(1000));
            temp.setPath(shape);
            temp.setInterpolator(Interpolator.LINEAR);
            temp.setDelay(Duration.ZERO);

            sequentialTransition.getChildren().add(temp);


        }
        sequentialTransition.play();

    }


    //TAKING THE INPUT FROM TXT







    public static void main(String[] args) {
        launch(args);
    }


    public boolean checkSolution(Tile[] tiles, ArrayList<Shape> shapes) {

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


                        if (tiles[j] instanceof CurvedPipe) {
                            ((CurvedPipe) tiles[j]).determineIsEnter1ReallyEnter(currentPoint);
                            ((CurvedPipe) tiles[j]).setShape(((CurvedPipe) tiles[j]).getStatus(), ((CurvedPipe) tiles[j]).getXCoordinate(), ((CurvedPipe) tiles[j]).getYCoordinate(), ((CurvedPipe) tiles[j]).isEnter1ReallyEnter());
                            shapes.add(((CurvedPipe) tiles[j]).getShape());
                        }
                        if (tiles[j] instanceof Pipe) {
                            ((Pipe) tiles[j]).determineIsEnter1ReallyEnter(currentPoint);
                            ((Pipe) tiles[j]).setShape(((Pipe) tiles[j]).getStatus(), ((Pipe) tiles[j]).getXCoordinate(), ((Pipe) tiles[j]).getYCoordinate(), ((Pipe) tiles[j]).isEnter1ReallyEnter());
                            shapes.add(((Pipe) tiles[j]).getShape());

                        }
                        if (tiles[j] instanceof PipeStatic) {
                            ((PipeStatic) tiles[j]).determineIsEnter1ReallyEnter(currentPoint);
                            ((PipeStatic) tiles[j]).setShape(((PipeStatic) tiles[j]).getStatus(), ((PipeStatic) tiles[j]).getXCoordinate(), ((PipeStatic) tiles[j]).getYCoordinate(), ((PipeStatic) tiles[j]).isEnter1ReallyEnter());
                            shapes.add(((PipeStatic) tiles[j]).getShape());
                        }
                        currentPoint = tiles[j].points.get(1);

                    } else if (tiles[j].points.get(1).equals(currentPoint)) {

                        if (tiles[j] instanceof CurvedPipe) {
                            ((CurvedPipe) tiles[j]).determineIsEnter1ReallyEnter(currentPoint);
                            ((CurvedPipe) tiles[j]).setShape(((CurvedPipe) tiles[j]).getStatus(), ((CurvedPipe) tiles[j]).getXCoordinate(), ((CurvedPipe) tiles[j]).getYCoordinate(), ((CurvedPipe) tiles[j]).isEnter1ReallyEnter());
                            shapes.add(((CurvedPipe) tiles[j]).getShape());
                        }
                        if (tiles[j] instanceof Pipe) {
                            ((Pipe) tiles[j]).determineIsEnter1ReallyEnter(currentPoint);
                            ((Pipe) tiles[j]).setShape(((Pipe) tiles[j]).getStatus(), ((Pipe) tiles[j]).getXCoordinate(), ((Pipe) tiles[j]).getYCoordinate(), ((Pipe) tiles[j]).isEnter1ReallyEnter());
                            shapes.add(((Pipe) tiles[j]).getShape());

                        }
                        if (tiles[j] instanceof PipeStatic) {
                            ((PipeStatic) tiles[j]).determineIsEnter1ReallyEnter(currentPoint);
                            ((PipeStatic) tiles[j]).setShape(((PipeStatic) tiles[j]).getStatus(), ((PipeStatic) tiles[j]).getXCoordinate(), ((PipeStatic) tiles[j]).getYCoordinate(), ((PipeStatic) tiles[j]).isEnter1ReallyEnter());
                            shapes.add(((PipeStatic) tiles[j]).getShape());
                        }

                        currentPoint = tiles[j].points.get(0);
                    }


                }
            }
        }


        return false; //Eğer çözüm yoksa false gönderiyor.

    }

    public void dragTile(Tile current, Tile target, Tile[] tiles) {


        if(isSolved)
            return;

        if(current instanceof EmptyFree)
            return;
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

        //looking for if the tiles that are attached
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
            ((CurvedPipe) current).setPoints();
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
        gridPane.getChildren().remove(target);
        currentTransition.play();




        currentTransition.setOnFinished(e -> {
            gridPane.getChildren().remove(current);
            current.setTranslateX(0);
            current.setTranslateY(0);
            gridPane.add(current, currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setTranslateX(0);
            target.setTranslateY(0);
            gridPane.add(target, targetXCoordinate, targetYCoordinate);
        });


        numberOfMoves++;

    }





    public Tile[] createTiles(int i){
        Tile[] tiles = new Tile[16];
        String filename = "CSE1242_spring2022_project_level";
        File file = new File(filename + i + ".txt");
        //File file = new File("CSE1242_spring2022_project_level6.txt"); //deney deneme amaçlı

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
                    circle= new Circle((50 + 125 / 2 + x * 130), (125 / 2 + y * 130), 12);
                    circle.setFill(Color.ORANGE);
                    circle.setStroke(Color.BLACK);
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

                    if (property.equals("00") || property.equals("01") || property.equals("10") || property.equals("11"))
                        tiles[Integer.parseInt(boxNumber) - 1] = new CurvedPipe(x, y, property, false);

                    else
                        tiles[Integer.parseInt(boxNumber) - 1] = new PipeStatic(x, y, property);
                }


                 else if (tileType.equals("End")) {
                    tiles[Integer.parseInt(boxNumber) - 1] = new End(x, y, property);
                }
                //Tile türü bulundu ve oluşturuldu.

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return tiles;
    }

    public void startGame(Stage primaryStage){

        primaryStage.setResizable(false);

        gridPane = new GridPane();
        borderPane = new BorderPane();
        numberOfMoves = 0;
        Tile[] tiles = createTiles(level);
        ArrayList<Shape> shapes = new ArrayList<>();

        for (Tile tile : tiles) {
            gridPane.add(tile, tile.getXCoordinate(), tile.getYCoordinate());

        }
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: gray; -fx-stroke: black;");  //Bunu sihay yapcaz resimler düzeldiğinde
        //gridPane.setGridLinesVisible(true); gerek yok


        centerPane = new StackPane();
        centerPane.setStyle("-fx-background-color: lightgray;");
        centerPane.getChildren().add(gridPane);
        centerPane.setPadding(new Insets(0, 50, 0, 50));

        Pane generalPane = new Pane();
        generalPane.getChildren().add(centerPane);
        generalPane.getChildren().add(circle);




        //Center design


        //Bottom design
        Label movesLabel = new Label();

        Button nextLevel = new Button("Next Level");
        nextLevel.setVisible(false);
        nextLevel.setOnAction(e -> {
            if (!isSolved)
                return;
            startGame(primaryStage);
            isSolved = false;
            unlockedLevels.setValue(level);
        });

        Button restart = new Button("Restart");
        restart.setOnAction(event -> {
            if(isSolved) {
                level--;
                isSolved = false;
            }
            startGame(primaryStage);
            unlockedLevels.setValue(level);
        });

        addLevelToComboBox();

        unlockedLevels.setOnAction(e->{
            level = unlockedLevels.getSelectionModel().getSelectedIndex()+1;
            unlockedLevels.setValue(level);
            startGame(primaryStage);
        });

        Label selectLevel = new Label();
        selectLevel.setText("Select Level:");
        HBox selectLevels = new HBox();
        selectLevels.setSpacing(5);
        selectLevels.getChildren().addAll(selectLevel,unlockedLevels);
        selectLevels.setAlignment(Pos.CENTER_RIGHT);




        movesLabel.setText("Moves: " + numberOfMoves); //Her hamle action sonrası bunu yazdırmayı unutma
        movesLabel.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));

        HBox buttons = new HBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(movesLabel, selectLevels , restart, nextLevel);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(0,0,0,222));


        Pane pane = new Pane();
        pane.getChildren().add(movesLabel);
        pane.getChildren().add(buttons);







        //GUI DESIGN


        //Center design



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

                if(isSolved)
                    return;

                Tile tile1 = (Tile) e.getTarget();
                Tile tile2 = (Tile) e.getPickResult().getIntersectedNode();
                dragTile(tile1, tile2, tiles);


                movesLabel.setText("Moves: " + numberOfMoves);

                isSolved = checkSolution(tiles,shapes);


                //path transition yap


                if (isSolved) {
                    playAnimation(shapes,circle);
                    level++;
                    nextLevel.setVisible(true);
                    addLevelToComboBox();



                }


                /*if (isSolved) {  //Burası yörüngeyi gösterme deneme amaçlı
                    for (int i = 0; i < shapes.size(); i++) {
                        generalPane.getChildren().add(shapes.get(i));
                    }
                }*/


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




        borderPane.setStyle("-fx-background-color: lightgray;");
        borderPane.setTop(titlePane);
        borderPane.setCenter(generalPane);
        borderPane.setBottom(pane);


        Scene scene = new Scene(borderPane, 600, 600); //kutular 500*500  + sağdan ve soldan 50 piksel
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();

    }

    public void addLevelToComboBox(){
        ObservableList levels = unlockedLevels.getItems();
        if(!levels.contains(level)){
            unlockedLevels.getItems().add(level);
        }
    }



}