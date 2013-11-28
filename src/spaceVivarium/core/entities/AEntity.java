package spaceVivarium.core.entities;

import java.util.List;

import spaceVivarium.core.actions.ABehavior;
import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.maps.tiles.ATile;

public abstract class AEntity {

    private ATile laCase;
    protected int vision;
    protected List<ABehavior> comportements;

    public AEntity(ATile depart) {
        laCase = depart;
    }

    public AEntity(ATile depart, List<ABehavior> comps) {
        laCase = depart;
        comportements = comps;
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

    public double getDistance(AEntity entity) {
        int x1 = laCase.getX();
        int y1 = laCase.getY();
        int x2 = entity.getLaCase().getX();
        int y2 = entity.getLaCase().getY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * demande de l'action suivante de l'entité
     * 
     * @param list
     *            Liste des tile vues par l'entité
     * 
     * @return l'action demandée par l'entité
     */
    public abstract IAction update(List<ATile> list);

}
