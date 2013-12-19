package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Planet extends ATile {

    private static String[] chemins = { "Planet1.png", "Planet2.png",
            "Planet3.png", "Planet4.png", "Planet5.png", "Planet6.png" };

    public Planet(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
