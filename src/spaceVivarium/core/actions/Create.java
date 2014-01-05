package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.entities.Entity;

public class Create extends Action {

    private Point position;
    private Class<? extends Entity> type;

    public Create(int prio, Point pos, Class<? extends Entity> typeE) {
        super(prio);
        position = pos;
        type = typeE;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public Action inConflict(Action action) {

        /*
         * Action toRemove = null; if (action instanceof Move) { Move move =
         * (Move) action; if (move.getArrivee().equals(position)) toRemove =
         * this; } else if (action instanceof Create) { Create create = (Create)
         * action; if (create.getPosition().equals(position)) toRemove = action;
         * } else if (action instanceof Nothing) { Nothing nothing = (Nothing)
         * action; if (nothing.getCurrent().equals(position)) toRemove = this; }
         * 
         * if (toRemove != null) toRemove.setWillBeDone(false);
         */

        return null;
    }

    @Override
    public void doItImpl(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove) {
        try {

            entitiesToAdd.put(position, type.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
