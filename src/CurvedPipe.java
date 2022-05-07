import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;

public class CurvedPipe extends Tile{
    private int enter1;
    private int enter2;
    private String status;
    private boolean isEnter1ReallyEnter;
    private Arc shape;
    private Point2D enter1Point, enter2Point;


    //Constructor -> Setting enter1, enter2, shape, isMoveable and image
    public CurvedPipe(int xCoordinate, int yCoordinate, String status) {
        super(xCoordinate, yCoordinate);
        this.status = status;
        setMoveable(true);
        if(status.equals("00")){
            enter1 = 1;
            enter2 = 4;
            this.setImage(new Image("file:GamePNGs/curvedPipe00.png"));
        } else if (status.equals("01"))  {
            enter1 = 1;
            enter2 = 2;
            this.setImage(new Image("file:GamePNGs/curvedPipe01.png"));
        } else if (status.equals("10"))  {
            enter1 = 4;
            enter2 = 3;
            this.setImage(new Image("file:GamePNGs/curvedPipe10.png"));
        } else {
            enter1 = 2;
            enter2 = 3;
            this.setImage(new Image("file:GamePNGs/curvedPipe11.png"));
        }

        if (enter1 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
            enter1Point = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter1 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
            enter1Point = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter1 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
            enter1Point = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter1 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
            enter1Point = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }
        if (enter2 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
            enter2Point = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter2 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
            enter2Point = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter2 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
            enter2Point = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter2 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
            enter2Point = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }

        // Drawing shape for animation
        shape = DrawShape.drawCurvedPipe(status, xCoordinate, yCoordinate, isEnter1ReallyEnter);
        shape.setStrokeWidth(5);

    }

    //Constructor -> Setting images and enters
    //UnnecessaryParam is required because two constructor is required
    public CurvedPipe(int xCoordinate, int yCoordinate, String status, boolean unnecessaryParam) {
        super(xCoordinate, yCoordinate);
        this.status = status;
        setMoveable(false);
        if(status.equals("00")){
            enter1 = 1;
            enter2 = 4;
            this.setImage(new Image("file:GamePNGs/curvedPipeStatic00.png"));
        } else if (status.equals("01"))  {
            enter1 = 1;
            enter2 = 2;
            this.setImage(new Image("file:GamePNGs/curvedPipeStatic01.png"));
        } else if (status.equals("10"))  {
            enter1 = 4;
            enter2 = 3;
            this.setImage(new Image("file:GamePNGs/curvedPipeStatic10.png"));
        } else {
            enter1 = 2;
            enter2 = 3;
            this.setImage(new Image("file:GamePNGs/curvedPipeStatic11.png"));
        }

        if (enter1 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
            enter1Point = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter1 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
            enter1Point = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter1 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
            enter1Point = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter1 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
            enter1Point = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }
        if (enter2 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
            enter2Point = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter2 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
            enter2Point = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter2 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
            enter2Point = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter2 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
            enter2Point = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }

        shape = DrawShape.drawCurvedPipe(status, xCoordinate, yCoordinate, isEnter1ReallyEnter);
        shape.setStrokeWidth(5);

    }

    //Getter and setters
    public String getStatus(){
        return this.status;
    }
    // Setting coordinates. points are set in this setter method
    public void setCoordinates(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
        points.clear();
        if (enter1 == 1) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate()-0.5));
        }
        else if (enter1 == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate()));
        }
        else if (enter1 == 3) {
            points.add(new Point2D(getXCoordinate(), getYCoordinate() + 0.5));
        }
        else if (enter1 == 4) {
            points.add(new Point2D(getXCoordinate() - 0.5, getYCoordinate()));
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

    public void setPoints() {

        if (enter1 == 1) {
            enter1Point = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter1 == 2) {
            enter1Point = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter1 == 3) {
            enter1Point = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter1 == 4) {
            enter1Point = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }
        if (enter2 == 1) {
            enter2Point = new Point2D(getXCoordinate(), getYCoordinate()-0.5);
        }
        else if (enter2 == 2) {
            enter2Point = new Point2D(getXCoordinate() + 0.5, getYCoordinate());
        }
        else if (enter2 == 3) {
            enter2Point = new Point2D(getXCoordinate(), getYCoordinate() + 0.5);
        }
        else if (enter2 == 4) {
            enter2Point = new Point2D(getXCoordinate() - 0.5, getYCoordinate());
        }
        points.set(0, enter1Point);
        points.set(1, enter2Point);

    }


    public Arc getShape() {
        return shape;
    }

    public void setShape(String status, int xCoordinate, int yCoordinate, boolean isEnter1ReallyEnter) {
       shape = DrawShape.drawCurvedPipe(status, xCoordinate, yCoordinate, isEnter1ReallyEnter);
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

        if (enter1Point.equals(point))
            isEnter1ReallyEnter = true;
        else
            isEnter1ReallyEnter = false;
    }


}
