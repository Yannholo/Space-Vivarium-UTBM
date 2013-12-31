package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.FieldUtils;
import spaceVivarium.utils.Vector2D;

/**
 * Follow behaviour, indique � l'entit� de suivre toutes les entit�s de type
 * typePrey .
 * 
 */
public class Follow extends Behaviour {
    // le type des entit�s � suivre
    private Class<? extends Entity> typePrey;

    public Follow(Class<? extends Entity> typeP) {
        typePrey = typeP;
    }

    /**
     * Give the position of the nearest entity of type typePrey.
     * 
     * @param tiles
     *            map of seen tiles
     * @param entities
     *            map of seen entities
     * @param current
     *            position of the behaving entity
     * @return the position of the nearest prey
     */
    private Point getNearestPrey(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        return FieldUtils.getNearestEntity(typePrey, entities, current);
    }

    /**
     * @param proie
     *            la proie � suivre pour l'entit� entity
     * @param list
     *            la liste des cases vues par l'entit� entity
     * @return la meilleur case pour suivre la proie
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
            action = new Move(current, FieldUtils.getRandomTile(tiles.keySet(),
                    current), 1);
        return action;
    }
}
