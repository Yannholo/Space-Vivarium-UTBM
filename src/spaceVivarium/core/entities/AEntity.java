package spaceVivarium.core.entities;

import java.util.List;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.maps.tiles.ATile;

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

    /**
     * demande de l'action suivante de l'entit�
     * 
     * @param list
     *            Liste des tile vues par l'entit�
     * @return l'action demand�e par l'entit�
     */
    public abstract IAction update(List<ATile> list);

}
