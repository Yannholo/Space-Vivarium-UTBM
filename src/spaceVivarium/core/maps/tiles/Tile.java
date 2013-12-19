package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Tile extends ATile {

    private static String[] chemins = { "espace1.png", "espace2.png",
            "espace3.png", "espace2.png" };

    public Tile(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
