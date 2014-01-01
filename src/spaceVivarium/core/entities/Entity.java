package spaceVivarium.core.entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.behaviour.Behaviour;
import spaceVivarium.core.maps.tiles.ATile;

public abstract class Entity {

    protected int vision;
    protected int hunger;
    protected int reprodTimer;
    protected List<Behaviour> comportements;

    protected Entity(int vision) {
        this.vision = vision;
        comportements = new ArrayList<>();
    }

    protected Entity(int vision, int hunger) {
        this.vision = vision;
        this.hunger = hunger;
        comportements = new ArrayList<>();
    }

    protected Entity(int vision, int hunger, int reprodTimer) {
        this.vision = vision;
        this.hunger = hunger;
        this.reprodTimer = reprodTimer;
        comportements = new ArrayList<>();
    }

    public int getVision() {
        return vision;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hung) {
        hunger = hung;
    }

    public int getReproductionTimer() {
        return reprodTimer;
    }

    public void setReproductionTimer(int reprod) {
        reprodTimer = reprod;
    }

    /*
     * public double getDistance(Entity entity) { int x1 = laCase.getX(); int y1
     * = laCase.getY(); int x2 = entity.getLaCase().getX(); int y2 =
     * entity.getLaCase().getY(); return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 -
     * y2) * (y1 - y2)); }
     */

    /**
     * demande de l'action suivante de l'entité
     * 
     * @param list
     *            Liste des tile vues par l'entité
     * 
     * @return l'action demandée par l'entité
     */
    public final Action update(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        Action action = null;
        Action tmp = null;
        for (Behaviour comp : comportements) {
            tmp = comp.behave(tiles, entities, current);
            if (action == null || action.getPriority() < tmp.getPriority()) {
                action = tmp;
            }
        }
        return action;
    }

    public abstract void print(Graphics2D g, Point point);
}
