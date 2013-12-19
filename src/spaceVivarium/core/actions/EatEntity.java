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
        // entities.get(eatenEntity).setAlive(false);
        entitiesToRemove.add(eatenEntity);
        entities.get(selfEntity).setHunger(
                entities.get(selfEntity).getHunger() + 20);
        // TODO augmenter niveau de faim de selfEntity.
    }

    @Override
    public Action inConflict(Action action) {
        Action toRemove = null;
        if (action instanceof Move) {
            Move move = (Move) action;
            if (move.getDepart().equals(eatenEntity))
                toRemove = action;
            if (toRemove != null)
                toRemove.setWillBeDone(false);
        }

        return toRemove;
    }

}
