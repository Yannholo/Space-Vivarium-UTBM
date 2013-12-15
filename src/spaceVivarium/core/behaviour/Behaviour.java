package spaceVivarium.core.behaviour;

import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.maps.tiles.ATile;

/**
 * Détermine un comportement applicable à une entité.
 * 
 */
public abstract class Behaviour {

    public abstract List<Action> behave(List<ATile> list);
}
