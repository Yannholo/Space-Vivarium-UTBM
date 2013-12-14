package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.Vector2D;

/**
 * Follow behavior, indique à l'entité entity de suivre toutes les entités de
 * type typeEnemy .
 * 
 */
public class Follow extends Behavior {
    // l'entité qui suit
    private Entity entity;
    // le type des entités à suivre
    private Class<? extends Entity> typePrey;

    public Follow(Entity self, Class<? extends Entity> typeP) {
        entity = self;
        typePrey = typeP;
    }

    /**
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return la proie AEntity la plus proche de l'entité entity
     */
    private Entity getNearestPrey(List<ATile> list) {
        Entity res = null;
        Entity tileEntity = null;
        double distance = 10000;
        double newDistance = 0;
        for (ATile tile : list) {
            tileEntity = tile.getEntity();
            if (tileEntity != null) {
                // l'entité existe sur la case.
                if (tileEntity.getClass().equals((typePrey))) {
                    // l'entité est une proie.
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

    /**
     * @param proie
     *            la proie à suivre pour l'entité entity
     * @param list
     *            la liste des cases vues par l'entité entity
     * @return la case la plus sûre pour éviter enemy
     */
    private ATile getClosestTile(Entity proie, List<ATile> list) {
        ATile res = null;
        Vector2D pPrey = new Vector2D(proie.getLaCase().getX(), proie
                .getLaCase().getY());
        Vector2D pSelf = new Vector2D(entity.getLaCase().getX(), entity
                .getLaCase().getY());
        Vector2D normalizedDir = new Vector2D(pPrey.getX() - pSelf.getX(),
                pPrey.getY() - pSelf.getY()).normalize();
        Point arrive = pSelf.translate(normalizedDir).getIntPoint();
        for (ATile tile : list) {
            if (tile.getX() == arrive.getX() && tile.getY() == arrive.getY())
                res = tile;
        }

        return res;
    }

    @Override
    public List<Action> behave(List<ATile> list) {
        List<Action> listAct = new ArrayList<Action>();
        Entity proie = getNearestPrey(list);
        if (proie != null) {
            ATile safeTile = getClosestTile(proie, list);
            if (safeTile != null)
                listAct.add(new Move(entity, safeTile, 3));
            else
                listAct.add(new Nothing());

        } else
            listAct.add(new Move(entity, getRandomTile(list), 3));
        return listAct;
    }
}
