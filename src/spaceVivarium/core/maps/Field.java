package spaceVivarium.core.maps;

import java.awt.Graphics2D;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;

public class Field {

    private Board map;
    private java.util.Map<Point, ATile> field;
    private List<Entity> entities;

    public Field(Board map,
            java.util.Map<Class<? extends Entity>, Integer> entityConf) {
        this.map = map;
        entities = new LinkedList<>();
        clearField();
        placeEntities(entityConf);
    }

    /**
     * remet le field � 0
     */
    private void clearField() {
        if (field == null) {
            field = new HashMap<Point, ATile>(map.getSizeX() * map.getSizeY());
        }
        Point p;
        Constructor<? extends ATile> constructor;
        for (int x = 0; x < map.getSizeX(); x++) {
            for (int y = 0; y < map.getSizeY(); y++) {
                p = new Point(x, y);
                try {
                    constructor = map.getTileClass(x, y).getConstructor(
                            Point.class);
                    field.put(p, (ATile) constructor.newInstance(p));

                } catch (NoSuchMethodException | SecurityException
                        | InstantiationException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private void placeEntities(
            java.util.Map<Class<? extends Entity>, Integer> entityConf) {
        for (Entry<Class<? extends Entity>, Integer> entry : entityConf
                .entrySet()) {
            placeEntities(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Place des instances d'entit�e sur le field
     * 
     * @param nb
     *            le nombre d'entit�e voulues
     * @param type
     *            le type d'entit�e voulues
     */
    private void placeEntities(int nb, Class<? extends Entity> type) {

        Constructor<? extends Entity> constructor;
        try {
            constructor = type.getConstructor(ATile.class);
            for (int i = 0; i < nb; i++) {
                ATile tile = getTile(type);
                Entity entity = constructor.newInstance(tile);
                entities.add(entity);
                tile.setEntity(entity);
            }
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Renvois une tile selon le type d'entit�e
     * 
     * @param type
     *            le type de l'entit�e voulue
     */
    private ATile getTile(Class<? extends Entity> type) {
        // Pour l'instant simple random sur la map
        // TODO gerer au moins les conflits
        int x = (int) (Math.random() * map.getSizeX());
        int y = (int) (Math.random() * map.getSizeY());
        return field.get(new Point(x, y));
    }

    public List<? extends Entity> getEntities() {
        return entities;
    }

    public List<ATile> getView(Entity entity) {
        ATile depart = entity.getLaCase();
        // on choppe toutes les cases vues par la bestiole
        int nbCasesVues = entity.getVision() * 4
                + (int) Math.pow(4, entity.getVision());
        List<ATile> vues = new ArrayList<>(nbCasesVues);
        // @formatter:off
        for (int x = depart.getX() - entity.getVision(); 
                 x <= depart.getX() + entity.getVision(); 
                 x++) {
            for (int y = depart.getY() - entity.getVision(); 
                     y <= depart.getY() + entity.getVision(); 
                     y++) {
        // @formatter:on
                if (x >= 0 && x < map.getSizeX() && y >= 0
                        && y < map.getSizeY()) {
                    vues.add(field.get(new Point(x, y)));
                }
            }
        }
        return vues;
    }

    public void print(Graphics2D g) {
        for (ATile tile : field.values()) {
            tile.print(g);
        }
    }

}
