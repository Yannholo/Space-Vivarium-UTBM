package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Create;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.FieldUtils;
import spaceVivarium.utils.Vector2D;

/**
 * Reproduce behaviour, pousse l'entité à se reproduire avec les entités de type
 * typePrey .
 */
public class Reproduce extends Behaviour {

    // le type des entités avec lesquelles se reproduire
    private Class<? extends Entity> typeEntity;

    public Reproduce(Class<? extends Entity> typeE) {
        typeEntity = typeE;
    }

    private Point getNearestMate(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        return FieldUtils.getNearestEntity(typeEntity, entities, current);
    }

    private Point getBirthCoord(
            Point current, Map<Point, Entity> entities, Collection<Point> list) {
        ArrayList<Point> tiles = FieldUtils.getAdjacentCoords(list, current);
        return tiles.get((int) (Math.random() * tiles.size()));
    }

    /**
     * @param mate
     *            l'entité à suivre pour l'entité entity
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return la meilleur case pour suivre la proie
     */
    private Point getClosestTile(
            Point mate, Point current, Map<Point, ATile> tiles) {
        Point res = null;
        Vector2D pMate = new Vector2D(mate.x, mate.y);
        Vector2D pSelf = new Vector2D(current.x, current.y);
        Vector2D normalizedDir = new Vector2D(pMate.getX() - pSelf.getX(),
                pMate.getY() - pSelf.getY()).normalize();
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
        entities.get(current).setReproductionTimer(
                entities.get(current).getReproductionTimer() - 1);
        Action res = null;
        if (entities.size() > 1) {
            // Determine si l'entité peut se reproduire
            boolean reproduction = false;
            Point tmp = null;

            for (Entry<Point, Entity> typeE : entities.entrySet()) {
                if (typeE.getValue().getClass().equals(typeEntity)) {
                    // Verifie si une des entité de type typeEntity est
                    // adjacente
                    if (typeE.getKey().distance(current) <= 1)
                        reproduction = true;
                }
            }

            if (reproduction) {
                reproduction = false;
                // l'entité peut à nouveau se reproduire
                if (entities.get(current).getReproductionTimer() <= 0) {
                    return new Create(2, this.getBirthCoord(current, entities,
                            tiles.keySet()), current, typeEntity);
                }
            }

            return new Move(this.getNearestMate(tiles, entities, current),
                    current, 1);

        }

        return new Move(current, FieldUtils.getRandomTile(tiles.keySet(),
                current), 1);

    }
}
