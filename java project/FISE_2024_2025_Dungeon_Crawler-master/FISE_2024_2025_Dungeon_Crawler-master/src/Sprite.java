import java.awt.*;

/**
 * Sprite class represents a basic game element with visual representation.
 * Implements the Displayable interface to allow rendering on the screen.
 */
public class Sprite implements Displayable {

    // Position of the sprite on the X-axis
    protected double x;

    // Position of the sprite on the Y-axis
    protected double y;

    // Image representing the sprite (final to ensure immutability)
    protected final Image image;

    // Width of the sprite in pixels
    protected final double width;

    // Height of the sprite in pixels

    protected final double height;

    /**
     * Constructor initializes the Sprite's position, image, and dimensions.
     *
     * @param x The X coordinate of the sprite
     * @param y The Y coordinate of the sprite
     * @param image The visual image of the sprite
     * @param width The width of the sprite image
     * @param height The height of the sprite image
     */
    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * draw method renders the sprite's image at its current position.
     *
     * @param g The Graphics object used to draw the image on the screen.
     */
    @Override
    public void draw(Graphics g) {
        // Casts coordinates to integers for accurate pixel placement
        g.drawImage(image, (int)x, (int)y, null);
    }
}
