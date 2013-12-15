package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move implements Action {

    private Entity entity;
    private ATile destination;
    private int priority;

    public Move(Entity bestiole, ATile aTile) {
        this.entity = bestiole;
        this.destination = aTile;
    }

    public Move(Entity bestiole, ATile aTile, int prio) {
        this.entity = bestiole;
        this.destination = aTile;
        this.priority = prio;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int prio) {
        this.priority = prio;
    }

    @Override
    public void doIt() {
        if (entity.getLaCase().getEntity() == entity) {
            entity.getLaCase().setEntity(null);
        }
        entity.setLaCase(destination);
        destination.setEntity(entity);
    }

    @Override
    public Action inConflict(Action action) {
        Action toRemove = null;
        if (this != action) {
            if (action.getClass() == Move.class) {
                Move move = (Move) action;
                if (move.destination.equals(destination)) {
                    toRemove = move;
                }

            } else if (action.getClass() == Nothing.class) {
                Nothing nothing = (Nothing) action;
                if (nothing.getEntity().getLaCase().equals(destination)) {
                    toRemove = this;
                }

            }
        }
        return toRemove;
    }

    @Override
    public String toString() {
        return "Move : " + entity + " " + entity.getLaCase().getX() + ","
                + entity.getLaCase().getY() + " -> " + destination.getX() + ","
                + destination.getY();
    }
}
