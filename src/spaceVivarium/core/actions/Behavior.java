package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.maps.tiles.ATile;

/**
 * D�termine un comportement applicable � une entit�.
 * 
 */
public abstract class Behavior {

    public abstract List<Action> behave(List<ATile> list);
}
