package spaceVivarium.ihm;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Create;
import spaceVivarium.core.actions.Kill;
import spaceVivarium.core.entities.Ant;
import spaceVivarium.core.entities.Cthuli;
import spaceVivarium.core.entities.Human;
import spaceVivarium.core.entities.Spider;
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
    private List<Action> listeA;

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
        spiderButton = new JRadioButton("araign�e");
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
                listeA = new ArrayList<Action>();
                Point pos = new Point(me.getX() / 10, me.getY() / 10);
                // JOptionPane.showMessageDialog(null, " x : " + me.getX() /
                // 10);
                if (me.isMetaDown())
                    listeA.add(new Kill(pos, 10));
                // sim.getField().deleteEntity(
                // new Point(me.getX() / 10, me.getY() / 10));
                else {
                    if (spiderButton.isSelected())
                        listeA.add(new Create(10, pos, Spider.class));
                    else if (humanButton.isSelected())
                        listeA.add(new Create(10, pos, Human.class));
                    else if (antButton.isSelected())
                        listeA.add(new Create(10, pos, Ant.class));
                    else if (ctulhiButton.isSelected())
                        listeA.add(new Create(10, pos, Cthuli.class));
                }
                sim.addActions(listeA);

            }

        });

        pauseButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                sim.setPause(!sim.isPaused());
            }
        });
    }
}
