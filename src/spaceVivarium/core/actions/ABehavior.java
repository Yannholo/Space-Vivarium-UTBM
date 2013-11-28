package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.maps.tiles.ATile;

/**
 * D�termine un comportement applicable � une entit�.
 * 
 */
public abstract class ABehavior {

    public abstract IAction behave(List<ATile> list);
}
