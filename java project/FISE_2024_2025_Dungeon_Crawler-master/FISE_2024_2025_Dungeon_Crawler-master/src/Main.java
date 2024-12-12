import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The Main class initializes the game window, sets up all game components (RenderEngine,
 * GameEngine, and PhysicEngine), loads the level, and starts the game loop also the main displays message to test the
 * setup.
 * This design uses 3 engines, each runs by a specific timer (executed every 50 ms).
 */
public class Main {
    private static JFrame frame; // Main game frame

    /**
     * Displays the Game Over screen.
     */
    public static void gameOver() {
        if (frame == null) {
            System.err.println("Game frame is not initialized.");
            return;
        }

        // Clear the game content and show Game Over screen
        frame.getContentPane().removeAll();

        // Create Game Over message
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel("Game Over", JLabel.CENTER);
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 48));
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        // Add a retry button to restart the game
        JButton retryButton = new JButton("Retry");
        gameOverPanel.add(retryButton, BorderLayout.SOUTH);

        // Action to restart the game when retry button is clicked
        retryButton.addActionListener(e -> {
            frame.dispose(); // Close and remove the old game window
            try {
                new Main(); // Start a new game
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.getContentPane().add(gameOverPanel); // Show Game Over screen
        frame.revalidate();
    }

    private static void showTitleScreen() {
        // Placeholder for showing the title screen or resetting the game.
        System.out.println("Title screen displayed (or game reset).");
    }

    // JFrame that serves as the display area for the game.
    private JFrame displayZoneFrame;

    // Core components of the game: rendering, game logic, and physics engines.
    private RenderEngine renderEngine;
    private GameEngine gameEngine;
    private PhysicEngine physicEngine;

    /**
     * Constructor for the Main class. It initializes the game window, sets up all components,
     * loads the game level, and starts the game loop.
     *
     * @throws Exception if there's an issue loading resources like images or levels.
     */
    public Main() throws Exception {
        // Create and configure the main game window.
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400, 600);  // Set window dimensions.
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit on close.

        // Assign displayZoneFrame to the static frame variable
        frame = displayZoneFrame;

        // Initialize the hero sprite with its starting position and sprite sheet.
        DynamicSprite hero = new DynamicSprite(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);

        // Initialize the render engine and pass it the display frame to render graphics.
        renderEngine = new RenderEngine(displayZoneFrame);

        // Initialize the physics and game engines.
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        // Set up timers for each engine to update at a regular interval (50 milliseconds).
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        // Start the timers to begin the game loop.
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        // Add the render engine to the JFrame content to handle visual rendering.
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);  // Make the game window visible.

        // Load the level from a text file and prepare the game environment.
        Playground level = new Playground("./data/level1.txt");

        // Add sprites and solid objects to the render and physics engines.
        renderEngine.addToRenderList(level.getSpriteList());  // Add level sprites.
        renderEngine.addToRenderList(hero);  // Add the hero to the render list.
        physicEngine.addToMovingSpriteList(hero);  // Allow the hero to move with physics.
        physicEngine.setEnvironment(level.getSolidSpriteList());  // Set collision objects.

        // Enable the GameEngine to listen to key events for player input.
        displayZoneFrame.addKeyListener(gameEngine);
    }

    /**
     * The main entry point for the application. Creates an instance of Main to start the game.
     *
     * @param args command-line arguments (not used).
     * @throws Exception if initialization fails (e.g., resource loading).
     */
    public static void main(String[] args) throws Exception {
        // Create and start the game.
        Main main = new Main();
    }
}
