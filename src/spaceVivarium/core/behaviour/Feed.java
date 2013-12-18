package spaceVivarium.core.behaviour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Kill;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.utils.FieldUtils;

/**
 * Feed behavior, ajoute à une entité le besoin de se nourrir d'autres entités
 * 
 */
public class Feed extends Behaviour {

    // le type des entités à manger
    private ArrayList<Class<? extends Entity>> typeEnemy;
    // La jauge de faim
    private int hunger;

    public Feed(ArrayList<Class<? extends Entity>> typeEnemy, int baseHunger) {
        this.hunger = baseHunger;
        this.typeEnemy = typeEnemy;
    }

    @Override
    public Action behave(
            Map<Point, ATile> tiles, Map<Point, Entity> entities, Point current) {
        Point res = null;
        Point tmp = null;
        for (Class<? extends Entity> typeE : typeEnemy) {
            tmp = FieldUtils.getNearestEntity(typeE, entities, current);
            if (tmp != null)
                if (tmp.distance(current) <= 1.5)
                    res = tmp;
        }
        if (res != null) {
            // TODO transférer this.hunger dans Entity ?
            hunger++;
            return new Kill(res, 4);

        }
        hunger--;

        if (hunger <= 0)
            return new Kill(current, 4);
        return new Nothing(current);
    }
}
