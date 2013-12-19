package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Hole extends ATile {

    private static String[] chemins = { "void3.png" };

    public Hole(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
