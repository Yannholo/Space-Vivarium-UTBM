package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.Map;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Nothing extends Action {

    private Point current;

    public Nothing(Point current) {
        super(0);
        this.current = current;
    }

    @Override
    public void doItImpl(Map<Point, Entity> entities, Map<Point, ATile> tiles) {
        // nothing
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

    public Object getCurrent() {
        return current;
    }

}
