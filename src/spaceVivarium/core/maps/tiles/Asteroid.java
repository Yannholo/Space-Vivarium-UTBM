package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public class Asteroid extends ATile {

    private static String[] chemins = { "Asteroid.png" };

    public Asteroid(Point coord) {
        super(coord, chemins);

    }

    @Override
    public void affect(Entity entity) {
        // slowdown

    }

}
