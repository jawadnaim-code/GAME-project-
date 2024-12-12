import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * SolidSprite represents a sprite with a physical presence in the game environment,
 * meaning it has a hitbox that can interact with other objects.
 * This class is used for objects that the player or other entities cannot pass through.
 */
public class SolidSprite extends Sprite {

    /**
     * Constructor initializes the position, image, and dimensions of the SolidSprite.
     *
     * @param x      the x-coordinate of the sprite
     * @param y      the y-coordinate of the sprite
     * @param image  the image to display for this sprite
     * @param width  the width of the sprite
     * @param height the height of the sprite
     */
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    /**
     * Returns the hitbox of the sprite as a Rectangle2D.
     * This defines the area it occupies, used for collision detection.
     *
     * @return a Rectangle2D representing the hitbox of the sprite
     */
    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);  // Defines the rectangular hitbox
    }

    /**
     * Checks if this SolidSprite intersects with another hitbox.
     * Used for detecting collisions with other objects in the game.
     *
     * @param hitBox the hitbox of another object to check for intersection
     * @return true if there is an intersection, false otherwise
     */
    public boolean intersect(Rectangle2D.Double hitBox) {
        return this.getHitBox().intersects(hitBox);  // Checks if the hitboxes overlap
    }
}
