package programming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Rectangle extends Figure {
    public Rectangle() {
        super();
    }

    public Rectangle(double... pointsCoords) {
        super(pointsCoords);
    }

    public Rectangle(Point... points) {
        super(points);
    }

    @Override
    public int getPointsCount() {
        return 4;
    }

    // 11
    public boolean isInside(int ptX, int ptY) {
        var point = new Point(ptX, ptY);
        return this.isInside(point);
    }

    public boolean isInside(Point point) { //P(x,y)
        //Variant 1
        /*
        Let P(x,y), and rectangle A(x1,y1),B(x2,y2),C(x3,y3),D(x4,y4)
        Calculate the sum of areas of △APD,△DPC,△CPB,△PBA
        If this sum is greater than the area of the rectangle,
        then P(x,y) is outside the rectangle. Else if this sum is equal
        to the area of the rectangle (observe that this sum cannot be less than the latter) then
                1. if area of any of the triangles is 0,
            then P(x,y) is on the rectangle (in fact on that line corresponding to the triangle of area=0).
            Observe that the equality of the sum is necessary; it is not sufficient that area=0),
                2. else P(x,y) is is inside the rectangle.

        Acceptably this approach needs substantial amount of computation.
        This approach can be employed to any irregular polygon, too.
        */

        // Assume [A,B,C,D] = this.getPoints();
        var trianglesArea = IntStream
                .range(0, this.getPointsCount())
                .mapToObj(i ->
                        new Triangle(
                                this.getPoints()[i],
                                i == this.getPointsCount() - 1 ? this.getPoints()[0] : this.getPoints()[i + 1],
                                point))
                .map(Triangle::calcArea)
                .mapToDouble(Double::doubleValue)
                .sum();

        return trianglesArea <= this.calcArea();

        //Variant 2
        /*
        Another way is to calculate the perpendicular distances of P(x,y) from all the 4 lines AB,CD,AD,BC

        To be inside the rectangle, the perpendicular distances from AB,PAB
        (say) and from CD,PCD(say) must be less than |AD|=|BC| and the perpendicular distances
        from AD,PAD(say) and from BC,PBC(say) must be less than |CD|=|AB|.
        Here, the areas of each of the four triangles < 12 the area of the rectangle.

            1. If one of the perpendicular distances is greater than the respective length, then P(x,y)
        is outside the rectangle.
        This essentially implies and is implied by the statement : the area of the respective triangle > 12
        the area of the rectangle (as commented by Ben Voigt) as △APD=12AD⋅PAD.

            2. Else if PAB=0
        and PCD=|AD| , then P(x,y) is on AB . So, △PBA=0 and △PCD=12 the area of the rectangle.
        Observe that in this case, the remaining two perpendicular distances PAD,PBC
        must be ≤ |AB|=|CD|, PBC=|AB|⟹P(x,y) is lies on AD i.e, P coincides with A as it is already on AB .
        */

        //Variant 3
        /*
        P of coordinates (x,y) is inside the rectangle if
            (0<AP⋅AB<AB⋅AB)∧(0<AP⋅AD<AD⋅AD)

        (scalar product of vectors)
        */
    }

    // 12
    public Rectangle unionRect(Rectangle r) {
        if (Arrays.stream(this.getPoints()).noneMatch(r::isInside))
            return null; //No intersection

        var allPoints = Arrays.copyOf(this.getPoints(), this.getPointsCount() + r.getPointsCount());
        System.arraycopy(r.getPoints(), 0, allPoints, this.getPointsCount(), r.getPointsCount());

        var northEastPoint = new Point(
                Arrays.stream(allPoints)
                        .max(Comparator.comparingDouble(a -> a.iX))
                        .get().iX,
                Arrays.stream(allPoints)
                        .max(Comparator.comparingDouble(a -> a.iY))
                        .get().iY
        );

        var northWestPoint = new Point(
                Arrays.stream(allPoints)
                        .min(Comparator.comparing(p -> p.iX))
                        .get().iX,
                northEastPoint.iY
        );

        var southWestPoint = new Point(
                northWestPoint.iX,
                Arrays.stream(allPoints)
                        .min(Comparator.comparing(p -> p.iY))
                        .get().iY
        );

        var southEastPoint = new Point(
                Arrays.stream(allPoints)
                        .max(Comparator.comparing(p -> p.iX))
                        .get().iX,
                southWestPoint.iY
        );

        return new Rectangle(northEastPoint, northWestPoint, southWestPoint, southEastPoint);
        //Note: returned rectangle is parallel to {Ox, Oy}
    }

    // 13
    public Rectangle intersectionRect(Rectangle r) {
        if (Arrays.stream(this.getPoints()).noneMatch(r::isInside))
            return null; //No intersection

        throw null; //TODO: implement
    }
}
