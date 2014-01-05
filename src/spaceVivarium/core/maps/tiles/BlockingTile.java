package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Board;

public abstract class BlockingTile extends ATile {

    public BlockingTile(String[] cheminImages) {
        super(cheminImages);
    }

    @Override
    public final Action affect(Entity entity, Point point, Board map) {
        // System.out.println("Bloque : " + entity);
        return null;
    }

}
