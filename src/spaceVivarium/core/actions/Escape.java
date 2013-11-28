package spaceVivarium.core.actions;

import java.util.List;

import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.maps.tiles.ATile;

public class Escape extends ABehavior {
    // l'entit� qui s'�chappe
    private AEntity entity;
    // le type de l'entit� � �viter
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

    private ATile getSafestTile(AEntity enemy) {
        // TODO : determiner la case sur laquelle se d�placer pour �chapper �
        // l'ennemi le plus proche
        return null;
    }

    @Override
    public IAction behave(List<ATile> list) {
        AEntity enemy = getNearestEnemy(list);
        if (enemy != null)
            return new Move(entity, getSafestTile(enemy));
        else
            return new Nothing();
    }

}
