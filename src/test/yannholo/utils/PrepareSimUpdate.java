package test.yannholo.utils;

import java.util.List;
import java.util.concurrent.Callable;

import test.yannholo.core.actions.IAction;
import test.yannholo.core.simulation.Simulation;

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
