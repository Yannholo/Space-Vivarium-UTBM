package spaceVivarium.ihm;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import spaceVivarium.core.simulation.Simulation;

public class InteractionPanel extends JPanel {

    private SimulationPanel simulationP;
    private Simulation sim;

    private ButtonGroup radioGroupe;
    private JRadioButton spiderButton;
    private JRadioButton antButton;
    private JRadioButton ctulhiButton;
    private JRadioButton humanButton;
    private JButton pauseButton;
    private String buttonText;

    public InteractionPanel(int width, int height, SimulationPanel simuP,
            Simulation simu) {
        this.simulationP = simuP;
        this.sim = simu;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        radioGroupe = new ButtonGroup();
        buttonText = new String("Pause");
        pauseButton = new JButton(buttonText);
        humanButton = new JRadioButton("humain");
        spiderButton = new JRadioButton("araignée");
        antButton = new JRadioButton("fourmis");
        ctulhiButton = new JRadioButton("cthulhi");
        radioGroupe.add(humanButton);
        radioGroupe.add(spiderButton);
        radioGroupe.add(antButton);
        radioGroupe.add(ctulhiButton);
        this.add(pauseButton);
        this.add(humanButton);
        this.add(spiderButton);
        this.add(antButton);
        this.add(ctulhiButton);
        addActionListener();
    }

    private void addActionListener() {
        simulationP.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JOptionPane.showMessageDialog(null, " x : " + me.getX() / 10);
            }

        });
    }
}
