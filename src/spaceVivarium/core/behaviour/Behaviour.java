package spaceVivarium.core.behaviour;

import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.maps.tiles.ATile;

/**
 * D�termine un comportement applicable � une entit�.
 * 
 */
public abstract class Behaviour {

    public abstract List<Action> behave(List<ATile> list);
}
