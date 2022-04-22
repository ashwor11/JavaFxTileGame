import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

import java.util.ArrayList;


public class Tile extends ImageView {
    protected int xCoordinate;
    protected int yCoordinate;

    protected ArrayList<Point2D> points = new ArrayList<>(2);


    protected boolean isMoveable;

    public Tile(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        super.setFitHeight(125);
        super.setFitWidth(125);
    }





    // 3                  3.5
    //2                   2.5



    public int getXCoordinate() {
        return xCoordinate;
    }
    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;

    }
    public int getYCoordinate() {
        return yCoordinate;
    }
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public boolean isMoveable() {
        return isMoveable;
    }
    public void setMoveable(boolean isMoveable) {
        this.isMoveable = isMoveable;
    }

    public void setCoordinates(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }
}

