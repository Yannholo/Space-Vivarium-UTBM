package spaceVivarium.core.entities;

import java.util.List;

import spaceVivarium.core.actions.ABehavior;
import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.maps.tiles.ATile;

public class TestSmartEntity extends AEntity {

    /**
     * Cr�e l'entit�e de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     * 
     * 
     * 
     */
    public TestSmartEntity(ATile depart, List<ABehavior> comps) {
        super(depart, comps);
        vision = 3;
    }

    public ABehavior chooseBehavior(List<ATile> vues) {
        // TODO : d�terminer priorit� des Behaviors
        return null;

    }

    public IAction update(List<ATile> vues) {
        ABehavior comp = chooseBehavior(vues);
        IAction todo = comp.behave(vues);
        return todo;
    }
}