package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.entities.Entity;

public class Nothing extends Action {

    private Point current;

    public Nothing(Point current) {
        super(0);
        this.current = current;
    }

    @Override
    public void doItImpl(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove) {
        // nothing
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

    public Object getCurrent() {
        return current;
    }

    @Override
    public String toString() {
        return "Nothing : " + current;
    }

}
