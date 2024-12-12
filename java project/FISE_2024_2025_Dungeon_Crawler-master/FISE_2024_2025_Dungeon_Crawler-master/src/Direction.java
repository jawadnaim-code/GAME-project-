/**
 * Enum representing the four possible directions a sprite can face or move:
 * NORTH, SOUTH, EAST, and WEST.
 * Each direction is associated with a unique frame line number for selecting
 * the appropriate row in the sprite sheet for animations.
 */
public enum Direction {
    NORTH(2),    // Row 2 in the sprite sheet for NORTH-facing frames
    SOUTH(0),    // Row 0 in the sprite sheet for SOUTH-facing frames
    EAST(3),     // Row 3 in the sprite sheet for EAST-facing frames
    WEST(1);     // Row 1 in the sprite sheet for WEST-facing frames

    // The frame line number in the sprite sheet for this direction.
    private final int frameLineNumber;

    /**
     * Constructor to initialize each direction with its specific frame line number.
     *
     * @param frameLineNumber The row in the sprite sheet that corresponds to the direction.
     */
    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    /**
     * Retrieves the frame line number associated with the direction.
     *
     * @return The frame line number in the sprite sheet for this direction.
     */
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}
