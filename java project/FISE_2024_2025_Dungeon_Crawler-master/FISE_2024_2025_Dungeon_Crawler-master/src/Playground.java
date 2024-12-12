import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Playground class is responsible for loading and managing the game's environment layout from a file.
 * It initializes different types of sprites (e.g., trees, rocks) based on symbols in the input file.
 */
public class Playground {
    // List to store all sprites (both static and solid) that make up the environment
    private ArrayList<Sprite> environment = new ArrayList<>();

    /**
     * Constructor for Playground. Loads the environment layout from a file and initializes the sprites.
     * Each character in the file represents a specific type of environment element (e.g., tree, grass, rock).
     *
     * @param pathName the path to the file containing the environment layout
     */
    public Playground(String pathName) {
        try {
            // Load images for different types of sprites in the environment
            final Image imageTree = ImageIO.read(new File("./img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("./img/grass.png"));
            final Image imageRock = ImageIO.read(new File("./img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("./img/trap.png")); // Trap image if needed

            // Dimensions for each image type, used for placing elements on a grid
            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);
            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);
            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);

            // Loads trap image (trap.png). and Trap image dimensions
            final int imageTrapWidth = imageRock.getWidth(null);
            final int imageTrapHeight = imageRock.getHeight(null);

            // Open the file and start reading each line to create the environment
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line = bufferedReader.readLine();
            int lineNumber = 0; // Tracks the row in the environment grid

            // Process each line in the input file
            while (line != null) {
                int columnNumber = 0; // Tracks the column in the environment grid
                for (byte element : line.getBytes(StandardCharsets.UTF_8)) {
                    // Determine the type of sprite to add based on character symbol in the file
                    switch (element) {
                        case 'T':
                            // Add a tree at the calculated position
                            environment.add(new SolidSprite(columnNumber * imageTreeWidth,
                                    lineNumber * imageTreeHeight, imageTree, imageTreeWidth, imageTreeHeight));
                            break;
                        case ' ':
                            // Add grass at the calculated position (non-solid, passable)
                            environment.add(new Sprite(columnNumber * imageGrassWidth,
                                    lineNumber * imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R':
                            // Add a rock at the calculated position
                            environment.add(new SolidSprite(columnNumber * imageRockWidth,
                                    lineNumber * imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
                        case 'J':
                            // Add a trap at the calculated position so that if he touches he will die
                            environment.add(new Trap(columnNumber * imageTrapWidth,
                                    lineNumber * imageTrapHeight, imageTrap, imageTrapWidth, imageTrapHeight));
                            break;
                    }
                    columnNumber++; // Move to the next column
                }
                lineNumber++; // Move to the next row
                line = bufferedReader.readLine(); // Read the next line in the file
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print any exceptions to assist with debugging
        }
    }

    /**
     * Gets a list of all solid sprites (e.g., trees, rocks) in the environment.
     * Solid sprites are obstacles that the player cannot pass through.
     *
     * @return a list of solid sprites in the environment
     */
    public ArrayList<Sprite> getSolidSpriteList() {
        ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) {
                solidSpriteArrayList.add(sprite);
            }
        }
        return solidSpriteArrayList;
    }

    /**
     * Gets a list of all displayable sprites in the environment.
     * This is used to render all elements in the environment, including passable and non-passable elements.
     *
     * @return a list of all displayable sprites in the environment
     */
    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableArrayList.add(sprite);
        }
        return displayableArrayList;
    }
}
