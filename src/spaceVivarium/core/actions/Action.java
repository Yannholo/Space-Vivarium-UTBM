package spaceVivarium.core.actions;

public abstract class Action {
    private int priority;
    private boolean isMade;
    private Action dependOn;

    public Action(int prio) {
        priority = prio;
        isMade = true;
        dependOn = null;
    }

    public final void doIt() {
        if (isMade && (dependOn == null || dependOn.isMade)) {
            doItImpl();
        }
    }

    public abstract void doItImpl();

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
     * @return true si l'action va être effectuee
     */
    public boolean isMade() {
        return isMade;
    }

    /**
     * @param isMade
     *            false pour que l'action ne soit pas effectuee
     */
    public void setIsMade(boolean isMade) {
        this.isMade = isMade;
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
        this.dependOn = dependOn;
    }

}
