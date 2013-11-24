package spaceVivarium.core.entities;

import java.util.List;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.maps.tiles.ATile;

/**
 * 
 * @author Yannholo
 */
public class TestEntity extends AEntity {

    /**
     * Cr�e l'entit�e de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     */
    public TestEntity(ATile depart) {
        super(depart);
        vision = 1;
    }

    public IAction update(List<ATile> vues) {
        IAction todo;
        if (vues.size() <= 0) {
            // si l'entit� ne vois rien, elle ne fait rien
            todo = new Nothing();
        } else {
            // TODO sinon elle bouge al�atoirement sur une Tile
            todo = new Move(this, vues.get((int) (Math.random() * vues.size())));
        }

        return todo;
    }

}
