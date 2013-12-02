package spaceVivarium.core.actions;

import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move implements IAction {

    private AEntity entity;
    private ATile destination;
    private int priority;

    public Move(AEntity bestiole, ATile aTile) {
        this.entity = bestiole;
        this.destination = aTile;
    }

    public Move(AEntity bestiole, ATile aTile, int prio) {
        this.entity = bestiole;
        this.destination = aTile;
        this.priority = prio;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int prio) {
        this.priority = prio;
    }

    @Override
    public void doIt() {
        entity.getLaCase().setBestiole(null);
        entity.setLaCase(destination);
        destination.setBestiole(entity);
    }

}
