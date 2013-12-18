package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.entities.Entity;

public class Kill extends Action {
    private Point selfEntity;
    private Point killedEntity;

    public Kill(Point self, Point killed, int prio) {
        super(prio);
        selfEntity = self;
        killedEntity = killed;
    }

    public Point getSelfEntity() {
        return selfEntity;
    }

    public void setSelfEntity(Point selfEntity) {
        this.selfEntity = selfEntity;
    }

    public Point getKilledEntity() {
        return killedEntity;
    }

    public void setKilledEntity(Point killedEntity) {
        this.killedEntity = killedEntity;
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

    @Override
    public void doItImpl(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove) {
        entities.get(selfEntity).setAlive(false);
        entitiesToRemove.add(selfEntity);
        // killedEntity.getLaCase().setEntity(null);
        // killedEntity.setAlive(false);
    }

}
