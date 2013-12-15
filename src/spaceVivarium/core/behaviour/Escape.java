package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.Vector2D;

/**
 * Escape behavior, indique à l'entité entity de fuire toutes les entités de
 * type typeEnemy .
 * 
 */
public class Escape extends Behaviour {
    // l'entité qui s'échappe
    private Entity entity;
    // le type des entités à éviter
    private Class<? extends Entity> typeEnemy;

    public Escape(Entity self, Class<? extends Entity> typeE) {
        entity = self;
        typeEnemy = typeE;
    }

    /**
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return l'ennemi AEntity le plus proche de l'entité entity
     */
    private Entity getNearestEnemy(List<ATile> list) {
        Entity res = null;
        Entity tileEntity = null;
        double distance = 10000;
        double newDistance = 0;
        for (ATile tile : list) {
            tileEntity = tile.getEntity();
            if (tileEntity != null) {
                // l'entité existe sur la case.
                if (tileEntity.getClass().equals((typeEnemy))) {
                    // l'entité est un ennemi.
                    newDistance = entity.getDistance(tileEntity);
                    if (newDistance < distance) {
                        // l'entité est la plus proche actuellement
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
     *            l'ennemi à fuir par l'entité entity
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return la case la plus sûre pour éviter enemy
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

    private ATile getRandomTile(List<ATile> list) {
        ArrayList<ATile> tiles = this.getAdjacentCoords(list);
        return tiles.get((int) (Math.random() * tiles.size()));
    }

    public ArrayList<ATile> getAdjacentCoords(List<ATile> list) {
        ArrayList<ATile> adj = new ArrayList<ATile>();
        Vector2D self = new Vector2D(entity.getLaCase().getX(), entity
                .getLaCase().getY());
        for (ATile tile : list) {
            Vector2D dest = new Vector2D(tile.getX(), tile.getY());
            if (self.getDistance(dest) <= 1)
                adj.add(tile);
        }
        adj.add(entity.getLaCase());
        return adj;
    }

    @Override
    public List<Action> behave(List<ATile> list) {
        List<Action> listAct = new ArrayList<Action>();
        Entity enemy = getNearestEnemy(list);
        if (enemy != null) {
            ATile safeTile = getSafestTile(enemy, list);
            if (safeTile != null)
                listAct.add(new Move(entity, safeTile, 3));
            else
                listAct.add(new Nothing(entity));

        } else
            listAct.add(new Move(entity, getRandomTile(list), 3));
        return listAct;
    }
}
