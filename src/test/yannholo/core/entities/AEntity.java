package test.yannholo.core.entities;

import java.util.List;

import test.yannholo.core.actions.IAction;
import test.yannholo.core.maps.tiles.ATile;

public abstract class AEntity {

    private ATile laCase;
    protected int vision;

    public AEntity(ATile depart) {
        laCase = depart;
    }

    public ATile getLaCase() {
        return laCase;
    }

    public void setLaCase(ATile destination) {
        this.laCase = destination;
    }

    public int getVision() {
        return vision;
    }

    public abstract IAction update(List<ATile> list);

}
