package spaceVivarium.utils.thread;

import java.util.List;
import java.util.concurrent.Callable;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.simulation.Simulation;

public class PrepareSimUpdate implements Callable<List<IAction>> {

    Simulation sim;

    public PrepareSimUpdate(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public List<IAction> call() throws Exception {
        List<IAction> actions;
        synchronized (sim) {
            actions = sim.prepareUpdate();
        }
        return actions;
    }
}
