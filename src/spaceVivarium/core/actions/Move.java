package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move extends Action {

    private Entity entity;
    private ATile destination;

    public Move(Entity entity, ATile aTile) {
        this(entity, aTile, 0);
    }

    public Move(Entity entity, ATile aTile, int prio) {
        super(prio);
        this.entity = entity;
        this.destination = aTile;
    }

    @Override
    public void doItImpl() {
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
                // Les deux move ont la meme destination
                if (move.destination.equals(destination)) {
                    toRemove = move;
                    // une destination = le current de l'autre -> dependance
                } else if (move.destination.equals(entity.getLaCase())) {
                    // si l'entity change de case -> dependance
                    if (entity.getLaCase() != destination) {
                        move.setDependOn(this);
                        // si l'entity ne change pas de case -> conflit
                    } else {
                        toRemove = move;
                    }
                }

            } else if (action.getClass() == Nothing.class) {
                Nothing nothing = (Nothing) action;
                if (nothing.getEntity().getLaCase().equals(destination)) {
                    toRemove = this;
                }

            }
        }
        if (toRemove != null) {
            toRemove.setIsMade(false);
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
