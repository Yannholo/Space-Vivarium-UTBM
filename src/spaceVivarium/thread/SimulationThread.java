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
        long startTime = System.currentTimeMillis(), estimatedTime, timeToWait = 100;

        while (true) {

            actions = sim.prepareUpdate();

            estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println("### time = " + estimatedTime + " ms");
            try {
                timeToWait = 100 - estimatedTime;
                Thread.sleep(timeToWait < 0 ? 0 : timeToWait);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            startTime = System.currentTimeMillis();
            synchronized (sim) {
                sim.applyUpdate(actions);
                simPanel.repaint();
            }

        }
    }
}
