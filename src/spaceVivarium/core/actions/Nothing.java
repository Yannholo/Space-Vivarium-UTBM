package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;

public class Nothing implements Action {

    private int priority = 0;

    private Entity entity;

    public Nothing(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void doIt() {
        // nothing
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int prio) {
        this.priority = prio;
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

    public Entity getEntity() {
        return entity;
    }

}
