package spaceVivarium.core.entities;

import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Behavior;
import spaceVivarium.core.actions.Follow;
import spaceVivarium.core.maps.tiles.ATile;

public class TestSmartEntity extends Entity {

    /**
     * Crée l'entitée de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     */
    public TestSmartEntity(ATile depart) {
        super(depart);
        this.comportements = new ArrayList<Behavior>();
        this.comportements.add(new Follow(this, TestEntity.class));
        vision = 4;
    }

    public Action update(List<ATile> vues) {
        Action todo = chooseAction(vues);
        return todo;
    }
}