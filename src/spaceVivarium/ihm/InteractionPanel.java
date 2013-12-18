package spaceVivarium.ihm;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class InteractionPanel extends JPanel {

    private SimulationPanel simulation;

    private ButtonGroup radioGroupe;
    private JRadioButton spiderButton;
    private JRadioButton antButton;
    private JRadioButton ctilhiButton;

    public InteractionPanel(int width, int height, SimulationPanel simu) {
        this.simulation = simu;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        radioGroupe = new ButtonGroup();
        spiderButton = new JRadioButton("araignée");
        antButton = new JRadioButton("fourmis");
        ctilhiButton = new JRadioButton("cthilhi");
        radioGroupe.add(spiderButton);
        radioGroupe.add(antButton);
        radioGroupe.add(ctilhiButton);
        this.add(spiderButton);
        this.add(antButton);
        this.add(ctilhiButton);
        addActionListener();
    }

    private void addActionListener() {
        simulation.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                JOptionPane.showMessageDialog(null, me.getX());
            }

        });
    }
}
