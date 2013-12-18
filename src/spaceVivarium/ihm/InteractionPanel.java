package spaceVivarium.ihm;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InteractionPanel extends JPanel {

    private SimulationPanel simulation;

    private JButton spiderButton;
    private JButton antButton;
    private JButton ctilhiButton;

    public InteractionPanel(int width, int height, SimulationPanel simu) {
        this.simulation = simu;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JButton spiderButton = new JButton("araignée");
        JButton antButton = new JButton("fourmis");
        JButton ctilhiButton = new JButton("cthilhi");
        this.add(spiderButton);
        this.add(antButton);
        this.add(ctilhiButton);
    }

    private void addActionListener(JButton button) {
        // button.addActionListener(new Action)
    }
}
