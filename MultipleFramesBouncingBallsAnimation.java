import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents an animation of multiple bouncing balls within multiple frames using the biuoop.GUI library.
 */
public class MultipleFramesBouncingBallsAnimation {

    public static int width = 800;
    public static int height = 600;

    /**
     * Generates a random Point within specified boundaries.
     *
     * @param lowX  the lower bound for the x-coordinate
     * @param highX the upper bound for the x-coordinate
     * @param lowY  the lower bound for the y-coordinate
     * @param highY the upper bound for the y-coordinate
     * @return a randomly generated Point
     */
    public static Point pointRandomizer(int lowX, int highX, int lowY, int highY) {
        Random random = new Random();
        double x = random.nextInt(10) + lowX;
        double y = random.nextInt(10) + lowY;
        return new Point(x, y);
    }

    /**
     * Sets random velocities for the given array of Ball objects.
     *
     * @param ballsArray the array of Ball objects
     */
    public static void setBallsVelocity(Ball[] ballsArray) {
        Random random = new Random();
        for (int i = 0; i < ballsArray.length; i++) {
            double dx = random.nextInt(20) + 50;
            double dy = random.nextInt(10) + 50;
            ballsArray[i].setVelocity(Velocity.fromAngleAndSpeed(
                    dx / ballsArray[i].getSize(), dy / ballsArray[i].getSize()));
        }
    }

    /**
     * Creates an array of Ball objects with random positions and specified sizes within different frames.
     *
     * @param length   the length of the array
     * @param sizes    an array of sizes for the balls
     * @param bigFrame the larger frame for half of the balls
     * @return an array of Ball objects
     */
    public static Ball[] makeBallsArray(int length, int[] sizes, Frame bigFrame) {
        Ball[] ballsArray = new Ball[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if (i < Math.floor((double) length / 2)) {
                ballsArray[i] = new Ball(pointRandomizer(bigFrame.getTopX(), bigFrame.getBottomX(),
                        bigFrame.getTopY(), bigFrame.getBottomY()), sizes[i], Color.black);
            } else {
                if (sizes[i] > 20 && sizes[i] < 50) {
                    ballsArray[i] = new Ball(pointRandomizer(bigFrame.getBottomX() + sizes[i], width,
                            0, height), sizes[i], Color.red);
                } else if (sizes[i] > 0 && sizes[i] <= 20) {
                    ballsArray[i] = new Ball(pointRandomizer(0, bigFrame.getTopX(), 0, height),
                            sizes[i], Color.BLUE);
                } else {
                    sizes[i] = random.nextInt(50);
                    i -= 1;
                }
            }
        }
        return ballsArray;
    }

    /**
     * Displays the bouncing balls animation within multiple frames on a GUI.
     *
     * @param sizes an array of sizes for the balls
     * @param gui   the GUI for displaying the animation
     */
    private static void drawAnimation(int[] sizes, GUI gui) {
        Random random = new Random();
        Sleeper sleeper = new Sleeper();

        Frame bigFrame = new Frame(new Point(50, 50), new Point(500, 500), Color.gray);
        Frame smallFrame = new Frame(new Point(450, 450), new Point(600, 600), Color.yellow);
        Frame mainFrame = new Frame(new Point(0, 0), new Point(800, 600), Color.pink);

        Ball[] ballsArray = makeBallsArray(sizes.length, sizes, bigFrame);
        setBallsVelocity(ballsArray);

        while (true) {
            biuoop.DrawSurface d = gui.getDrawSurface();
            bigFrame.drawOn(d);
            smallFrame.drawOn(d);
            for (int i = 0; i < ballsArray.length; i++) {
                if (i < Math.floor((double) ballsArray.length / 2)) {
                    ballsArray[i].moveOneStep(bigFrame);
                    ballsArray[i].drawOn(d);
                } else {
                    ballsArray[i].moveOneStep(mainFrame);
                    ballsArray[i].moveOneStepOpposite(bigFrame);
                    ballsArray[i].moveOneStepOpposite(smallFrame);
                    ballsArray[i].drawOn(d);
                }
                sleeper.sleepFor(5);
            }
            smallFrame.drawOn(d);
            gui.show(d);
        }
    }

    /**
     * Main method to initialize the bouncing balls animation within multiple frames.
     *
     * @param args command line arguments for specifying ball sizes
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Main Frame", 800, 600);
        int[] sizes = CommandLineArguments.getArgumentsAsIntArray(args);

        drawAnimation(sizes, gui);
    }
}
