package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;

public class Nothing extends Action {

    private Entity entity;

    public Nothing(Entity entity) {
        super(0);
        this.entity = entity;
    }

    @Override
    public void doItImpl() {
        // nothing
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

    public Entity getEntity() {
        return entity;
    }

}
