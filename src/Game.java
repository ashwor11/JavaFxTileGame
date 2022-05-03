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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    Tile[] tiles;
    ArrayList<Shape> shapes;
    boolean isGameFinished, isSolved;
    private static int numberOfMoves;
    SequentialTransition sequentialTransition;



    //Nodes
    Circle circle;
    GridPane gridPane;
    StackPane centerPane;
    BorderPane borderPane;
    Pane generalPane, pane;
    Scene scene;
    StackPane titlePane;

    Label movesLabel, title, selectLevel;
    HBox buttons, selectLevels;
    Button restart,nextLevel;
    ComboBox<Integer> unlockedLevels = new ComboBox<>();




    //Sounds
    Media gameStart = new Media(new File("soundtrack/gameStart.mp4").toURI().toString());
    MediaPlayer playGameStart = new MediaPlayer(gameStart);
    Media correct = new Media(new File("soundtrack/correct.mp4").toURI().toString());
    MediaPlayer playCorrect = new MediaPlayer(correct);

    Media levelStart = new Media(new File("soundtrack/levelStart.mp4").toURI().toString());
    MediaPlayer playLevelStart = new MediaPlayer(levelStart);

    Media dragTileSound = new Media(new File("soundtrack/dragTileSound.wav").toURI().toString());
    MediaPlayer playdragTileSound = new MediaPlayer(dragTileSound);







    @Override
    public void start(Stage primaryStage) {
                unlockedLevels.setValue(level);

                startGame(primaryStage);

        }

    private void playAnimation(ArrayList<Shape> shapes, Circle circle) {
        sequentialTransition = new SequentialTransition();

        for (Shape shape : shapes) {
            PathTransition temp = new PathTransition();
            temp.setNode(circle);
            temp.setDuration(Duration.millis(700));
            temp.setPath(shape);
            temp.setInterpolator(Interpolator.LINEAR);
            temp.setDelay(Duration.ZERO);

            sequentialTransition.getChildren().add(temp);


        }
        sequentialTransition.play();



        sequentialTransition.setOnFinished(e -> {
            Label bravo = new Label();
            bravo.setText("Congratulations!");
            bravo.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));
            bravo.setTextFill(Color.DARKBLUE);
            bravo.setAlignment(Pos.CENTER);
            StackPane bravoPane = new StackPane();
            bravoPane.getChildren().add(bravo);
            borderPane.setTop(bravoPane);
            nextLevel.setVisible(true);
            level++;
            addLevelToComboBox();

            if (level == 1)
                playGameStart.stop();
            else
                playLevelStart.stop();
            playCorrect.play();
        });

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


        shapes.clear();

        Point2D currentPoint = null;

        for (Tile tile : tiles) {
            if (tile instanceof Starter) {
                shapes.add(((Starter) tile).getShape());
                currentPoint = tile.getPoints().get(0);  //currentPoint starterın çıkışı olarak belirlendi
                break;
            }
        }

        for (int i = 0; i < tiles.length; i++) { //Genel loop

            //Oyun bitti mi kontrolü
            for (Tile tile : tiles) {
                if (tile instanceof End) {
                    if (tile.points.get(0).equals(currentPoint)) {
                        shapes.add(((End) tile).getShape());
                        return true; //Eğer current point end ile aynı noktadaysa oyun tamam
                    }
                }
            }


            //Eğer oyun tamamlanmadıysa bir sonraki currentnoktayı bulacağız


            for (Tile tile : tiles) {

                if ((tile instanceof CurvedPipe) || (tile instanceof Pipe) || (tile instanceof PipeStatic)) {
                    if (tile.points.get(0).equals(currentPoint)) {


                        if (tile instanceof CurvedPipe) {
                            ((CurvedPipe) tile).determineIsEnter1ReallyEnter(currentPoint);
                            ((CurvedPipe) tile).setShape(((CurvedPipe) tile).getStatus(),  tile.getXCoordinate(),  tile.getYCoordinate(), ((CurvedPipe) tile).isEnter1ReallyEnter());
                            shapes.add(((CurvedPipe) tile).getShape());
                        }
                        if (tile instanceof Pipe) {
                            ((Pipe) tile).determineIsEnter1ReallyEnter(currentPoint);
                            ((Pipe) tile).setShape(((Pipe) tile).getStatus(),  tile.getXCoordinate(),  tile.getYCoordinate(), ((Pipe) tile).isEnter1ReallyEnter());
                            shapes.add(((Pipe) tile).getShape());

                        }
                        if (tile instanceof PipeStatic) {
                            ((PipeStatic) tile).determineIsEnter1ReallyEnter(currentPoint);
                            ((PipeStatic) tile).setShape(((PipeStatic) tile).getStatus(),  tile.getXCoordinate(),  tile.getYCoordinate(), ((PipeStatic) tile).isEnter1ReallyEnter());
                            shapes.add(((PipeStatic) tile).getShape());
                        }
                        currentPoint = tile.points.get(1);

                    } else if (tile.points.get(1).equals(currentPoint)) {

                        if (tile instanceof CurvedPipe) {
                            ((CurvedPipe) tile).determineIsEnter1ReallyEnter(currentPoint);
                            ((CurvedPipe) tile).setShape(((CurvedPipe) tile).getStatus(), tile.getXCoordinate(),  tile.getYCoordinate(), ((CurvedPipe) tile).isEnter1ReallyEnter());
                            shapes.add(((CurvedPipe) tile).getShape());
                        }
                        if (tile instanceof Pipe) {
                            ((Pipe) tile).determineIsEnter1ReallyEnter(currentPoint);
                            ((Pipe) tile).setShape(((Pipe) tile).getStatus(), tile.getXCoordinate(),  tile.getYCoordinate(), ((Pipe) tile).isEnter1ReallyEnter());
                            shapes.add(((Pipe) tile).getShape());

                        }
                        if (tile instanceof PipeStatic) {
                            ((PipeStatic) tile).determineIsEnter1ReallyEnter(currentPoint);
                            ((PipeStatic) tile).setShape(((PipeStatic) tile).getStatus(),  tile.getXCoordinate(), tile.getYCoordinate(), ((PipeStatic) tile).isEnter1ReallyEnter());
                            shapes.add(((PipeStatic) tile).getShape());
                        }

                        currentPoint = tile.points.get(0);
                    }


                }
            }
        }


        return false; //Eğer çözüm yoksa false gönderiyor.

    }

    public void dragTile(Tile current, Tile target, Tile[] tiles) {
        playdragTileSound.stop();

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
        double targetx = current.getLayoutX();
        double targety = current.getLayoutY();
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
            playdragTileSound.play();
            current.setCoordinates(currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setCoordinates(targetXCoordinate, targetYCoordinate);
            ((CurvedPipe) current).setShape(((CurvedPipe) current).getStatus(), currentXCoordinatexCoordinate, currentYCoordinateyCoordinate, ((CurvedPipe) current).isEnter1ReallyEnter());
            ((CurvedPipe) current).setPoints();
        } else if (current instanceof Pipe) {
            playdragTileSound.play();
            current.setCoordinates(currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setCoordinates(targetXCoordinate, targetYCoordinate);
            ((Pipe) current).setShape(((Pipe) current).getStatus(), currentXCoordinatexCoordinate, currentYCoordinateyCoordinate, ((Pipe) current).isEnter1ReallyEnter());

        } else {
            playdragTileSound.play();
            current.setCoordinates(currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);
            target.setCoordinates(targetXCoordinate, targetYCoordinate);
        }

        tiles[currentNewMatrixIndex] = current;
        tiles[targetNewMatrixIndex] = target;

        gridPane.add(current, targetXCoordinate, targetYCoordinate);
        gridPane.add(target, currentXCoordinatexCoordinate, currentYCoordinateyCoordinate);


        TranslateTransition currentTransition = new TranslateTransition();
        currentTransition.setDuration(Duration.millis(300));
        currentTransition.setByX(currentx - targetx);
        currentTransition.setByY(currenty - targety);
        currentTransition.setNode(current);

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
            if (isSolved)
                playAnimation(shapes,circle);


        });
        numberOfMoves++;
    }





    public void createTiles(int i){
        tiles = new Tile[16];
        String filename = "CSE1242_spring2022_project_level";
        File file = new File(filename + i + ".txt");

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals(""))
                    continue;
                String[] values = line.split(",");

                String boxNumber = values[0];
                String tileType = values[1];
                String property = values[2];  //property means "hoziontal","vertical","none","free" and "curved pipe type"

                int x;  //Coordinates of current tile
                int y;

                y = (Integer.parseInt(boxNumber) - 1) / 4;
                x = (Integer.parseInt(boxNumber) - 1) % 4;


                switch (tileType) {
                    case "Starter":
                        circle = new Circle((50 + 125.0 / 2 + x * 130), (125.0 / 2 + y * 130), 12);
                        circle.setFill(Color.ORANGE);
                        circle.setStroke(Color.BLACK);
                        tiles[Integer.parseInt(boxNumber) - 1] = new Starter(x, y, property);
                        break;
                    case "Empty":   //Empty free mi yoksa none mı kontrol edip ona göre yapmak gerek

                        if (property.equals("none"))
                            tiles[Integer.parseInt(boxNumber) - 1] = new Empty(x, y);
                        else
                            tiles[Integer.parseInt(boxNumber) - 1] = new EmptyFree(x, y);

                        break;
                    case "Pipe":

                        if (property.equals("Horizontal") || property.equals("Vertical"))  //Normal pipe
                            tiles[Integer.parseInt(boxNumber) - 1] = new Pipe(x, y, property);
                        else  //Curved pipe
                            tiles[Integer.parseInt(boxNumber) - 1] = new CurvedPipe(x, y, property);

                        break;
                    case "PipeStatic":

                        if (property.equals("00") || property.equals("01") || property.equals("10") || property.equals("11"))
                            tiles[Integer.parseInt(boxNumber) - 1] = new CurvedPipe(x, y, property, false);

                        else
                            tiles[Integer.parseInt(boxNumber) - 1] = new PipeStatic(x, y, property);
                        break;
                    case "End":
                        tiles[Integer.parseInt(boxNumber) - 1] = new End(x, y, property);
                        break;
                }
                //Tile türü bulundu ve oluşturuldu.

            }
        } catch (FileNotFoundException e) {
            isGameFinished = true;
        }


    }

    public void startGame(Stage primaryStage){

        createTiles(level);


        if (isGameFinished){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You won the game");
            level--;
            unlockedLevels.getItems().remove(level);
            alert.show();
            return;

        }

        playCorrect.stop();

        if (level == 1)
             playGameStart.play();
        else
            playLevelStart.play();


        //Center design

        primaryStage.setResizable(false);

        gridPane = new GridPane();
        borderPane = new BorderPane();
        numberOfMoves = 0;
        shapes = new ArrayList<>();

        for (Tile tile : tiles) {
            gridPane.add(tile, tile.getXCoordinate(), tile.getYCoordinate());

        }
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setStyle("-fx-background-color: gray; -fx-stroke: black;");


        centerPane = new StackPane();
        centerPane.setStyle("-fx-background-color: lightgray;");
        centerPane.getChildren().add(gridPane);
        centerPane.setPadding(new Insets(0, 50, 0, 50));

        generalPane = new Pane();
        generalPane.getChildren().add(centerPane);
        generalPane.getChildren().add(circle);


        //Bottom design

        movesLabel = new Label();

        nextLevel = new Button("Next Level");
        nextLevel.setVisible(false);
        nextLevel.setOnAction(e -> {
            if (!isSolved)
                return;
            startGame(primaryStage);
            isSolved = false;
            unlockedLevels.setValue(level);
        });

        restart = new Button("Restart");
        restart.setOnAction(event -> {
            if(isSolved) {
                level--;
                isSolved = false;
                sequentialTransition.stop();
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

        selectLevel = new Label();
        selectLevel.setText("Select Level:");
        selectLevels = new HBox();
        selectLevels.setSpacing(5);
        selectLevels.getChildren().addAll(selectLevel,unlockedLevels);
        selectLevels.setAlignment(Pos.CENTER_RIGHT);




        movesLabel.setText("Moves: " + numberOfMoves); //Her hamle action sonrası bunu yazdırmayı unutma
        movesLabel.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));

        buttons = new HBox();
        buttons.setSpacing(20);
        buttons.getChildren().addAll(movesLabel, selectLevels , restart, nextLevel);
        buttons.setAlignment(Pos.CENTER_RIGHT);
        buttons.setPadding(new Insets(0,0,0,222));


        pane = new Pane();
        pane.getChildren().add(movesLabel);
        pane.getChildren().add(buttons);


        // Drag tile listen
        for (Tile tile : tiles) {

            tile.setOnMouseReleased(e -> {


                if(isSolved)
                    return;

                Tile tile1 = (Tile) e.getTarget();
                Tile tile2;
                try {
                    tile2 = (Tile) e.getPickResult().getIntersectedNode();
                }catch(Exception ClassCastException){
                    return;
                }

                dragTile(tile1, tile2, tiles);
                
                movesLabel.setText("Moves: " + numberOfMoves);
                isSolved = checkSolution(tiles,shapes);
                

                primaryStage.show();
            });
        }


        //Top design
        title = new Label();
        title.setText("GameName");
        title.setTextFill(Color.DARKBLUE);
        title.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));

        titlePane = new StackPane();
        titlePane.getChildren().add(title);
        
        
        //Top design end
        
        borderPane.setStyle("-fx-background-color: lightgray;");
        borderPane.setTop(titlePane);
        borderPane.setCenter(generalPane);
        borderPane.setBottom(pane);


        scene = new Scene(borderPane, 600, 600); //kutular 500*500  + sağdan ve soldan 50 piksel
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.show();

    }

    public void addLevelToComboBox(){
        ObservableList<Integer> levels = unlockedLevels.getItems();
        if(!levels.contains(level)){
            unlockedLevels.getItems().add(level);
        }
    }



}