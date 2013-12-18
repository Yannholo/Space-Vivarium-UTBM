package spaceVivarium.utils;

import java.awt.Point;
import java.util.Map;
import java.util.Map.Entry;

import spaceVivarium.core.entities.Entity;

public class FieldUtils {

    /**
     * Give the position of the nearest entity of type typeEntity.
     * 
     * @param typeEntity
     *            the entity type to search.
     * @param tiles
     *            map of seen tiles
     * @param entities
     *            map of seen entities
     * @param current
     *            searching entity
     * @return the position of the nearest entity
     */
    public static Point getNearestEntity(
            Class<? extends Entity> typeEntity, Map<Point, Entity> entities,
            Point current) {
        Point res = null;
        double distance = 10000;
        double newDistance = 0;
        for (Entry<Point, Entity> entry : entities.entrySet()) {

            // l'entité existe sur la case.
            if (entry.getValue().getClass().equals((typeEntity))) {
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

}
