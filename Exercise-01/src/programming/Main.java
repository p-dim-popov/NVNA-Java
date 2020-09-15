package programming;

import java.lang.instrument.IllegalClassFormatException;

public class Main {

    public static void main(String[] args) {
        var rect1 = new TwoPointRectangle(1, 1, -2, -1);
        var rect2 = new TwoPointRectangle(0, 1, -2, -2);

        System.out.println(rect1);
        System.out.println(rect2);

        rect1.translateXY(-1);
        System.out.println(rect1);

        if (!rect1.equals(new TwoPointRectangle(1, 1, -4, -1)))
            System.out.println("Rectangles are different");

        try {
            if(rect1.compareTo(new TwoPointRectangle(1, 1, -4, -1)) == -1)
                System.out.println("Anonymous rectangle is bigger");
        } catch (IllegalClassFormatException e) {
            e.printStackTrace();
        }

        System.out.println(rect1.unionRect(rect2));
        System.out.println(rect1.intersectionRect(rect2));
    }
}
