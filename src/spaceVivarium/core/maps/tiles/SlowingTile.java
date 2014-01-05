package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public abstract class SlowingTile extends ATile {

    public SlowingTile(Point coord, String[] cheminImages) {
        super(coord, cheminImages);
    }

    @Override
    public final void affect(Entity entity) {
        System.out.println("Slowing : " + entity);
    }

}
