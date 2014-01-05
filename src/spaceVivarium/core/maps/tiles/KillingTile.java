package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Kill;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Board;

public abstract class KillingTile extends ATile {

    public KillingTile(String[] cheminImages) {
        super(cheminImages);
    }

    @Override
    public final Action affect(Entity entity, Point point, Board map) {
        // System.out.println("Killing : " + entity);
        return new Kill(point, 10);

    }
}
