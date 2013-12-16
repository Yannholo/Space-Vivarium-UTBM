package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.Map;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move extends Action {

    Point depart, arrivee;

    public Move(Point depart, Point arrivee) {
        this(depart, arrivee, 0);
    }

    public Move(Point depart, Point arrivee, int prio) {
        super(prio);
        this.depart = depart;
        this.arrivee = arrivee;
    }

    @Override
    public void doItImpl(Map<Point, Entity> entities, Map<Point, ATile> tiles) {
        Entity toMove = entities.remove(depart);

        entities.put(arrivee, toMove);
    }

    @Override
    public Action inConflict(Action action) {
        Action toRemove = null;
        if (this != action) {
            if (action.getClass() == Move.class) {
                Move move = (Move) action;
                // Les deux move ont la meme destination
                if (move.arrivee.equals(arrivee)) {
                    toRemove = move;
                    // une destination = le current de l'autre -> dependance
                } else if (move.arrivee.equals(depart)) {
                    // si l'entity change de case -> dependance
                    if (depart != arrivee) {
                        move.setDependOn(this);
                        // si l'entity ne change pas de case -> conflit
                    } else {
                        toRemove = move;
                    }
                }

            } else if (action.getClass() == Nothing.class) {
                Nothing nothing = (Nothing) action;
                if (nothing.getCurrent().equals(arrivee)) {
                    toRemove = this;
                }

            }
        }
        if (toRemove != null) {
            toRemove.setWillBeDone(false);
        }
        return toRemove;
    }

    @Override
    public String toString() {
        return "Move : " + depart + " -> " + arrivee;
    }
}
