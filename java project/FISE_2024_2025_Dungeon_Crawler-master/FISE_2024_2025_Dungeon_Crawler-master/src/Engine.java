/**
 * Interface for game engine components that require periodic updates.
 * Implementing classes define specific update logic, allowing them to handle
 * tasks like rendering, user input, or physics processing.
 */
public interface Engine {

    /**
     * Updates the engine's state.
     * This method should be implemented to handle periodic tasks,
     * such as refreshing graphics, handling input events, or managing physics.
     * Typically called at a fixed interval (e.g., every 50 milliseconds).
     */
    void update();
}
