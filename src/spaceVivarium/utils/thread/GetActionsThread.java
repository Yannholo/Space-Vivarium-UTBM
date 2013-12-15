package spaceVivarium.utils.thread;

import java.util.Observable;

import spaceVivarium.core.simulation.Simulation;

public class GetActionsThread extends Observable implements Runnable {

    private Simulation simulation;

    public GetActionsThread(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 20; i++) {
                System.out.println("action " + i);
                // notifyObservers(arg);
            }
        }

    }
}
