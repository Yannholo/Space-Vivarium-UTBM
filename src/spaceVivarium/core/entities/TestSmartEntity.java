package spaceVivarium.core.entities;

import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.actions.Behavior;
import spaceVivarium.core.actions.Escape;
import spaceVivarium.core.actions.Action;
import spaceVivarium.core.maps.tiles.ATile;

public class TestSmartEntity extends Entity {

    /**
     * Cr�e l'entit�e de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     */
    public TestSmartEntity(ATile depart) {
        super(depart);
        comportements = new ArrayList<Behavior>();
        this.comportements.add(new Escape(this, TestEntity.class));
        vision = 4;
    }

    public Action update(List<ATile> vues) {
        Action todo = chooseAction(vues);
        return todo;
    }
}