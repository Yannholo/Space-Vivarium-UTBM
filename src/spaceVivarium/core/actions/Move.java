package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.entities.TestEntity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move implements IAction {

    private AEntity entity;
    private ATile destination;

    public Move(TestEntity bestiole, ATile aTile) {
        this.entity = bestiole;
        this.destination = aTile;
    }

    @Override
    public void doIt(List<ATile> updatedTile) {
        updatedTile.add(entity.getCurrentTile());
        updatedTile.add(destination);
        entity.getCurrentTile().setBestiole(null);
        entity.setCurrentTile(destination);
        destination.setBestiole(entity);
    }

}
