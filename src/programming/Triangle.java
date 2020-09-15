package programming;

public class Triangle extends TwoDimensionalFigure{
    public Triangle() {
        super();
    }

    public Triangle(double... pointsCoords){
        super(pointsCoords);
    }

    public Triangle(Point... points){
        super(points);
    }

    @Override
    protected int getPointsCount() {
        return 3;
    }
}
