package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public class Planet extends ATile {

    private static String[] chemins = { "Planet1.png", "Planet2.png",
            "Planet3.png", "Planet4.png", "Planet5.png", "Planet6.png" };

    public Planet(Point coord) {
        super(coord, chemins);

    }

    @Override
    public void affect(Entity entity) {
        // feed

    }

}
