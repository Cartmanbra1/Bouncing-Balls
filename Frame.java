import biuoop.DrawSurface;
/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents a frame in a two-dimensional space with specified top-left and bottom-right points, and a color.
 */
public class Frame {

    private Point topLeft;
    private Point bottomRight;
    private java.awt.Color color;

    /**
     * Constructs a new Frame object with the specified top-left and bottom-right points, and color.
     *
     * @param topLeft the top-left point of the frame
     * @param bottomRight the bottom-right point of the frame
     * @param color the color of the frame
     */
    public Frame(Point topLeft, Point bottomRight, java.awt.Color color) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.color = color;
    }

    /**
     * Gets the x-coordinate of the top-left point of the frame.
     *
     * @return the x-coordinate of the top-left point
     */
    public int getTopX() {
        return (int) this.topLeft.getX();
    }

    /**
     * Gets the y-coordinate of the top-left point of the frame.
     *
     * @return the y-coordinate of the top-left point
     */
    public int getTopY() {
        return (int) this.topLeft.getY();
    }

    /**
     * Gets the x-coordinate of the bottom-right point of the frame.
     *
     * @return the x-coordinate of the bottom-right point
     */
    public int getBottomX() {
        return (int) this.bottomRight.getX();
    }

    /**
     * Gets the y-coordinate of the bottom-right point of the frame.
     *
     * @return the y-coordinate of the bottom-right point
     */
    public int getBottomY() {
        return (int) this.bottomRight.getY();
    }

    /**
     * Gets the color of the frame.
     *
     * @return the color of the frame
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draws the frame on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the frame
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        int width = this.getBottomX() - this.getTopX();
        int height = this.getBottomY() - this.getTopY();
        surface.fillRectangle(this.getTopX(), this.getTopY(), width, height);
    }
}
