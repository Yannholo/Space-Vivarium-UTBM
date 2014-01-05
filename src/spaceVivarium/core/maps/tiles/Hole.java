package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;

public class Hole extends ATile {

    private static String[] chemins = { "void3.png" };

    public Hole() {
        super(chemins);

    }

    @Override
    public Action affect(Entity entity, Point point) {
        return null;
        // teleport
    }

}
