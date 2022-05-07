import javafx.scene.image.Image;

public class Empty extends Tile{

    //Constructor
    public Empty(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        setMoveable(true);
        this.setImage(new Image("file:GamePNGs/empty.png"));
    }
}
