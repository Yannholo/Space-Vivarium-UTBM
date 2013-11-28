package spaceVivarium;

import java.awt.Dimension;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.entities.AEntity;
import spaceVivarium.core.entities.TestEntity;
import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.tiles.Tile;
import spaceVivarium.core.simulation.Simulation;
import spaceVivarium.ihm.Fenetre;
import spaceVivarium.ihm.Panneau;
import spaceVivarium.utils.thread.PrepareSimUpdate;

public class Test {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {

        Simulation simulation = getSimulation();
        simulation.init();

        Fenetre fenetre = new Fenetre();

        Panneau pan = new Panneau(simulation);
        pan.setPreferredSize(new Dimension(2000, 2000));
        // JScrollPane scrollPane = new JScrollPane(pan);
        // scrollPane.setPreferredSize(new Dimension(1280, 800));
        // scrollPane.setAutoscrolls(true);
        fenetre.setContentPane(pan);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<IAction> actions;

        while (fenetre.isShowing()) {

            // On lance l'ia pour futures actions (thread)
            Future<List<IAction>> futureActionList = executor
                    .submit(new PrepareSimUpdate(simulation));
            // On dessine le field
            pan.repaint();

            // actions = simulation.prepareUpdate();
            // On attend la fin de l'ia
            actions = futureActionList.get();
            // On applique la simulation (non thread)
            synchronized (simulation) {
                simulation.applyUpdate(actions);
            }

            Thread.sleep(100); // TODO utiliser un delta

        }

    }

    private static Simulation getSimulation() {

        Board map = new Board(50, 50, Tile.class);

        java.util.Map<Class<? extends AEntity>, Integer> entityConf = new Hashtable<>();

        entityConf.put(TestEntity.class, 10);

        return new Simulation(map, entityConf);

    }

}
