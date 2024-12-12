import java.awt.Image;

/**
 * Represents a trap in the game. When the player steps on this trap,
 * it increases a death counter, and after three touches, the game ends.
 */
public class Trap extends SolidSprite {

    public Trap(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }
}
