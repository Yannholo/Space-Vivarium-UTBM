package test.yannholo.core.entities;

import java.util.List;

import test.yannholo.core.actions.IAction;
import test.yannholo.core.actions.Move;
import test.yannholo.core.actions.Nothing;
import test.yannholo.core.maps.tiles.ATile;

public class TestEntity extends AEntity {

    public TestEntity(ATile depart) {
        super(depart);
        vision = 1;
    }

    public IAction update(List<ATile> vues) {
        IAction todo;
        if (vues.size() <= 0) {
            todo = new Nothing();
        } else {
            todo = new Move(this, vues.get((int) (Math.random() * vues.size())));
        }

        return todo;
    }

}
