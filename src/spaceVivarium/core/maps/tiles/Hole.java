package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public class Hole extends ATile {

    private static String[] chemins = { "void3.png" };

    public Hole(Point coord) {
        super(coord, chemins);

    }

    @Override
    public void affect(Entity entity) {
        // teleport

    }

}
