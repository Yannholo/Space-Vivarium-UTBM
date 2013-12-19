package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Void extends ATile {

    private static String[] chemins = { "Void.png", "Void3.png", "Void2.png",
            "Void4.png", "Void5.png", "Void6.png" };

    public Void(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
