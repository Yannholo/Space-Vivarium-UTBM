package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;

public class Void extends ATile {

    private static String[] chemins = { "Void.png", "Void3.png", "Void2.png",
            "Void4.png", "Void5.png", "Void6.png" };

    public Void() {
        super(chemins);

    }

    @Override
    public Action affect(Entity entity, Point point) {
        return null;
        // nothing

    }

}
