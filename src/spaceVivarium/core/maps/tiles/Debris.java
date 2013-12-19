package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Debris extends ATile {

    private static String[] chemins = { "Debris.png", "Debris2.png",
            "Debris3.png", "Debris4.png", "Debris5.png", "Debris6.png" };

    public Debris(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }

}
