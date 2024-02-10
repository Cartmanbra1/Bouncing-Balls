/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents a threshold for comparing equality between integer and double values.
 */
public class Threshold {

    private static final double DEFAULT_ACCURACY = 0.0000001;

    private static double accuracy;

    /**
     * Constructs a Threshold object with the default accuracy.
     */
    public Threshold() {
        this(DEFAULT_ACCURACY);
    }

    /**
     * Constructs a Threshold object with a specified accuracy.
     *
     * @param accuracy the accuracy for comparing values
     */
    public Threshold(double accuracy) {
        Threshold.accuracy = accuracy;
    }

    /**
     * Checks if an integer value is approximately equal to a double value within the defined accuracy.
     *
     * @param intValue    the integer value
     * @param doubleValue the double value
     * @return true if the values are approximately equal, false otherwise
     */
    public static boolean areEqual(int intValue, double doubleValue) {
        double doubleIntValue = (double) intValue;
        return Math.abs(doubleIntValue - doubleValue) < accuracy;
    }
}
