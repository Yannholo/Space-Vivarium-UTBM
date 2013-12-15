package spaceVivarium.core.entities;

import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.actions.ABehavior;
import spaceVivarium.core.actions.Escape;
import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.maps.tiles.ATile;

public class TestSmartEntity extends AEntity {

    /**
     * Crée l'entitée de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     */
    public TestSmartEntity(ATile depart) {
        super(depart);
        comportements = new ArrayList<ABehavior>();
        this.comportements.add(new Escape(this, TestEntity.class));
        vision = 4;
    }

    public IAction update(List<ATile> vues) {
        IAction todo = chooseAction(vues);
        return todo;
    }
}