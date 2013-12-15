package spaceVivarium.utils.thread;

import java.util.Observable;
import java.util.Observer;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.simulation.Simulation;

public class ActionsConflictResolverThread implements Runnable, Observer {

    private Simulation simulation;

    public ActionsConflictResolverThread(Simulation simulation) {
        this.simulation = simulation;
    }

    public void run() {
        while (true) {

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof IAction) {

        }
    }
}