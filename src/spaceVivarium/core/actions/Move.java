package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Move implements Action {

    private Entity entity;
    private ATile destination;
    private int priority;

    public Move(Entity bestiole, ATile aTile) {
        this.entity = bestiole;
        this.destination = aTile;
    }

    public Move(Entity bestiole, ATile aTile, int prio) {
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
