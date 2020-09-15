package programming;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class TwoDimensionalFigure {
    protected Point[] oPoints;

    public TwoDimensionalFigure() {
        this.oPoints = new Point[getPointsCount()];
        for (int i = 0; i < this.oPoints.length; i++){
            this.oPoints[i] = new Point();
        }
    }

    public TwoDimensionalFigure(double... pointsCoords){
        if (pointsCoords.length / 2 != getPointsCount()) // Half the args because input comes in (x, y) tuples
            throw new IllegalArgumentException("Figure has " + this.getPointsCount() + " points!");

        this.oPoints = new Point[getPointsCount()];
        for (int i = 0; i < pointsCoords.length; i += 2) {
            this.oPoints[i / 2] =
                    new Point(pointsCoords[i], pointsCoords[i + 1]);
        }
    }

    public TwoDimensionalFigure(Point... points) {
        if (points.length != getPointsCount())
            throw new IllegalArgumentException("Figure has " + this.getPointsCount() + " points!");

        this.oPoints = Arrays.copyOf(points, points.length);
    }

    public Point[] getPoints() {
        return this.oPoints;
    }

    protected abstract int getPointsCount();

    // 4
    public double calcArea() {
        double result = 0;
        for (int i = 1; i < this.getPoints().length; i++) {
            result +=
                    this.getPoints()[i - 1].iX * this.getPoints()[i].iY -
                            this.getPoints()[i - 1].iY * this.getPoints()[i].iX;
        }

        return Math.abs(result / 2);
    }

    // 5
    public int compareTo(Object r) {
        final double areaDiff = ((Rectangle) r).calcArea() - this.calcArea();
        return Double.compare(areaDiff, 0);
    }

    // 6

    @Override
    public String toString() {
        return "com.nvna.Rectangle{" +
                "pointsCount=" + this.getPointsCount() + "," +
                "oPoints{" +
                Arrays.stream(this.getPoints())
                        .map(p -> "x=" + p.iX + ", y=" + p.iY)
                        .collect(Collectors.joining(",\n")) +
                "}" +
                '}';
    }

    // 7
    public boolean equals(Rectangle r) {
        return this.calcArea() == r.calcArea(); // check only for area

        // return Arrays.stream(this.getPoints()) // full figure check
        //      .allMatch(p -> Arrays.asList(r.getPoints()).contains(p));
    }

    // 8
    public void translateX(int iPoints) {
        Arrays.stream(this.getPoints())
                .forEach(p -> p.translateX(iPoints));
    }

    // 9
    public void translateY(int iPoints) {
        Arrays.stream(this.getPoints())
                .forEach(p -> p.translateY(iPoints));
    }

    // 10
    public void translateXY(int iPoints) {
        Arrays.stream(this.getPoints())
                .forEach(p -> {
                    p.translateX(iPoints);
                    p.translateY(iPoints);
                });
    }
}
