package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;

public class EatEntity extends Action {
    private Entity selfEntity;
    private Entity eatenEntity;

    public EatEntity(int prio, Entity self, Entity eaten) {
        super(prio);
        selfEntity = self;
        eatenEntity = eaten;
    }

    @Override
    public void doItImpl() {
        eatenEntity.getLaCase().setEntity(null);
        eatenEntity.setAlive(false);
        // TODO augmenter niveau de faim de selfEntity.
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

}
