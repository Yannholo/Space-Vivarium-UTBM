package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Board;

public class Planet extends ATile {

    private static String[] chemins = { "Planet1.png", "Planet2.png",
            "Planet3.png", "Planet4.png", "Planet5.png", "Planet6.png" };

    public Planet() {
        super(chemins);

    }

    @Override
    public Action affect(Entity entity, Point point, Board map) {
        entity.setHunger(entity.getHunger() + 10);
        return null;
    }

}
