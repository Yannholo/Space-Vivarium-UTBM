package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Craziness extends Behaviour {

    @Override
    public Action behave(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        Action todo = null;
        List<Point> destinations = new ArrayList<>(tiles.keySet());
        do {
            if (destinations.size() <= 0) {
                // si l'entité ne vois rien, elle ne fait rien
                todo = new Nothing(current);
            } else {
                // sinon elle bouge aléatoirement sur une Tile
                Point destination = destinations
                        .get((int) (Math.random() * destinations.size()));
                if (!entities.containsKey(destination)) {
                    todo = new Move(current, destination);
                } else {
                    destinations.remove(destination);
                }
            }
        } while (todo == null);

        return todo;
    }

}
