import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * RenderEngine is responsible for drawing all displayable objects on the screen.Some are static and others are
 * animated.
 * It extends JPanel and implements the Engine interface, allowing it to be updated and repainted.
 */
public class RenderEngine extends JPanel implements Engine {
    // List containing all displayable objects to be rendered on the screen.
    private ArrayList<Displayable> renderList;

    /**
     * Constructor initializes the render list and sets up the panel.
     *
     * @param jFrame The main JFrame of the application. This parameter is not used here
     *               but might be used for future enhancements.
     */
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
        // The JFrame parameter could be used to set layout or other configurations if needed.
    }

    /**
     * Adds a single displayable object to the render list if it's not already present.
     *
     * @param displayable the object to be rendered
     */
    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {  // Avoid duplicate entries
            renderList.add(displayable);
        }
    }

    /**
     * Adds multiple displayable objects to the render list if they're not already present.
     *
     * @param displayables a list of displayable objects to add
     */
    public void addToRenderList(ArrayList<Displayable> displayables) {
        for (Displayable displayable : displayables) {
            if (!renderList.contains(displayable)) {
                renderList.add(displayable);  // Add each displayable that isn’t already present
            }
        }
    }

    /**
     * Paint method is overridden to draw all objects in the render list.
     * This method is called automatically by Swing whenever the panel needs to be redrawn.
     *
     * @param g Graphics object used for drawing
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);  // Call the superclass's paint method to clear previous drawings
        // Draw each displayable object in the render list
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }
    }

    /**
     * Update method to refresh the panel by calling repaint.
     * This method triggers a new call to paint(), updating the rendered view.
     */
    @Override
    public void update() {
        this.repaint();  // Repaint the JPanel, causing paint() to be called
    }
}
