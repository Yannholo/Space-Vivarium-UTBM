package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.Vector2D;

/**
 * Follow behavior, indique à l'entité entity de suivre toutes les entités de
 * type typePrey .
 * 
 */
public class Follow extends Behaviour {
    // le type des entités à suivre
    private Class<? extends Entity> typePrey;

    public Follow(Class<? extends Entity> typeP) {
        typePrey = typeP;
    }

    /**
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return la proie AEntity la plus proche de l'entité entity
     */
    private Point getNearestPrey(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        Point res = null;
        double distance = 10000;
        double newDistance = 0;
        for (Entry<Point, Entity> entry : entities.entrySet()) {

            // l'entité existe sur la case.
            if (entry.getValue().getClass().equals((typePrey))) {
                // l'entité est une proie.
                newDistance = current.distance(entry.getKey());
                if (newDistance < distance) {
                    // l'entité est la plus proche actuellement
                    res = entry.getKey();
                    distance = newDistance;
                }
            }

        }
        return res;
    }

    private Point getRandomTile(Collection<Point> list, Point current) {
        ArrayList<Point> tiles = this.getAdjacentCoords(list, current);
        return tiles.get((int) (Math.random() * tiles.size()));
    }

    public ArrayList<Point> getAdjacentCoords(
            Collection<Point> list, Point current) {
        ArrayList<Point> adj = new ArrayList<Point>();
        Vector2D self = new Vector2D(current.x, current.y);
        for (Point point : list) {
            Vector2D dest = new Vector2D(point.x, point.y);
            if (self.getDistance(dest) <= 1.5)
                adj.add(point);
        }
        adj.add(current);
        return adj;
    }

    /**
     * @param proie
     *            la proie à suivre pour l'entité entity
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return la case la plus sûre pour éviter enemy
     */
    private Point getClosestTile(
            Point proie, Point current, Map<Point, ATile> tiles) {
        Point res = null;
        Vector2D pPrey = new Vector2D(proie.x, proie.y);
        Vector2D pSelf = new Vector2D(current.x, current.y);
        Vector2D normalizedDir = new Vector2D(pPrey.getX() - pSelf.getX(),
                pPrey.getY() - pSelf.getY()).normalize();
        Point arrive = pSelf.translate(normalizedDir).getIntPoint();
        for (Point point : tiles.keySet()) {
            if (point.x == arrive.x && point.y == arrive.y)
                res = point;
        }

        return res;
    }

    @Override
    public Action behave(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        Action action = null;
        Point proie = getNearestPrey(tiles, entities, current);
        if (proie != null) {
            Point safeTile = getClosestTile(proie, current, tiles);
            if (safeTile != null)
                action = new Move(current, safeTile, 3);
            else
                action = new Nothing(current);

        } else
            action = new Move(current, getRandomTile(tiles.keySet(), current),
                    3);
        return action;
    }
}
