import java.awt.Point;

import spaceVivarium.utils.Vector2D;

public class Test {

    public static void main(String[] args) {
        Point p1, p2;

        p1 = new Point(2, 2);
        p2 = new Point(2, 3);
        System.out.println(p1 + " -> " + p2 + " : " + p1.distance(p2));

        p1 = new Point(2, 2);
        p2 = new Point(3, 3);
        System.out.println(p1 + " -> " + p2 + " : " + p1.distance(p2));

        p1 = new Point(2, 2);
        p2 = new Point(3, 1);
        System.out.println(p1 + " -> " + p2 + " : " + p1.distance(p2));

        Vector2D vector2d1, vector2d2;

        vector2d1 = new Vector2D(2, 2);
        vector2d2 = new Vector2D(2, 3);
        System.out.println(vector2d1 + " -> " + vector2d2 + " : "
                + vector2d1.getDistance(vector2d2));

        vector2d1 = new Vector2D(2, 2);
        vector2d2 = new Vector2D(3, 3);
        System.out.println(vector2d1 + " -> " + vector2d2 + " : "
                + vector2d1.getDistance(vector2d2));

        vector2d1 = new Vector2D(2, 2);
        vector2d2 = new Vector2D(3, 1);
        System.out.println(vector2d1 + " -> " + vector2d2 + " : "
                + vector2d1.getDistance(vector2d2));

    }

}
