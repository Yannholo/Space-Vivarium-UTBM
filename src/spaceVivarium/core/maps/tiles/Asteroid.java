package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Asteroid extends SlowingTile {

    private static String[] chemins = { "Asteroid.png" };

    public Asteroid(Point coord) {
        super(coord, chemins);

    }

}
