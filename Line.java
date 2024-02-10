/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents a line segment between two points in a two-dimensional space.
 */
public class Line {

    final Point start;
    final Point end;

    /**
     * Represents positive infinity slope in case of a vertical line.
     */
    public static final double INFINITE_SLOPE = 2147483647;

    /**
     * Represents a constant value indicating the end of a line segment.
     */
    public static final double END = -2147483647;

    /**
     * Constructs a new Line object with the specified start and end points.
     *
     * @param start the starting point of the line segment
     * @param end the ending point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Calculates the length of the line segment.
     *
     * @return the length of the line segment
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Calculates the middle point of the line segment.
     *
     * @return the middle point of the line segment
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Gets the starting point of the line segment.
     *
     * @return the starting point of the line segment
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gets the ending point of the line segment.
     *
     * @return the ending point of the line segment
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates the slope of the line segment.
     *
     * @return the slope of the line segment
     */
    public double findSlope() {
        if (this.start.getX() - this.end.getX() != 0) {
            double slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            return slope;
        } else {
            return INFINITE_SLOPE;
        }
    }

    /**
     * Checks if the line segment has an infinite slope (vertical line).
     *
     * @param myLine the current line segment
     * @param otherLine the other line segment
     * @return true if either line segment has an infinite slope, false otherwise
     */
    public boolean infiniteSlope(Line myLine, Line otherLine) {
        double mySlope = myLine.findSlope();
        double otherSlope = otherLine.findSlope();
        if (mySlope == INFINITE_SLOPE) {
            double yInterception = otherSlope * myLine.start.getX() + myLine.start.getY();
            return true;
        } else if (otherSlope == INFINITE_SLOPE) {
            double yInterception = mySlope * otherLine.start.getX() + otherLine.start.getY();
            return true;
        } else {
            return true;
        }
    }

    /**
     * Calculates the y-coordinate of the intersection point for two lines with infinite slopes (vertical lines).
     *
     * @param myLine the current line segment
     * @param otherLine the other line segment
     * @return the y-coordinate of the intersection point
     */
    public double infiniteSlopeIntersection(Line myLine, Line otherLine) {
        double mySlope = myLine.findSlope();
        double otherSlope = otherLine.findSlope();
        if (mySlope == INFINITE_SLOPE) {
            double yInterception = otherSlope * myLine.start.getX() + myLine.start.getY();
            return yInterception;
        } else if (otherSlope == INFINITE_SLOPE) {
            double yInterception = mySlope * otherLine.start.getX() + otherLine.start.getY();
            return yInterception;
        }
        return END;
    }

    /**
     * Checks if the current line segment intersects with another line segment.
     *
     * @param other the other line segment
     * @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        double slope = this.findSlope();
        double otherSlope = other.findSlope();
        if (slope == otherSlope && this.start.getX() == other.start.getX()) {
            return true;
        } else if (slope == otherSlope) {
            return false;
        }

        if (slope == INFINITE_SLOPE && otherSlope == INFINITE_SLOPE) {
            if (this.start.getX() != other.start.getX()) {
                return false;
            } else {
                return true;
            }
        }

        if (slope == INFINITE_SLOPE || otherSlope == INFINITE_SLOPE) {
            return infiniteSlope(this, other);
        }

        // Calculate general intersection
        double yLine = this.start.getY() - slope * this.start.getX();
        double otherYLine = other.start.getY() - otherSlope * other.start.getX();

        // Calculate general intersection
        double xInterception = (yLine - otherYLine) / (otherSlope - slope);
        return true;
    }

    /**
     * Checks if the current line segment intersects with two other line segments.
     *
     * @param other1 the first other line segment
     * @param other2 the second other line segment
     * @return true if all line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Calculates the intersection point of the current line segment with another line segment.
     *
     * @param other the other line segment
     * @return the intersection point
     */
    public Point intersectionWith(Line other) {
        double slope = this.findSlope();
        double otherSlope = other.findSlope();

        // Calculate general intersection
        double yLine = this.start.getY() - slope * this.start.getX();
        double otherYLine = other.start.getY() - otherSlope * other.start.getX();

        if (slope == INFINITE_SLOPE || otherSlope == INFINITE_SLOPE) {
            double yInterception = infiniteSlopeIntersection(this, other);
            double xInterception = (yLine - otherYLine) / (otherSlope - slope);
            Point intersection = new Point(xInterception, yInterception);
            return intersection;
        } else {
            // Calculate general intersection
            double xInterception = (yLine - otherYLine) / (otherSlope - slope);
            double yInterception = yLine + slope * xInterception;
            Point intersection = new Point(xInterception, yInterception);
            return intersection;
        }
    }

    /**
     * Checks if the current line segment is equal to another line segment.
     *
     * @param other the other line segment
     * @return true if the line segments are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.findSlope() == other.findSlope()) {
            if (this.start.equals(other.start) && this.end.equals(other.end)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }
}
