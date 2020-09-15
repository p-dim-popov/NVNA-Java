package programming;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.stream.Collectors;

public abstract class Figure {
    protected Point[] oPoints;

    public Figure() {
        this.oPoints = new Point[getPointsCount()];
        for (int i = 0; i < this.oPoints.length; i++) {
            this.oPoints[i] = new Point();
        }
    }

    public Figure(double... pointsCoords) {
        if (pointsCoords.length / 2 != getPointsCount()) // Half the args because input comes in (x, y) tuples
            throw new IllegalArgumentException("Figure has " + this.getPointsCount() + " points!");

        this.oPoints = new Point[getPointsCount()];
        for (int i = 0; i < pointsCoords.length; i += 2) {
            this.oPoints[i / 2] =
                    new Point(pointsCoords[i], pointsCoords[i + 1]);
        }
    }

    public Figure(Point... points) {
        this(
                Arrays.stream(points)
                        .flatMapToDouble(point -> Arrays.stream(new double[]{point.iX, point.iY}))
                        .toArray()
        );
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
    public int compareTo(Object f) throws IllegalClassFormatException {
        if (f == null)
            throw new NullPointerException();
        if (!(f instanceof Figure))
            throw new IllegalClassFormatException();

        var figure = (Figure) f;
        return Double.compare(this.calcArea(), figure.calcArea());
    }

    // 6

    @Override
    public String toString() {
        return "Figure{\n" +
                "\tpointsCount=" + this.getPointsCount() + ",\n" +
                "\toPoints{\n" +
                Arrays.stream(this.getPoints())
                        .map(p -> "\t\tx=" + p.iX + ", y=" + p.iY)
                        .collect(Collectors.joining(",\n")) + "\n" +
                "\t}\n" +
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
