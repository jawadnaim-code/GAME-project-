import java.awt.*;
        import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Represents a movable and animated sprite with direction and collision detection.
 * This class inherits from SolidSprite and adds additional movement and animation controls.
 Track Trap Interactions: Add a counter in DynamicSprite to track how many times the character has entered a trap.
 */
public class DynamicSprite extends SolidSprite {
    // Add a trap count to track trap touches
    private int trapCount = 0;
    private boolean isInvicible = false;
    private long lastTimeCollision=System.currentTimeMillis();
    private final long invincibleDelayInMs=2000;

    /**
     * Increments the trap counter. If it reaches 3, the game is over.
     */
    public void incrementTrapCount() {
            if (!isInvicible) { // Only increment if not invincible
                trapCount++;
                becomeInvicible(); // Activate invincibility after trap interaction
            }

            // Check if the player has lost all chances
            if (trapCount >= 3) {
                Main.gameOver(); // Display Game Over screen
            }
        }



    /**
     * Resets the trap count (useful if restarting the game).
     */
    public void resetTrapCount() {
        trapCount = 0;
    }




    // Direction of movement; initialized to face EAST by default.
    private Direction direction = Direction.EAST;

    // Speed of the sprite in pixels per frame.
    private double speed = 5;

    // Time between frames for animation, in milliseconds.
    private double timeBetweenFrame = 250;

    // Determines whether the sprite is currently moving.
    private boolean isWalking = true;

    private boolean isAlive = true;

    // Number of columns in the sprite sheet for animation frames.
    private final int spriteSheetNumberOfColumn = 10;


    public void dies(){
        isAlive=false;
        System.out.println("Dead");
    }
    /**
     * Constructor to initialize a dynamic sprite with position, image, and dimensions.
     *
     * @param x      The x-coordinate of the sprite.
     * @param y      The y-coordinate of the sprite.
     * @param image  The image representing the sprite.
     * @param width  The width of the sprite.
     * @param height The height of the sprite.
     */
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    /**
     * Checks if moving in the current direction is possible by detecting collisions
     * with solid sprites in the environment.
     *
     * @param environment List of sprites in the environment.
     * @return true if movement is possible, false if it would cause a collision.
     */
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        // Creates a hitbox for the next position based on direction and speed.
        Rectangle2D.Double moved = new Rectangle2D.Double();

        // Adjust hitbox position based on direction
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        // Check collision with other solid sprites
        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && !(s instanceof Trap) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false; // Movement not possible if collision is detected
                }
            }
        }
        return true; // Movement is possible if no collisions detected
    }

    /**
     * Sets the direction of the sprite.
     *
     * @param direction The new direction for the sprite.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Moves the sprite in the current direction by its speed.
     */
    private void move() {
        switch (direction) {
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
        }
    }

    /**
     * Checks if movement is possible and, if so, moves the sprite in the current direction.
     *
     * @param environment List of sprites in the environment.
     */
    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            move();
        }
        // Check if the sprite has touched a trap
        for (Sprite sprite : environment) {
            if ((sprite instanceof SolidSprite) && ((SolidSprite) sprite).intersect(this.getHitBox())) {
                // Assuming 'J' represents a trap in the game
                if (sprite instanceof Trap) { // A new TrapSprite class could extend SolidSprite
         //           Main.gameOver(); // Trigger game over when touching a trap
                    return;
                }
            }
        }
    }

    /**
     * Draws the sprite on the screen, displaying the appropriate animation frame based on
     * direction and time.
     *
     * @param g The Graphics object used for rendering the sprite.
     */
    @Override
    public void draw(Graphics g) {
        // Calculate the current frame index based on system time for animation
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        // Draw the current frame from the sprite sheet based on direction and index
        if (!isInvicible || (System.currentTimeMillis()%250<150)){
            g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                    (int) (index * this.width),
                    (int) (direction.getFrameLineNumber() * height),
                    (int) ((index + 1) * this.width),
                    (int) ((direction.getFrameLineNumber() + 1) * this.height), null);
        }
        if (isInvicible && System.currentTimeMillis()-lastTimeCollision > invincibleDelayInMs) {
            isInvicible = false;
        }
    }

    public void becomeInvicible() {
        isInvicible = true;
        lastTimeCollision = System.currentTimeMillis();
    }

    public boolean getIsInvicible() {
        return isInvicible;
    }
}
