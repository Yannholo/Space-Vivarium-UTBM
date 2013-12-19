package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Sun extends ATile {

    private static String[] chemins = { "Sun.png" };

    public Sun(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
