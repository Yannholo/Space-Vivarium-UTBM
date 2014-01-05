package spaceVivarium.core.maps;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.core.maps.tiles.BlockingTile;

public class Field {

    private Board map;
    private Map<Point, ATile> tiles;
    private Map<Point, Entity> entities;

    public Field(Board map, Map<Class<? extends Entity>, Integer> entityConf) {
        this.map = map;
        entities = new HashMap<>();
        clearField();
        placeEntities(entityConf);
    }

    /**
     * remet le field à 0
     */
    private void clearField() {
        if (tiles == null) {
            tiles = new HashMap<Point, ATile>(map.getSizeX() * map.getSizeY());
        }
        Point p;

        for (int x = 0; x < map.getSizeX(); x++) {
            for (int y = 0; y < map.getSizeY(); y++) {
                p = new Point(x, y);
                try {

                    tiles.put(p, map.getTileClass(x, y).newInstance());

                } catch (SecurityException | InstantiationException
                        | IllegalAccessException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void placeEntities(Map<Class<? extends Entity>, Integer> entityConf) {
        for (Entry<Class<? extends Entity>, Integer> entry : entityConf
                .entrySet()) {
            placeEntities(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Place des instances d'entitée sur le field
     * 
     * @param nb
     *            le nombre d'entitée voulues
     * @param type
     *            le type d'entitée voulues
     */
    private void placeEntities(int nb, Class<? extends Entity> type) {
        try {

            for (int i = 0; i < nb; i++) {

                Point point = getTile(type);
                Entity entity;
                entity = type.newInstance();
                entities.put(point, entity);

            }
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException | SecurityException e) {

            e.printStackTrace();
        }

    }

    /**
     * Renvois une tile selon le type d'entitée
     * 
     * @param type
     *            le type de l'entitée voulue
     */
    private Point getTile(Class<? extends Entity> type) {
        // Pour l'instant simple random sur la map
        // TODO gerer au moins les conflits
        int x = (int) (Math.random() * map.getSizeX());
        int y = (int) (Math.random() * map.getSizeY());
        return new Point(x, y);
    }

    private Map<Point, ATile> getTilesViewed(Entity entity) {
        Point depart = getEntityPoint(entity);
        // on choppe toutes les cases vues par la bestiole
        int nbCasesVues = entity.getVision() * 4
                + (int) Math.pow(4, entity.getVision());
        Map<Point, ATile> vues = new HashMap<>(nbCasesVues);
        // @formatter:off
        for (int x = depart.x - entity.getVision(); 
                 x <= depart.getX() + entity.getVision(); 
                 x++) {
            for (int y = depart.y - entity.getVision(); 
                     y <= depart.getY() + entity.getVision(); 
                     y++) {
        // @formatter:on
                if (x >= 0 && x < map.getSizeX() && y >= 0
                        && y < map.getSizeY()) {
                    ATile tile = tiles.get(new Point(x, y));
                    if (!(tile instanceof BlockingTile))
                        vues.put(new Point(x, y), tile);
                }
            }
        }
        return vues;
    }

    private Map<Point, Entity> getEntitiesViewed(Entity entity) {
        Point pointEntity = getEntityPoint(entity);
        Point pointEntity2 = null;
        Map<Point, Entity> vues = new HashMap<>();
        for (Entity entity2 : entities.values()) {
            pointEntity2 = getEntityPoint(entity2);
            double distance = pointEntity.distance(pointEntity2);
            // if (distance <= 1.5) {
            if (distance <= entity.getVision()) {
                vues.put(pointEntity2, entity2);
            }
            // System.out.println("Distance : " + distance);
        }
        return vues;
    }

    /**
     * @param entity
     * @return Les coordonée de l'entity
     */
    public Point getEntityPoint(Entity entity) {
        Point point = null;
        for (Entry<Point, Entity> entry : entities.entrySet()) {
            if (entity == entry.getValue()) {
                point = entry.getKey();
                break;
            }
        }
        return point;
    }

    public void print(Graphics2D g) {
        for (Entry<Point, ATile> entry : tiles.entrySet()) {
            entry.getValue().print(g, entry.getKey());
        }
        for (Entry<Point, Entity> entry : entities.entrySet()) {
            entry.getValue().print(g, entry.getKey());
        }
    }

    public List<Action> askActions() {
        List<Action> actions = new ArrayList<>(entities.size());
        for (Entry<Point, Entity> entry : entities.entrySet()) {
            actions.add(entry.getValue().update(
                    getTilesViewed(entry.getValue()),
                    getEntitiesViewed(entry.getValue()), entry.getKey()));
        }
        return actions;
    }

    public List<Action> applyUpdates(List<Action> actions) {
        List<Action> environementalActions = new LinkedList<>();
        List<Point> entitiesToRemove = new LinkedList<>();
        Map<Point, Entity> entitiesToAdd = new HashMap<>();
        for (Action action : actions) {
            // System.out.println(action);
            action.doIt(entities, entitiesToAdd, entitiesToRemove);
        }

        for (Point key : entitiesToRemove)
            entities.remove(key);

        for (Entry<Point, Entity> entry : entitiesToAdd.entrySet()) {
            entities.put(entry.getKey(), entry.getValue());
            Action environmentalAction = tiles.get(entry.getKey()).affect(
                    entry.getValue(), entry.getKey(), map);
            if (environmentalAction != null) {
                environementalActions.add(environmentalAction);
            }
        }
        return environementalActions;
    }

}
