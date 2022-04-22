import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class End extends Tile {

    int enterCordinate;
    private Line shape;

    public End(int xCoordinate, int yCoordinate, String status) {
        super(xCoordinate,yCoordinate);

        setMoveable(false);

        if (status.equals("Vertical")) {

            this.setImage(new Image("file:resimler/enddikey.png"));
            this.setEnterCordinate(3);
        }
        else {
            this.setEnterCordinate(4);
            this.setImage(new Image("file:resimler/endyatay.png"));
        }

        if (enterCordinate == 1) {
            points.add(new Point2D(getXCoordinate() , getYCoordinate() - 0.5));
        }
        else if (enterCordinate == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate() ));   // 1 sesim geliyor mu yukarı bakmıyor muydu yukarı 0.5 olmaz mı aynen ortası 0,0 ise yukarsısı -0.
        }
        else if (enterCordinate == 3) {
            points.add(new Point2D(getXCoordinate()  , getYCoordinate() + 0.5)); //bu artı
        }
        else if (enterCordinate == 4) {
            points.add(new Point2D(getXCoordinate() -0.5, getYCoordinate()));
        }


        //End'in girişi point olarak eklendi



        shape = DrawShape.drawEnd(status, xCoordinate, yCoordinate, enterCordinate);
        shape.setStrokeWidth(5);

    }

    public void setCoordinates(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
        points.clear();
        if (enterCordinate == 1) {
            points.add(new Point2D(getXCoordinate() , getYCoordinate() - 0.5));
        }
        else if (enterCordinate == 2) {
            points.add(new Point2D(getXCoordinate() + 0.5, getYCoordinate() ));   // 1 sesim geliyor mu yukarı bakmıyor muydu yukarı 0.5 olmaz mı aynen ortası 0,0 ise yukarsısı -0.
        }
        else if (enterCordinate == 3) {
            points.add(new Point2D(getXCoordinate()  , getYCoordinate() + 0.5)); //bu artı
        }
        else if (enterCordinate == 4) {
            points.add(new Point2D(getXCoordinate() -0.5, getYCoordinate()));
        }

    }

    public int getEnterCordinate() {
        return enterCordinate;
    }

    public void setEnterCordinate(int enterCordinate) {
        this.enterCordinate = enterCordinate;
    }


    public Line getShape() {
        return shape;
    }

    public void setShape(String status, int xCoordinate, int yCoordinate, int enterCordinate) {
       shape =  DrawShape.drawEnd(status, xCoordinate, yCoordinate, enterCordinate);
    }



}