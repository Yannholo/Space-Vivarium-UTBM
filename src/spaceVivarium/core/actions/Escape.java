package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.Vector2D;

/**
 * Escape behavior, indique � l'entit� entity de fuire toutes les entit�s de
 * type typeEnemy .
 * 
 */
public class Escape extends Behavior {
    // l'entit� qui s'�chappe
    private Entity entity;
    // le type des entit�s � �viter
    private Class<? extends Entity> typeEnemy;

    public Escape(Entity self, Class<? extends Entity> typeE) {
        entity = self;
        typeEnemy = typeE;
    }

    /**
     * @param list
     *            la liste des cases vues par l'entit� entity
     * @return l'ennemi AEntity le plus proche de l'entit� entity
     */
    private Entity getNearestEnemy(List<ATile> list) {
        Entity res = null;
        Entity tileEntity = null;
        double distance = 10000;
        double newDistance = 0;
        for (ATile tile : list) {
            tileEntity = tile.getEntity();
            if (tileEntity != null) {
                // l'entit� existe sur la case.
                if (tileEntity.getClass().equals((typeEnemy))) {
                    // l'entit� est un ennemi.
                    newDistance = entity.getDistance(tileEntity);
                    if (newDistance < distance) {
                        // l'entit� est la plus proche actuellement
                        res = tileEntity;
                        distance = newDistance;
                    }
                }
            }
        }
        return res;
    }

    /**
     * @param enemy
     *            l'ennemi � fuir par l'entit� entity
     * @param list
     *            la liste des cases vues par l'entit� entity
     * @return la case la plus s�re pour �viter enemy
     */
    private ATile getSafestTile(Entity enemy, List<ATile> list) {
        ATile res = null;
        Vector2D pEnemy = new Vector2D(enemy.getLaCase().getX(), enemy
                .getLaCase().getY());
        Vector2D pSelf = new Vector2D(entity.getLaCase().getX(), entity
                .getLaCase().getY());
        Vector2D normalizedDir = new Vector2D(pSelf.getX() - pEnemy.getX(),
                pSelf.getY() - pEnemy.getY()).normalize();
        Point arrive = pSelf.translate(normalizedDir).getIntPoint();
        for (ATile tile : list) {
            if (tile.getX() == arrive.getX() && tile.getY() == arrive.getY())
                res = tile;
        }

        return res;
    }

    private ATile getRandomTile(List<ATile> list) throws Exception {
        ATile res = null;
        ArrayList<Point> coords = entity.getLaCase().getAdjacentCoords();
        Point dest = coords.get((int) (Math.random() * coords.size()));
        for (ATile tile : list) {
            if (tile.getCoord().equals(dest))
                res = tile;
        }
        return res;
    }

    @Override
    public List<Action> behave(List<ATile> list) {
        List<Action> listAct = new ArrayList<Action>();
        Entity enemy = getNearestEnemy(list);
        if (enemy != null) {
            ATile safeTile = getSafestTile(enemy, list);
            if (safeTile != null)
                listAct.add(new Move(entity, safeTile, 3));
            else {
                /*
                 * try { listAct.add(new Move(entity, getRandomTile(list), 3));
                 * } catch (Exception e) { listAct.add(new Nothing()); }
                 */
                listAct.add(new Nothing());

            }

            // listAct.add(new Move(entity, list.get((int) (Math.random() * list
            // .size()))));
        } else
            listAct.add(new Nothing());
        return listAct;
    }
}
