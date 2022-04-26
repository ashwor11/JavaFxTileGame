import javafx.scene.image.Image;

public class Empty extends Tile{
    private String status1;


    public Empty(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        setMoveable(true);
        this.setImage(new Image("file:GamePNGs/empty.png"));
    }

    public String getStatus(){
        return this.status1;
    }
}
