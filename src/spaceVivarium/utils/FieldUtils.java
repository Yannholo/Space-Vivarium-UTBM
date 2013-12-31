package spaceVivarium.utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
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

    public static ArrayList<Point> getAdjacentCoords(
            Collection<Point> list, Point current) {
        ArrayList<Point> adj = new ArrayList<Point>();
        Point self = new Point(current.x, current.y);
        for (Point point : list) {
            Point dest = new Point(point.x, point.y);
            if (self.distance(dest) <= 1.5)
                adj.add(point);
        }
        adj.add(current);
        return adj;
    }

    public static ArrayList<Point> getFreeAdjacentCoords(
            Point current, Map<Point, Entity> entities, Collection<Point> list) {
        ArrayList<Point> freeAdj = new ArrayList<Point>();
        ArrayList<Point> adj = getAdjacentCoords(entities.keySet(), current);
        for (Point coord : adj) {
            if (!entities.containsKey(coord))
                freeAdj.add(coord);
        }
        return freeAdj;
    }

    public static Point getRandomTile(Collection<Point> list, Point current) {
        ArrayList<Point> tiles = FieldUtils.getAdjacentCoords(list, current);
        return tiles.get((int) (Math.random() * tiles.size()));
    }
}
