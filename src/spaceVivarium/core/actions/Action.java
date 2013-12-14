package spaceVivarium.core.actions;

public interface Action {
    public void doIt();

    public int getPriority();

    public void setPriority(int prio);

}
