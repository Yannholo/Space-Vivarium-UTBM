package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.maps.tiles.ATile;

/**
 * Détermine un comportement applicable à une entité.
 * 
 */
public abstract class ABehavior {

    public abstract IAction behave(List<ATile> list);
}
