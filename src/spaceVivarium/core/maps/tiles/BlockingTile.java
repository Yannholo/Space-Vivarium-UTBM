package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public abstract class BlockingTile extends ATile {

    public BlockingTile(Point coord, String[] cheminImages) {
        super(coord, cheminImages);
    }

    @Override
    public final void affect(Entity entity) {
        System.out.println("Bloque : " + entity);

    }

}
