package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.entities.Entity;

public class Kill extends Action {
    private Point killedEntity;

    public Kill(Point killed, int prio) {
        super(prio);
        killedEntity = killed;
    }

    public Point getKilledEntity() {
        return killedEntity;
    }

    public void setKilledEntity(Point killedEntity) {
        this.killedEntity = killedEntity;
    }

    @Override
    public Action inConflict(Action action) {
        Action toRemove = null;
        if (action instanceof Move) {
            Move move = (Move) action;
            if (move.getDepart().equals(killedEntity))
                toRemove = action;
            if (toRemove != null)
                toRemove.setWillBeDone(false);
        }

        return toRemove;
    }

    @Override
    public void doItImpl(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove) {
        // entities.get(killedEntity).setAlive(false);
        entitiesToRemove.add(killedEntity);
        // killedEntity.getLaCase().setEntity(null);
        // killedEntity.setAlive(false);
    }

    @Override
    public String toString() {
        return "Kill : " + killedEntity;
    }

}
