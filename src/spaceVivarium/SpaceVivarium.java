package spaceVivarium;

import javax.swing.SwingUtilities;

import spaceVivarium.ihm.FenetreConf;

public class SpaceVivarium {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new FenetreConf();

            }
        });

    }
}
