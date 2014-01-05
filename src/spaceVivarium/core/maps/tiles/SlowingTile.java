package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;

public abstract class SlowingTile extends ATile {

    public SlowingTile(String[] cheminImages) {
        super(cheminImages);
    }

    @Override
    public final Action affect(Entity entity, Point point) {
        System.out.println("Slowing : " + entity);
        entity.decreaseActionCounter(1);
        return null;
    }

}
