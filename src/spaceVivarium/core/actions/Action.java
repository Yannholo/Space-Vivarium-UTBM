package spaceVivarium.core.actions;

public interface Action {
    public void doIt();

    /**
     * @param action
     * @return l'action à supprimer pour regler le conflit sinon null
     */
    public Action inConflict(Action action);

    public int getPriority();

    public void setPriority(int prio);

}
