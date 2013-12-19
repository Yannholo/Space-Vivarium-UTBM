package spaceVivarium.utils.thread;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import spaceVivarium.core.simulation.Simulation;
import spaceVivarium.ihm.InteractionPanel;
import spaceVivarium.ihm.SimulationPanel;

public class IHMThread implements Runnable {

    private Simulation sim;

    private SimulationPanel simulationPanel;
    private InteractionPanel interactionPanel;

    public IHMThread(Simulation sim) {
        this.sim = sim;
        simulationPanel = new SimulationPanel(sim);
        interactionPanel = new InteractionPanel(550, 30, simulationPanel, sim);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Simulation Space Vivarium");
        // Nous demandons maintenant à notre objet de se positionner au centre
        frame.setLocationRelativeTo(null);
        // Définit sa taille
        // frame.setSize(550, 580);
        // Termine le processus lorsqu'on clique sur la croix rouge
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setPreferredSize(new Dimension(550, 600));
        frame.getContentPane().setLayout(
                new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(simulationPanel);
        frame.getContentPane().add(interactionPanel);
        // frame.add(globalPanel);
        frame.pack();

        // Instanciation d'un objet JPanel
        // frame.setContentPane(simulationPanel);
        // Et enfin, la rendre visible
        frame.setVisible(true);
    }

    public SimulationPanel getSimulationPanel() {
        return simulationPanel;
    }

}
