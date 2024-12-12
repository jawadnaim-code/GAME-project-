import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The GameEngine class is responsible for handling user inputs to control the hero's movement.
 * It implements the Engine interface (to perform updates) and the KeyListener interface
 * (to handle keyboard inputs).
 */
public class GameEngine implements Engine, KeyListener {
    // Reference to the hero character, a DynamicSprite, whose direction is controlled by user input.
    private DynamicSprite hero;

    /**
     * Constructor that initializes the GameEngine with a hero.
     * @param hero The DynamicSprite character that the GameEngine will control.
     */
    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    /**
     * The update method required by the Engine interface.
     * Currently, it is left empty as there is no periodic update logic needed in this version.
     */
    @Override
    public void update() {
        // This can be expanded if periodic updates are needed.
    }

    /**
     * The keyTyped method is required by KeyListener but is not used in this class.
     * It can be left empty or used for any future specific character input handling.
     * @param e The KeyEvent triggered by a key typing action.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Unused, but implemented as required by KeyListener
    }

    /**
     * Handles the keyPressed event. This method changes the direction of the hero
     * based on arrow key inputs.
     * @param e The KeyEvent triggered by a key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // Set hero's direction to NORTH when the up arrow key is pressed.
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                // Set hero's direction to SOUTH when the down arrow key is pressed.
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                // Set hero's direction to WEST when the left arrow key is pressed.
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                // Set hero's direction to EAST when the right arrow key is pressed.
                hero.setDirection(Direction.EAST);
                break;
        }
    }

    /**
     * The keyReleased method is required by KeyListener but is not used in this class.
     * This can be expanded to handle any logic upon key release if needed.
     * @param e The KeyEvent triggered by a key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Unused, but implemented as required by KeyListener
    }
}
