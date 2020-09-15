package programming;

public class Main {

    public static void main(String[] args) {
        var rect1 = new Rectangle(1,1,-1,1,-1,-1,1,-1);
        rect1.translateXY(2);
        var rect2 = new Rectangle(0,1,0,0,-1,0,-1,1);

        System.out.println(rect1.unionRect(rect2).toString());
    }
}
