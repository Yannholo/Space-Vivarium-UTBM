package spaceVivarium.utils.thread;

import spaceVivarium.core.simulation.Simulation;

public class ApplyActionsThread implements Runnable {

    private Simulation simulation;

    public ApplyActionsThread(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        while (true) {

        }
    }

}
