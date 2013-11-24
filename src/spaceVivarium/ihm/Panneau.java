package spaceVivarium.ihm;

import java.awt.Graphics;

import javax.swing.JPanel;

import spaceVivarium.core.simulation.Simulation;

public class Panneau extends JPanel {

    Simulation sim;

    int i = 0;

    public Panneau(Simulation sim) {
        super();
        this.sim = sim;

    }

    public void paintComponent(Graphics g) {
        synchronized (sim) {
            i++;
            System.out.println("paintComponent " + i);
            sim.print(g);
            System.out.println("paintComponend " + i);
        }
    }
}