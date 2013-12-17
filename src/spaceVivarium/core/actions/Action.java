package spaceVivarium.core.actions;

import java.awt.Point;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.entities.Entity;

public abstract class Action {
    private int priority;
    private boolean isDone;
    private boolean willBeDone;
    private Action dependOn;

    public Action(int prio) {
        priority = prio;
        isDone = false;
        willBeDone = true;
        dependOn = null;
    }

    public final void doIt(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove) {
        if (!isDone && willBeDone && (dependOn == null || dependOn.willBeDone)) {
            isDone = true;
            if (dependOn != null && !dependOn.isDone) {
                dependOn.doIt(entities, entitiesToAdd, entitiesToRemove);
            }
            doItImpl(entities, entitiesToAdd, entitiesToRemove);

        }
    }

    public abstract void doItImpl(
            Map<Point, Entity> entities, Map<Point, Entity> entitiesToAdd,
            List<Point> entitiesToRemove);

    /**
     * @param action
     * @return l'action à supprimer pour regler le conflit sinon null
     */
    public abstract Action inConflict(Action action);

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return true if the action have been made
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @return false if the action will never be done
     */
    public boolean isWillBeDone() {
        return willBeDone;
    }

    public void setWillBeDone(boolean willBeDone) {
        this.willBeDone = willBeDone;
    }

    /**
     * Si l'action dont on dépend isMade == false on ne fait pas l'action
     * courante
     * 
     * @return l'action de l'aquelle dépend l'action courrante
     */
    public Action getDependOn() {
        return dependOn;
    }

    public void setDependOn(Action dependOn) {
        if (dependOn != this)
            this.dependOn = dependOn;
    }

}
