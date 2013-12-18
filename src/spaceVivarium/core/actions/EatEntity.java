package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.entities.Entity;

public class EatEntity extends Action {
    private Point selfEntity;
    private Point eatenEntity;

    public EatEntity(int prio, Point self, Point eaten) {
        super(prio);
        selfEntity = self;
        eatenEntity = eaten;
    }

    @Override
    public void doItImpl(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove) {
        entities.get(selfEntity).setAlive(false);
        entitiesToRemove.add(selfEntity);
        // eatenEntity.getLaCase().setEntity(null);
        // eatenEntity.setAlive(false);
        // TODO augmenter niveau de faim de selfEntity.
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

}
