package spaceVivarium.core.actions;

import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move implements IAction {

    private AEntity entity;
    private ATile destination;

    public Move(AEntity bestiole, ATile aTile) {
        this.entity = bestiole;
        this.destination = aTile;
    }

    @Override
    public void doIt() {
        entity.getLaCase().setBestiole(null);
        entity.setLaCase(destination);
        destination.setBestiole(entity);
    }

}
