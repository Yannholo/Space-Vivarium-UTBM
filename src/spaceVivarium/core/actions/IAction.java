package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.maps.tiles.ATile;

public interface IAction {
    public void doIt(List<ATile> updatedTiles);
}
