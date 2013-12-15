package spaceVivarium.core.actions;

import spaceVivarium.core.entities.Entity;

public class Kill extends Action {
    private Entity selfEntity;
    private Entity killedEntity;

    public Kill(Entity self, Entity killed, int prio) {
        super(prio);
        selfEntity = self;
        killedEntity = killed;
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
    public Action inConflict(Action action) {
        return null;
    }

    @Override
    public void doItImpl() {
        killedEntity.getLaCase().setEntity(null);
        killedEntity.setAlive(false);
    }

}
