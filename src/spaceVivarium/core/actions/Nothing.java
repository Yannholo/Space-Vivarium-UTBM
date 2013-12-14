package spaceVivarium.core.actions;

public class Nothing implements Action {

    private int priority = 0;

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

}
