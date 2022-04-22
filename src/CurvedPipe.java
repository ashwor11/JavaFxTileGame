import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;

public class CurvedPipe extends Tile{
    private int enter1;
    private int enter2;
    private String status1;
    private boolean isEnter1ReallyEnter;
    private Arc shape;



    public CurvedPipe(int xCoordinate, int yCoordinate, String status) {
        super(xCoordinate, yCoordinate);
        status1 = status;
        setMoveable(true);
        if(status.equals("00")){
            enter1 = 1;
            enter2 = 4;
            this.setImage(new Image("file:resimler/00.png"));
        } else if (status.equals("01"))  {
            enter1 = 1;
            enter2 = 2;
            this.setImage(new Image("file:resimler/01.png"));
        } else if (status.equals("10"))  {
            enter1 = 4;
            enter2 = 3;
            this.setImage(new Image("file:resimler/10.png"));
        } else {
            enter1 = 2;
            enter2 = 3;
            this.setImage(new Image("file:resimler/11.png"));
        }

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



        shape = DrawShape.drawCurvedPipe(status, xCoordinate, yCoordinate, isEnter1ReallyEnter);
        shape.setStrokeWidth(5);



    }


    public String getStatus(){
        return this.status1;
    }
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

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public boolean isEnter1ReallyEnter() {
        return isEnter1ReallyEnter;
    }

    public void setEnter1ReallyEnter(boolean enter1ReallyEnter) {
        isEnter1ReallyEnter = enter1ReallyEnter;
    }
}
