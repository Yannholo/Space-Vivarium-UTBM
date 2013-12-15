package spaceVivarium.core.entities;

import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.maps.tiles.ATile;

/**
 * 
 * @author Yannholo
 */
public class TestEntity extends Entity {

    /**
     * Crée l'entitée de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     * 
     * 
     */
    public TestEntity(ATile depart) {
        super(depart);
        vision = 1;
    }

    public Action update(List<ATile> vues) {
        Action todo = null;
        do {
            if (vues.size() <= 0) {
                // si l'entité ne vois rien, elle ne fait rien
                todo = new Nothing(this);
            } else {
                // sinon elle bouge aléatoirement sur une Tile
                ATile destination = vues
                        .get((int) (Math.random() * vues.size()));
                if (destination.getEntity() == null) {
                    todo = new Move(this, destination);
                } else {
                    vues.remove(destination);
                }
            }
        } while (todo == null);

        return todo;
    }
}
