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
import spaceVivarium.utils.FieldUtils;
import spaceVivarium.utils.Vector2D;

/**
 * Escape behavior, indique � l'entit� entity de fuire toutes les entit�s de
 * type typeEnemy .
 * 
 */
public class Escape extends Behaviour {
    // le type des entit�s � �viter
    private Class<? extends Entity> typeEnemy;

    public Escape(Class<? extends Entity> typeE) {
        typeEnemy = typeE;
    }

    /**
     * Give the position of the nearest entity of type typeEnemy.
     * 
     * @param tiles
     *            map of seen tiles
     * @param entities
     *            map of seen entities
     * @param current
     *            position of the behaving entity
     * @return the position of the nearest enemy
     */
    private Point getNearestEnemy(Point current, Map<Point, Entity> entities) {
        return FieldUtils.getNearestEntity(this.typeEnemy, entities, current);

    }

    /**
     * @param enemyCoord
     *            l'ennemi � fuir par l'entit� entity
     * @param tiles
     *            la liste des cases vues par l'entit� entity
     * @return la case la plus s�re pour �viter enemy
     */
    private Point getSafestTile(
            Point current, Point enemyCoord, Map<Point, ATile> tiles) {
        Point res = null;
        Vector2D pEnemy = new Vector2D(enemyCoord.x, enemyCoord.y);
        Vector2D pSelf = new Vector2D(current.x, current.y);
        Vector2D normalizedDir = new Vector2D(pSelf.getX() - pEnemy.getX(),
                pSelf.getY() - pEnemy.getY()).normalize();
        Point arrive = pSelf.translate(normalizedDir).getIntPoint();
        for (Entry<Point, ATile> entry : tiles.entrySet()) {
            if (entry.getKey().x == arrive.x && entry.getKey().y == arrive.y)
                res = entry.getKey();
        }

        return res;
    }

    private Point getRandomTile(Collection<Point> list, Point current) {
        ArrayList<Point> tiles = FieldUtils.getAdjacentCoords(list, current);
        return tiles.get((int) (Math.random() * tiles.size()));
    }

    @Override
    public Action behave(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        Action action = null;
        Point enemyCoord = getNearestEnemy(current, entities);
        if (enemyCoord != null) {
            Point safeTile = getSafestTile(current, enemyCoord, tiles);
            if (safeTile != null)
                action = new Move(current, safeTile);
            else
                action = new Nothing(current);

        } else {
            action = new Move(current, getRandomTile(tiles.keySet(), current),
                    3);
        }
        return action;
    }
}
