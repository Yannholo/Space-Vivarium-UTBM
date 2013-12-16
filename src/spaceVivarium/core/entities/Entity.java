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
    protected boolean alive;
    protected List<Behaviour> comportements;

    protected Entity(int vision) {
        this.vision = vision;
        comportements = new ArrayList<>();
    }

    public int getVision() {
        return vision;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean b) {
        alive = b;
    }

    /*
     * public double getDistance(Entity entity) { int x1 = laCase.getX(); int y1
     * = laCase.getY(); int x2 = entity.getLaCase().getX(); int y2 =
     * entity.getLaCase().getY(); return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 -
     * y2) * (y1 - y2)); }
     */

    /**
     * demande de l'action suivante de l'entit�
     * 
     * @param list
     *            Liste des tile vues par l'entit�
     * 
     * @return l'action demand�e par l'entit�
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
