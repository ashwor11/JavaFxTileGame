import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class Starter extends Tile {

    private int exitCoordinate;
    private Line shape;


    //Constructor -> Setting exitCoordinate, shape, isMoveable and image
    public Starter(int xCoordinate, int yCoordinate, String status) {
        super(xCoordinate, yCoordinate);

        setMoveable(false);


        if (status.equals("Vertical")) {
            this.setImage(new Image("file:GamePNGs/startVertical.png"));
            this.setExitCordinate(3);
        } else {
            this.setExitCordinate(4);
            Image image = new Image("file:GamePNGs/startHorizontal.png");
            this.setImage(image);
        }


        if (exitCoordinate == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() - 0.5));
        }
        else if (exitCoordinate == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
        }
        else if (exitCoordinate == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
        }
        else if (exitCoordinate == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
        }


        shape = DrawShape.drawStarter(status, xCoordinate, yCoordinate, exitCoordinate);
        shape.setStrokeWidth(5);
    }

    //Setters and getters
    // Setting coordinates. points are set in this setter method
    public int getExitCoordinate() {
        return exitCoordinate;
    }

    public void setExitCordinate(int exitCordinate) {
        this.exitCoordinate = exitCordinate;
    }

    public void setCoordinates(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
        points.clear();
        if (exitCoordinate == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() - 0.5));
        }
        else if (exitCoordinate == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
        }
        else if (exitCoordinate == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
        }
        else if (exitCoordinate == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
        }
    }


    public Line getShape() {
        return shape;
    }

    public void setShape(String status, int xCoordinate, int yCoordinate, int exitCoordinate) {
       shape = DrawShape.drawStarter(status, xCoordinate, yCoordinate, exitCoordinate);
    }

    public void setExitCoordinate(int exitCoordinate) {
        this.exitCoordinate = exitCoordinate;
    }
}