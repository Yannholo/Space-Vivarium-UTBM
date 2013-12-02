package spaceVivarium.core.actions;

import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.maps.tiles.ATile;

public class Escape extends ABehavior {
    // l'entité qui s'échappe
    private AEntity entity;
    // le type de l'entité à éviter
    private Class<? extends AEntity> typeEnemy;

    public Escape(AEntity self, Class<? extends AEntity> typeE) {
        entity = self;
        typeEnemy = typeE;
    }

    private AEntity getNearestEnemy(List<ATile> list) {
        AEntity res = null;
        AEntity tileEntity = null;
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

    private ATile getSafestTile(AEntity enemy, List<ATile> list) {
        return null;
    }

    @Override
    public List<IAction> behave(List<ATile> list) {
        List<IAction> listAct = new ArrayList<IAction>();
        AEntity enemy = getNearestEnemy(list);
        if (enemy != null) {
            // listAct.add(new Move(entity, getSafestTile(enemy), 3));
            listAct.add(new Move(entity, list.get((int) (Math.random() * list
                    .size()))));
        } else
            listAct.add(new Nothing());
        return listAct;
    }
}
