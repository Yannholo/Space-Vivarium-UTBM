package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.maps.tiles.ATile;

/**
 * Détermine un comportement applicable à une entité.
 * 
 */
public abstract class Behavior {

    public abstract List<Action> behave(List<ATile> list);
}
