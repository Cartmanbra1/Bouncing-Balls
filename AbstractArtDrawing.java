import java.awt.Color;
import java.util.Random;

/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Represents an abstract art drawing consisting of random lines and various geometric shapes.
 */
public class AbstractArtDrawing {

    public static final int LINE_AMOUNT = 10;
    private static final biuoop.GUI GUI = new biuoop.GUI("Random Lines", 300, 400);
    private static final biuoop.DrawSurface DRAW_SURFACE = GUI.getDrawSurface();

    public static void main(String[] args) {
        Line[] linesArray = drawLines();
        drawMidPoint(linesArray);
        drawIntersectionPoint(linesArray);
        drawTriangles(linesArray);
    }

    /**
     * Draws random lines on the canvas and returns an array of Line objects.
     *
     * @return an array of Line objects representing the random lines
     */
    public static Line[] drawLines() {
        Random rand = new Random();
        Line[] linesArray = new Line[LINE_AMOUNT];

        for (int i = 0; i < LINE_AMOUNT; i++) {
            int x = rand.nextInt(300);
            int y = rand.nextInt(400);
            int x2 = rand.nextInt(300);
            int y2 = rand.nextInt(400);

            Point point = new Point(x, y);
            Point secondPoint = new Point(x2, y2);
            Line line = new Line(point, secondPoint);

            DRAW_SURFACE.setColor(Color.black);
            DRAW_SURFACE.drawLine((int) point.getX(), (int) point.getY(),
                    (int) secondPoint.getX(), (int) secondPoint.getY());

            linesArray[i] = line;
        }

        return linesArray;
    }

    /**
     * Draws the midpoint of each line in blue on the canvas.
     *
     * @param linesArray an array of Line objects
     */
    public static void drawMidPoint(Line[] linesArray) {
        Point middle = new Point(0, 0);

        for (int i = 0; i < LINE_AMOUNT; i++) {
            middle = linesArray[i].middle();
            DRAW_SURFACE.setColor(Color.blue);
            DRAW_SURFACE.drawCircle((int) middle.getX(), (int) middle.getY(), 2);
            DRAW_SURFACE.fillCircle((int) middle.getX(), (int) middle.getY(), 2);
        }
    }

    /**
     * Draws the intersection points of the lines in red on the canvas.
     *
     * @param linesArray an array of Line objects
     */
    public static void drawIntersectionPoint(Line[] linesArray) {
        for (int i = 0; i < LINE_AMOUNT; i++) {
            for (int k = 0; k < LINE_AMOUNT; k++) {
                if (linesArray[i].equals(linesArray[k])) {
                    continue;
                } else if (linesArray[i].isIntersecting(linesArray[k])) {
                    Point intersectionPoint = linesArray[i].intersectionWith(linesArray[k]);

                    // Checking if the intersection is within the parameter range of both lines.
                    if (isPointWithinLine(intersectionPoint, linesArray[i]) && isPointWithinLine(intersectionPoint, linesArray[k])) {
                        DRAW_SURFACE.setColor(Color.red);
                        DRAW_SURFACE.drawCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 2);
                        DRAW_SURFACE.fillCircle((int) intersectionPoint.getX(), (int) intersectionPoint.getY(), 2);
                    }
                }
            }
        }
    }

    /**
     * Checks if a point is within the range of a given line.
     *
     * @param point the Point object to check
     * @param line  the Line object representing the range
     * @return true if the point is within the line's range, false otherwise
     */
    private static boolean isPointWithinLine(Point point, Line line) {
        double minX = Math.min(line.start.getX(), line.end.getX());
        double maxX = Math.max(line.start.getX(), line.end.getX());
        double minY = Math.min(line.start.getY(), line.end.getY());
        double maxY = Math.max(line.start.getY(), line.end.getY());

        return point.getX() >= minX && point.getX() <= maxX && point.getY() >= minY && point.getY() <= maxY;
    }

    /**
     * Draws triangles formed by intersecting lines in green on the canvas.
     *
     * @param linesArray an array of Line objects
     */
    public static void drawTriangles(Line[] linesArray) {
        for (int i = 0; i < linesArray.length; i++) {
            for (int k = i + 1; k < linesArray.length; k++) {
                for (int t = k + 1; t < linesArray.length; t++) {
                    if (linesArray[i].isIntersecting(linesArray[k], linesArray[t])
                            && linesArray[k].isIntersecting(linesArray[t], linesArray[i])) {

                        Point intersectionPoint = linesArray[i].intersectionWith(linesArray[k]);
                        Point secondPoint = linesArray[i].intersectionWith(linesArray[t]);
                        Point thirdPoint = linesArray[k].intersectionWith(linesArray[t]);

                        if (isPointWithinLine(intersectionPoint, linesArray[i])
                                && isPointWithinLine(intersectionPoint, linesArray[k])
                                && isPointWithinLine(secondPoint, linesArray[i]) && isPointWithinLine(secondPoint, linesArray[t])
                                && isPointWithinLine(thirdPoint, linesArray[k]) && isPointWithinLine(thirdPoint, linesArray[t])) {
                            Line[] triangleLines = new Line[3];

                            triangleLines[0] = new Line(intersectionPoint, secondPoint);
                            triangleLines[1] = new Line(intersectionPoint, thirdPoint);
                            triangleLines[2] = new Line(secondPoint, thirdPoint);

                            // Draw the triangle using the green color
                            DRAW_SURFACE.setColor(Color.green);
                            for (Line triangleLine : triangleLines) {
                                DRAW_SURFACE.drawLine(
                                        (int) triangleLine.start.getX(),
                                        (int) triangleLine.start.getY(),
                                        (int) triangleLine.end.getX(),
                                        (int) triangleLine.end.getY()
                                );
                            }
                        }
                    }
                }
            }
        }

        GUI.show(DRAW_SURFACE);
    }
}
