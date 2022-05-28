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


/*
*   This program is a game consists of 16 Tile 4*4 area. Each tile has specific properties. If user creates a valid path starts from Starter Tile and ends at End tile
*   with Pipe and StaticPipes a level will be completed. Two Tile's place can be switch by dragging them.
*
*   This program is written by Yusuf Demir (150120032) and Kerim Hasan Yıldırım (150120040) for Marmara University Computer Engineering Department's CSE 1242's term project.

* */

//For a GUI application class must extend Application
public class Game extends Application {

    //Declaring global variables and initialize some of them.
    int level = 1;
    int lastLevel;
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
    MediaPlayer playDragTileSound = new MediaPlayer(dragTileSound);






    //A class extends Application must override start method. For starting startGame method invoked.
    @Override
    public void start(Stage primaryStage) {
                unlockedLevels.setValue(level);

                startGame(primaryStage);

        }

    //Master method for the game all thw works done inside here
    private void startGame(Stage primaryStage){
        isSolved = false;

        isSolved = false;

        //Creating current level
        createTiles(level);


        //If there is no level to play show an alert
        if (lastLevel == level){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You won the game");
            level--;
            unlockedLevels.getItems().remove(level);
            alert.show();
            isSolved = false;
            return;

        }

        playCorrect.stop();
        playGameStart.stop();
        playLevelStart.stop();

        if (level == 1)
            playGameStart.play();
        else
            playLevelStart.play();


        //Center GUI design

        primaryStage.setResizable(false);

        gridPane = new GridPane();
        borderPane = new BorderPane();
        numberOfMoves = 0;
        shapes = new ArrayList<>();

        //Adding tiles to gridPane
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


        //Bottom GUI design

        movesLabel = new Label();

        nextLevel = new Button("Next Level");
        nextLevel.setVisible(false);
        //If nextLevel button pressed render the next level all over again
        nextLevel.setOnAction(e -> {
            if (!isSolved)
                return;
            startGame(primaryStage);
            isSolved = false;
            unlockedLevels.setValue(level);
        });

        restart = new Button("Restart");
        //If nextLevel button pressed render the same level all over again
        restart.setOnAction(event -> {

            unlockedLevels.setDisable(false);
            if(isSolved) {
                level--;
                isSolved = false;
                sequentialTransition.stop();
            }
            startGame(primaryStage);

            unlockedLevels.setValue(level);
        });
        //If level is done next level added to the pool of Combo Box
        addLevelToComboBox();

        //If value of unlockedLevel is changed, changed valueth level will be displayed
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



        //Moves Label
        movesLabel.setText("Moves: " + numberOfMoves);
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

            //If any of the tile is pressed this event handler will be invoked.
            tile.setOnMouseReleased(e -> {


                if(isSolved)
                    return;
                //Get the tile pressed
                Tile tile1 = (Tile) e.getTarget();
                Tile tile2;
                try {
                    //Get the tile mouse released
                    tile2 = (Tile) e.getPickResult().getIntersectedNode();

                    //If mouse released somewhere is not a Tile, Console should not print anything
                }catch(Exception ClassCastException){
                    return;
                }
                //DragTile animation
                dragTile(tile1, tile2, tiles);

                movesLabel.setText("Moves: " + numberOfMoves);
                isSolved = checkSolution(tiles,shapes);

                //Show primaryStage after the changes.
                primaryStage.show();
            });
        }


        //Top GUI design
        title = new Label();
        title.setText("Ball Piping");
        title.setTextFill(Color.DARKBLUE);
        title.setFont(Font.font("Times New Roman", FontWeight.BLACK, FontPosture.REGULAR, 36));

        titlePane = new StackPane();
        titlePane.getChildren().add(title);


        //Top design end

        borderPane.setStyle("-fx-background-color: lightgray;");
        borderPane.setTop(titlePane);
        borderPane.setCenter(generalPane);
        borderPane.setBottom(pane);


        scene = new Scene(borderPane, 600, 600); //Boxes 125*125*4  + 50 pixels from right and left
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ball Piping");
        primaryStage.show();

    }

    //This methods takes global variable level and initialize tiles reading a txt file line by line.
    private void createTiles(int level){
        //Initializing tiles to an empty array
        tiles = new Tile[16];
        String filename = "CSE1242_spring2022_project_level";
        File file = new File(filename + level + ".txt");
        //Reading file
        try (Scanner sc = new Scanner(file)) {
            //If file has next line continue
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                //If this line is blank go to next line
                if (line.equals(""))
                    continue;
                //Spliting the line by ,
                String[] values = line.split(",");
                //Getting parameters for creating tile
                String boxNumber = values[0];
                String tileType = values[1];
                String property = values[2];  //property means "horizontal","vertical","none","free" and "curved pipe type like 00 01 10 11"

                //Coordinates of current tile
                int x;
                int y;

                y = (Integer.parseInt(boxNumber) - 1) / 4;
                x = (Integer.parseInt(boxNumber) - 1) % 4;

                //Creating tile by the tile Type
                switch (tileType) {
                    case "Starter":
                        circle = new Circle((50 + 125.0 / 2 + x * 130), (125.0 / 2 + y * 130), 12);
                        circle.setFill(Color.ORANGE);
                        circle.setStroke(Color.BLACK);
                        tiles[Integer.parseInt(boxNumber) - 1] = new Starter(x, y, property);
                        break;
                    case "Empty":
                        //If property equals none Tile must be Empty
                        if (property.equals("none"))
                            tiles[Integer.parseInt(boxNumber) - 1] = new Empty(x, y);
                        //If it is not none so it must be EmptyFree
                        else
                            tiles[Integer.parseInt(boxNumber) - 1] = new EmptyFree(x, y);

                        break;
                    case "Pipe":

                        if (property.equals("Horizontal") || property.equals("Vertical"))  //Pipe
                            tiles[Integer.parseInt(boxNumber) - 1] = new Pipe(x, y, property);
                        else  //Curved pipe
                            tiles[Integer.parseInt(boxNumber) - 1] = new CurvedPipe(x, y, property);

                        break;
                    case "PipeStatic":
                            if(property.equals("Horizontal") || property.equals("Vertical"))
                             tiles[Integer.parseInt(boxNumber) - 1] = new PipeStatic(x, y, property);
                            else
                                tiles[Integer.parseInt(boxNumber) - 1] = new CurvedPipe(x, y, property, false);
                        break;
                    case "End":
                        tiles[Integer.parseInt(boxNumber) - 1] = new End(x, y, property);
                        break;
                }
                //Tile type has been found and created.

            }
            //If there is no file matches with name so there is no level to play. User has won the game.
        } catch (FileNotFoundException e) {
            isGameFinished = true;
            lastLevel = level;
        }


    }

    //Drag Tile Animation
    private void dragTile(Tile pressedTile, Tile releasedTile, Tile[] tiles) {
        //If there is any DragTileSound playing stopping it. Can be cause to a bug
        playDragTileSound.stop();

        //If level is solved don't let dragging tiles
        if(isSolved)
            return;

        //If the tile mouse pressed is an instance of Empty Free return. Because the pressed tile must not be  EmptyFree
        if(pressedTile instanceof EmptyFree)
            return;
        //If the tile mouse released is not an instance of Empty Free return. Because the released tile must be  EmptyFree
        if (!(releasedTile instanceof EmptyFree))
            return;
        //If pressed or released tiles' isMoveable false return. Because they must be true to move.
        if (!((pressedTile.isMoveable) && releasedTile.isMoveable))
            return;

        //Getting coordinates the tiles to help when changing their places. These are pixels.
        double pressedTileLayoutX = releasedTile.getLayoutX();
        double pressedTileLayoutY = releasedTile.getLayoutY();
        double releasedTileLayoutX = pressedTile.getLayoutX();
        double releasedTileLayoutY = pressedTile.getLayoutY();
        //new coordinates of pressed at gridPane. Just integers.
        int pressedTileNewX = releasedTile.getXCoordinate();
        int pressedTileNewY = releasedTile.getYCoordinate();

        //new coordinates of released at gridPane. Just integers.
        int releasedTileNewX = pressedTile.getXCoordinate();
        int releasedTileNewY = pressedTile.getYCoordinate();

        //looking for if the tiles that are attached. If they are return
        if (!(pressedTileNewX - releasedTileNewX == 0 && Math.abs(pressedTileNewY - releasedTileNewY) == 1) && !(Math.abs(pressedTileNewX - releasedTileNewX) == 1 && pressedTileNewY - releasedTileNewY == 0))
            return;


        //y = (Integer.parseInt(boxNumber)-1) /4;
        // Calculating the positions at gridPane
        int pressedTileNewMatrixIndex = pressedTileNewY * 4 + pressedTileNewX;
        int releasedTileNewMatrixIndex = releasedTileNewY * 4 + releasedTileNewX;


        gridPane.getChildren().remove(pressedTile);
        gridPane.getChildren().remove(releasedTile);

        //Creating new Tiles with new coordinates.
        if (pressedTile instanceof CurvedPipe) {
            playDragTileSound.play();
            pressedTile.setCoordinates(pressedTileNewX, pressedTileNewY);
            releasedTile.setCoordinates(releasedTileNewX, releasedTileNewY);
            ((CurvedPipe) pressedTile).setShape(((CurvedPipe) pressedTile).getStatus(), pressedTileNewX, pressedTileNewY, ((CurvedPipe) pressedTile).isEnter1ReallyEnter());
            ((CurvedPipe) pressedTile).setPoints();
        } else if (pressedTile instanceof Pipe) {
            playDragTileSound.play();
            pressedTile.setCoordinates(pressedTileNewX, pressedTileNewY);
            releasedTile.setCoordinates(releasedTileNewX, releasedTileNewY);
            ((Pipe) pressedTile).setShape(((Pipe) pressedTile).getStatus(), pressedTileNewX, pressedTileNewY, ((Pipe) pressedTile).isEnter1ReallyEnter());

        } else {
            playDragTileSound.play();
            pressedTile.setCoordinates(pressedTileNewX, pressedTileNewY);
            releasedTile.setCoordinates(releasedTileNewX, releasedTileNewY);
        }

        //Changing tiles places in tiles array
        tiles[pressedTileNewMatrixIndex] = pressedTile;
        tiles[releasedTileNewMatrixIndex] = releasedTile;

        //Adding new pressed tile to gridPane
        gridPane.add(pressedTile, releasedTileNewX, releasedTileNewY);

        //Creating a TranslateTransition for pressed Tile
        //Not creating for released because it should be seen as just moving the pressed Tile
        TranslateTransition pressedTileTransition = new TranslateTransition();
        pressedTileTransition.setDuration(Duration.millis(300));
        pressedTileTransition.setByX(pressedTileLayoutX - releasedTileLayoutX);
        pressedTileTransition.setByY(pressedTileLayoutY - releasedTileLayoutY);
        pressedTileTransition.setNode(pressedTile);


        pressedTileTransition.play();




        pressedTileTransition.setOnFinished(e -> {
            gridPane.getChildren().remove(pressedTile);
            pressedTile.setTranslateX(0);
            pressedTile.setTranslateY(0);
            gridPane.add(pressedTile, pressedTileNewX, pressedTileNewY);
            releasedTile.setTranslateX(0);
            releasedTile.setTranslateY(0);
            gridPane.add(releasedTile, releasedTileNewX, releasedTileNewY);
            //if isSolved true play circle animation
            if (isSolved)
                playAnimation(shapes,circle);


        });
        //after dragging increment numberOfMoves by 1
        numberOfMoves++;
    }

    //checkSolution invokes after dragtile method
    private boolean checkSolution(Tile[] tiles, ArrayList<Shape> shapes) {



        //Clearing shapes for a new shape
        shapes.clear();

        Point2D currentPoint = null;

        //Finding starter shape draw road
        for (Tile tile : tiles) {
            if (tile instanceof Starter) {
                shapes.add(((Starter) tile).getShape());
                currentPoint = tile.getPoints().get(0);  //currentPoint set as exit of Starter.
                break;
            }
        }

        //If road comes to End tile
        for (int i = 0; i < tiles.length; i++) { //General loop

            //If level is completed
            for (Tile tile : tiles) {
                if (tile instanceof End) {
                    if (tile.points.get(0).equals(currentPoint)) {
                        shapes.add(((End) tile).getShape());
                        return true; //If current point and endpoint is equal return true.
                    }
                }
            }


            //If level is not completed finding next currentPoint if there is.


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


        return false; //If there is no currentPoint with endPoint return false

    }

    //Creating a whole animation consists of small piece of animations
    private void playAnimation(ArrayList<Shape> shapes, Circle circle) {
        //Initializing a new SequentialTransition
        sequentialTransition = new SequentialTransition();

        //Adding all the small animations in to the sequentialTransition
        for (Shape shape : shapes) {
            PathTransition temp = new PathTransition();
            temp.setNode(circle);
            temp.setDuration(Duration.millis(700));
            temp.setPath(shape);
            temp.setInterpolator(Interpolator.LINEAR);
            temp.setDelay(Duration.ZERO);

            sequentialTransition.getChildren().add(temp);


        }
        if(lastLevel != level)
            level++;
        //Start animation
        unlockedLevels.setDisable(true);
        sequentialTransition.play();



        //When animation is done some changes will be made
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
            addLevelToComboBox();
            unlockedLevels.setDisable(false);


            if (level == 1)
                playGameStart.stop();
            else
                playLevelStart.stop();
            playCorrect.play();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addLevelToComboBox(){
        ObservableList<Integer> levels = unlockedLevels.getItems();
        if(!levels.contains(level)){
            unlockedLevels.getItems().add(level);
        }
    }



}