package spaceVivarium;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.entities.TestEntity;
import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.xml.XmlReader;
import spaceVivarium.core.simulation.Simulation;
import spaceVivarium.ihm.Fenetre;
import spaceVivarium.ihm.Panneau;

public class VivariumSynchronized {

    public static void main(String[] args) {
        Simulation simulation = getSimulation();
        simulation.init();

        Fenetre fenetre = new Fenetre();

        Panneau pan = new Panneau(simulation);
        fenetre.setContentPane(pan);

        ExecutorService executor = Executors.newFixedThreadPool(4);

    }

    private static Simulation getSimulation() {

        /* test xml reader */

        XmlReader reader = new XmlReader();

        Board testmap = reader
                .xmlToBoard("src\\spaceVivarium\\core\\maps\\xml\\board.xml");

        // Board map = new Board(50, 50, Tile.class);

        java.util.Map<Class<? extends AEntity>, Integer> entityConf = new Hashtable<>();

        entityConf.put(TestEntity.class, 10);
        // entityConf.put(TestSmartEntity.class, 10);

        return new Simulation(testmap, entityConf);

    }

}
