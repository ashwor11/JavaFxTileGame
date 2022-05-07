import javafx.scene.image.Image;

public class EmptyFree extends Tile{

    //Constructor
    public EmptyFree(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
        setMoveable(true);
        this.setImage(new Image("file:GamePNGs/emptyFree.png"));
    }
}
