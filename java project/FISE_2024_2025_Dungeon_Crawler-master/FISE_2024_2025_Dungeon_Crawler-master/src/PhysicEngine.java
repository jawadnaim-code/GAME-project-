import java.util.ArrayList;

/**
 * PhysicEngine is responsible for handling the movement and collision checks
 * of dynamic sprites within an environment of static and solid sprites.
 * its for modelling the physics of the game .(constant speed and collisions).
 */
public class PhysicEngine implements Engine {
    // List to store all dynamic sprites that can move within the environment.
    private ArrayList<DynamicSprite> movingSpriteList;
    // List of all static sprites that represent obstacles or interactable objects in the environment.
    private ArrayList<Sprite> environment;

    /**
     * Constructor initializes empty lists for dynamic sprites and environment sprites.
     */
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    /**
     * Adds a sprite to the environment list if it's not already present.
     * This list represents obstacles or static objects within the game.
     *
     * @param sprite the static sprite to add
     */
    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite)) {  // Avoid adding duplicates
            environment.add(sprite);
        }
    }

    /**
     * Sets the environment list, replacing any existing environment sprites.
     * This method can be used to load a new level or reset the environment.
     *
     * @param environment the new list of environment sprites
     */
    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    /**
     * Adds a dynamic sprite (movable object) to the moving sprite list if it's not already there.
     * Dynamic sprites are objects that can move and interact with the environment.
     *
     * @param sprite the dynamic sprite to add
     */
    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {  // Avoid adding duplicates
            movingSpriteList.add(sprite);
        }
    }

    /**
     * Updates the state of the PhysicEngine by attempting to move each dynamic sprite.
     * Each dynamic sprite in the moving list will check if it can move, considering
     * the environment's obstacles.
     */
    @Override
    public void update() {
        // For each dynamic sprite, attempt to move if possible, given the environment constraints
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);  // Each sprite checks for possible moves
            // Check if the sprite has collided with a trap
            for (Sprite sprite : environment) {
                if ((!dynamicSprite.getIsInvicible()) && sprite instanceof Trap && ((SolidSprite) sprite).intersect(dynamicSprite.getHitBox())) {
                    dynamicSprite.incrementTrapCount();
                    System.out.println("1 LIFE LOST");
                    dynamicSprite.becomeInvicible();
                    // Increment trap count if collision occurs
                    break; // Stop checking once we detect a trap
                }
            }
        }

    }
}