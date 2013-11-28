package spaceVivarium.core.entities;

import java.awt.Graphics2D;
import java.util.List;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.maps.tiles.ATile;

public abstract class AEntity {

    private ATile currentTile;
    protected int vision;

    public AEntity(ATile depart) {
        currentTile = depart;
    }

    public ATile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(ATile destination) {
        this.currentTile = destination;
    }

    public int getVision() {
        return vision;
    }

    /**
     * demande de l'action suivante de l'entité
     * 
     * @param list
     *            Liste des tile vues par l'entité
     * @return l'action demandée par l'entité
     */
    public abstract IAction update(List<ATile> list);

    public abstract void print(Graphics2D g2d);

}
