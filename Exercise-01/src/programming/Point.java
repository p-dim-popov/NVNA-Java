package programming;

public class Point {
    public double iX;
    public double iY;

    public Point() {
        this.iX = 0;
        this.iY = 0;
    }

    public Point(double x, double y) {
        this.iX = x;
        this.iY = y;
    }

    public static double getXDistance(Point left, Point right) {
        return Math.abs(left.iX - right.iX);
    }

    public static double getYDistance(Point left, Point right) {
        return Math.abs(left.iY - right.iY);
    }

    public boolean equals(Point p) {
        return this.iX == p.iX && this.iY == p.iY;
    }

    public void translateX(int i) {
        this.iX += i;
    }

    public void translateY(int i){
        this.iY += i;
    }
}
