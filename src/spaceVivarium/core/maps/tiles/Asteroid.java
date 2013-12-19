package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Asteroid extends ATile {

    private static String[] chemins = { "Asteroid.png" };

    public Asteroid(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
