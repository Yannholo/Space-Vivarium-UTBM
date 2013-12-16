package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

/**
 * D�termine un comportement applicable � une entit�.
 * 
 */
public abstract class Behaviour {

    public abstract Action behave(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current);
}
