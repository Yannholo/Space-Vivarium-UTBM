package spaceVivarium.core.actions;

public interface IAction {
    public void doIt();

    public int getPriority();

    public void setPriority(int prio);

}
