/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    public final Comparator<Point> BY_SLOPE = new BySlope();
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    // in this class, I'm left with slopeOrder and BySlope
    private static class BySlope implements Comparator<Point> {
        public int compare(Point first, Point second) {
            int firstSlope = first.y / first.x, secondSlope = second.y / second.x;

            if (firstSlope < secondSlope) {
                return -1;
            } else if (firstSlope > secondSlope) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) { // YOUR CODE HERE
        // https://www.coursera.org/learn/algorithms-part1/programming/prXiW/collinear-points/discussions/threads/klTTwG6TEeeRiQpcwQKh-g
        // horizontal line segment: 0/(x differences which isn't zero) = 0 -> return 0
        // vertical line segment: (y differences which isn't zero)/0 = [ArithmeticException: division by zero] -> return Double.POSITIVE_INFINITY
        // degenerate line segment: 0/0 = [ArithmeticException: division by zero] since (x0, y0) and (x1, y1) are equal -> return Double.NEGATIVE_INFINITY
        double slope, yDifferences = that.y - this.y, xDifferences = that.x - this.x;
        try {
            slope = yDifferences / xDifferences;
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: Division by zero which means ");
            if (yDifferences == 0) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return Double.POSITIVE_INFINITY;
            }
        }

        System.out.println("slope: " + slope);
        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) { // YOUR CODE HERE
        if (this.y < that.y || this.y == that.y && this.x < that.x) {
            return -1;
        } else if (this.y > that.y || this.y == that.y && this.x > that.x) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() { // YOUR CODE HERE
        return new BySlope();
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) { // YOUR CODE HERE
        /*
        Point x = new Point(2, 2);
        Point y = new Point(2, 5);

        x.draw();
        y.draw();
        x.drawTo(y);
        System.out.println(x.compareTo(y));
        System.out.println(x.slopeTo(y)); */

        /*
        In in = new In(args[0]);
        int n = in.readInt();
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points.add(p);
        }

        Collections.sort(points);
        for (Point a : points)
            System.out.println(a.toString()); */

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points); // by right, FastCollinearPoints
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
