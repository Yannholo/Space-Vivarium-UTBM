package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public class Sun extends ATile {

    private static String[] chemins = { "Sun.png" };

    public Sun(Point coord) {
        super(coord, chemins);

    }

    @Override
    public void affect(Entity entity) {
        // death

    }
}
