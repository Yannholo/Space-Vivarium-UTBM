package spaceVivarium.thread;

import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.simulation.Simulation;
import spaceVivarium.ihm.SimulationPanel;

public class SimulationThread implements Runnable {

    Simulation sim;
    SimulationPanel simPanel;

    public SimulationThread(Simulation sim, SimulationPanel simPanel) {
        this.sim = sim;
        this.simPanel = simPanel;
    }

    @Override
    public void run() {
        List<Action> actions;

        while (true) {

            actions = sim.prepareUpdate();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            synchronized (sim) {
                sim.applyUpdate(actions);
                simPanel.repaint();
            }

        }
    }
}
