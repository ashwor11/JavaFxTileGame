import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

import java.io.File;

public class Pipe extends Tile {
    private int enter1;
    private int enter2;
    private String status;
    private Line shape;
    private boolean isEnter1ReallyEnter;
    private Point2D enterPoint;

    public Pipe(int xCoordinate, int yCoordinate, String status) {
        super(xCoordinate, yCoordinate);

        this.status = status;

        setMoveable(true);
        if(status.equals("Horizontal")){
            enter1 = 4;
            enter2 = 2;
            this.setImage(new Image("file:GamePNGs/pipeHorizontal.png"));
        } else  {
            enter1 = 1;
            enter2 = 3;
            System.out.println(new File(".").getAbsolutePath());
            this.setImage(new Image("file:GamePNGs/pipeVertical.png"));
        }

        if (enter1 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
            enterPoint = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter1 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
            enterPoint = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter1 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
            enterPoint = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter1 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
            enterPoint = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }

        if (enter2 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
        }
        else if (enter2 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
        }
        else if (enter2 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
        }
        else if (enter2 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
        }


        //Pipe'ın iki girişi de point olarak bulundu



        shape = DrawShape.drawPipe(status, xCoordinate, yCoordinate,  isEnter1ReallyEnter);
        shape.setStrokeWidth(5);


    }

    public void setCoordinates(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
        points.clear();
        if (enter1 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
            enterPoint = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter1 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
            enterPoint = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter1 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
            enterPoint = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter1 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
            enterPoint = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }

        if (enter2 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
        }
        else if (enter2 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
        }
        else if (enter2 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
        }
        else if (enter2 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
        }


    }


    public Line getShape() {
        return shape;
    }

    public void setShape(String status, int xCoordinate, int yCoordinate, boolean isEnter1ReallyEnter) {
       shape = DrawShape.drawPipe(status, xCoordinate, yCoordinate, isEnter1ReallyEnter);
    }

    public int getEnter1() {
        return enter1;
    }

    public void setEnter1(int enter1) {
        this.enter1 = enter1;
    }

    public int getEnter2() {
        return enter2;
    }

    public void setEnter2(int enter2) {
        this.enter2 = enter2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEnter1ReallyEnter() {
        return isEnter1ReallyEnter;
    }

    public void setEnter1ReallyEnter(boolean enter1ReallyEnter) {
        isEnter1ReallyEnter = enter1ReallyEnter;
    }

    public void determineIsEnter1ReallyEnter(Point2D point) {

        if (enterPoint.equals(point))
            isEnter1ReallyEnter = true;
        else
            isEnter1ReallyEnter = false;
    }
}
