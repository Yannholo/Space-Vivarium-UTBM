package spaceVivarium.utils.thread;

import java.util.List;
import java.util.concurrent.Callable;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.simulation.Simulation;

public class PrepareSimUpdate implements Callable<List<Action>> {

    Simulation sim;

    public PrepareSimUpdate(Simulation sim) {
        this.sim = sim;
    }

    @Override
    public List<Action> call() throws Exception {
        List<Action> actions;
        synchronized (sim) {
            actions = sim.prepareUpdate();
        }
        return actions;
    }
}
