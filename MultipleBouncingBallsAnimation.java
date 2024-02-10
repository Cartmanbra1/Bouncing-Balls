import java.util.Random;
import java.awt.Color;

/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents an animation of multiple bouncing balls using the biuoop.GUI library.
 */
public class MultipleBouncingBallsAnimation {

    /**
     * Generates a random Point within the boundaries of a 200x200 frame.
     *
     * @return a randomly generated Point
     */
    public static Point pointRandomizer() {
        Random random = new Random();
        double x = random.nextDouble() * 200;
        double y = random.nextDouble() * 200;
        return new Point(x, y);
    }

    /**
     * Creates an array of Ball objects with random positions and specified sizes.
     *
     * @param length the length of the array
     * @param sizes an array of sizes for the balls
     * @return an array of Ball objects
     */
    public static Ball[] makeBallsArray(int length, int[] sizes) {
        Ball[] ballsArray = new Ball[length];
        for (int i = 0; i < length; i++) {
            ballsArray[i] = new Ball(pointRandomizer(), sizes[i], Color.black);
        }
        return ballsArray;
    }

    /**
     * Sets random velocities for the given array of Ball objects.
     *
     * @param ballsArray the array of Ball objects
     */
    public static void setBallsVelocity(Ball[] ballsArray) {
        Random random = new Random();
        for (int i = 0; i < ballsArray.length; i++) {
            double dx = random.nextInt(5);
            double dy = random.nextInt(5);
            ballsArray[i].setVelocity(Velocity.fromAngleAndSpeed(dx, dy));
        }
    }

    /**
     * Main method to initialize the bouncing balls animation.
     *
     * @param args command line arguments for specifying ball sizes
     */
    public static void main(String[] args) {
        int[] sizes = CommandLineArguments.getArgumentsAsIntArray(args);
        Ball[] ballsArray = makeBallsArray(sizes.length, sizes);
        setBallsVelocity(ballsArray);
        drawAnimation(ballsArray);
    }

    /**
     * Displays the bouncing balls animation on a GUI.
     *
     * @param ballsArray the array of Ball objects
     */
    public static void drawAnimation(Ball[] ballsArray) {
        biuoop.GUI gui = new biuoop.GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        while (true) {
            biuoop.DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < ballsArray.length; i++) {
                ballsArray[i].moveOneStep();
                ballsArray[i].drawOn(d);
                sleeper.sleepFor(5);
            }
            gui.show(d);
        }
    }
}
