package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

/**
 * Détermine un comportement applicable à une entité.
 * 
 */
public abstract class Behaviour {

    public abstract Action behave(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current);
}
