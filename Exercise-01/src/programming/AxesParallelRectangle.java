package programming;

import java.util.Arrays;

public class TwoPointRectangle extends Rectangle {
    // This rectangle receives only two points and assumes the other two
    // so that the sides are parallel to the axes

    private final static String ERROR_MESSAGE_ILLEGAL_POINTS_COORDS =
            "Points should not lie on the same line!";

    public TwoPointRectangle() {
        this.oPoints = new Point[this.getPointsCount()];
        this.oPoints[0] = new Point(1,1);
        this.oPoints[1] = new Point(-1,1);
        this.oPoints[2] = new Point(-1,-1);
        this.oPoints[3] = new Point(1,-1);
    }

    public TwoPointRectangle(double... pointsCoords) {
        if (pointsCoords[0] == pointsCoords[2] || pointsCoords[1] == pointsCoords[3])
            throw new IllegalArgumentException(ERROR_MESSAGE_ILLEGAL_POINTS_COORDS);

        this.oPoints = new Point[this.getPointsCount()];
        this.oPoints[0] = new Point(pointsCoords[0], pointsCoords[1]);
        this.oPoints[1] = new Point(pointsCoords[0], pointsCoords[3]);
        this.oPoints[2] = new Point(pointsCoords[2], pointsCoords[3]);
        this.oPoints[3] = new Point(pointsCoords[2], pointsCoords[1]);
    }

    public TwoPointRectangle(Point... points) {
        this(
                Arrays.stream(points)
                        .flatMapToDouble(point -> Arrays.stream(new double[]{point.iX, point.iY}))
                        .toArray()
        );
    }

    @Override
    public boolean isInside(Point point) {
        return Arrays.stream(this.getPoints())
                .map(p -> p.iX)
                .max(Double::compareTo)
                .get() >= point.iX &&
                Arrays.stream(this.getPoints())
                        .map(p -> p.iY)
                        .max(Double::compareTo)
                        .get() >= point.iY;
    }

    @Override
    public TwoPointRectangle intersectionRect(Rectangle r) {
        if (Arrays.stream(this.getPoints()).noneMatch(r::isInside))
            return null; // No intersection

        return null; // TODO: Implement
    }
}
