import java.awt.*;

/**
 * Interface for any displayable element in the game.
 * Ensures that implementing classes have a draw method for rendering.
 */
public interface Displayable {

    /**
     * Draws the displayable element on the screen using the provided Graphics object.
     *
     * @param g the Graphics object used to render the element
     */
    public void draw(Graphics g);
}
