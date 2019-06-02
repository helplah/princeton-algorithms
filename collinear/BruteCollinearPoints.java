import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }
        int counter = 0;

        // if the argument to the constructor is null, if any point in the array is null
        // or if the argument to the constructor contains a repeated point
        for (int i = 0; i < points.length - 2; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Point cannot be null");
            }
            double slope = points[i].slopeTo(points[i++]);
            double nextSlope = points[i++].slopeTo(points[i + 2]);

            // what if there are multiple line segments?
            if (slope == nextSlope && counter < 3) {
                counter++;
            } else if (counter == 3) {
                LineSegment lineSegment = new LineSegment(points[i - 2], points[i + 2]);
                lineSegments.add(lineSegment);
                counter = 0;
            } else {
                counter = 0;
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] arraySegments = new LineSegment[lineSegments.size()];

        for (int x = 0; x < lineSegments.size(); x++) {
            arraySegments[x] = lineSegments.get(x);
        }
        return arraySegments;
    }

    public static void main(String[] args) { // YOUR CODE HERE
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
