package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;

public class Kill implements Action {
    private Entity selfEntity;
    private Entity killedEntity;
    private int priority;

    public Kill() {
        selfEntity = null;
        killedEntity = null;
        priority = 0;
    }

    public Kill(Entity self, Entity killed, int prio) {
        selfEntity = self;
        killedEntity = killed;
        priority = prio;
    }

    public Entity getSelfEntity() {
        return selfEntity;
    }

    public void setSelfEntity(Entity selfEntity) {
        this.selfEntity = selfEntity;
    }

    public Entity getKilledEntity() {
        return killedEntity;
    }

    public void setKilledEntity(Entity killedEntity) {
        this.killedEntity = killedEntity;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public Action inConflict(Action action) {
        return null;
    }

    @Override
    public void doIt() {
        killedEntity.getLaCase().setEntity(null);
        killedEntity.setAlive(false);
    }

}
