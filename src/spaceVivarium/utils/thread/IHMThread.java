package spaceVivarium.utils.thread;

import javax.swing.JFrame;

import spaceVivarium.core.simulation.Simulation;
import spaceVivarium.ihm.SimulationPanel;

public class IHMThread implements Runnable {

    private Simulation sim;

    private SimulationPanel simulationPanel;

    public IHMThread(Simulation sim) {
        this.sim = sim;
        simulationPanel = new SimulationPanel(sim);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Simulation");
        // Nous demandons maintenant à notre objet de se positionner au centre
        frame.setLocationRelativeTo(null);
        // Définit sa taille
        frame.setSize(550, 580);
        // Termine le processus lorsqu'on clique sur la croix rouge
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Instanciation d'un objet JPanel
        frame.setContentPane(simulationPanel);
        // Et enfin, la rendre visible
        frame.setVisible(true);
    }

    public SimulationPanel getSimulationPanel() {
        return simulationPanel;
    }

}
