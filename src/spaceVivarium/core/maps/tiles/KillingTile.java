package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public abstract class KillingTile extends ATile {

    public KillingTile(Point coord, String[] cheminImages) {
        super(coord, cheminImages);
    }

    @Override
    public final void affect(Entity entity) {
        System.out.println("Killing : " + entity);

    }

}
