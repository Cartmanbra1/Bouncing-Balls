/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents a simple animation of bouncing balls using the biuoop.GUI library.
 */
public class BouncingBallAnimation {

    /**
     * The main method that creates a GUI, initializes balls, and displays the animation.
     *
     * @param args command line arguments for specifying initial ball position and velocity
     */
    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("Balls Test 1", 400, 400);
        biuoop.DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(100, 100, 30, java.awt.Color.RED);
        Ball b2 = new Ball(100, 150, 10, java.awt.Color.BLUE);
        Ball b3 = new Ball(80, 249, 50, java.awt.Color.GREEN);
        int[] result = CommandLineArguments.getArgumentsAsIntArray(args);

        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);
        drawAnimation(new Point(result[0], result[1]), result[2], result[3]);
        gui.show(d);
    }

    /**
     * Draws a bouncing ball animation on a new GUI with the specified starting point and velocity.
     *
     * @param start the starting point of the ball
     * @param dx the change in x-coordinate per step (velocity)
     * @param dy the change in y-coordinate per step (velocity)
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        biuoop.GUI gui = new biuoop.GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);

        while (true) {
            ball.moveOneStep();
            biuoop.DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
