package test.yannholo;

import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import test.yannholo.core.actions.IAction;
import test.yannholo.core.entities.AEntity;
import test.yannholo.core.entities.TestEntity;
import test.yannholo.core.maps.Map;
import test.yannholo.core.maps.tiles.Tile;
import test.yannholo.core.simulation.Simulation;
import test.yannholo.ihm.Fenetre;
import test.yannholo.ihm.Panneau;
import test.yannholo.utils.PrepareSimUpdate;

public class Test {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {

        Simulation simulation = getSimulation();
        simulation.init();

        Fenetre fenetre = new Fenetre();

        Panneau pan = new Panneau(simulation);
        fenetre.setContentPane(pan);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        while (fenetre.isShowing()) {

            // On lance l'ia pour futures actions (thread)
            Future<List<IAction>> futureActionList = executor
                    .submit(new PrepareSimUpdate(simulation));
            // On dessine le field (on attend la fin du dessin)
            pan.paintImmediately(pan.getBounds());
            // On attend la fin de l'ia
            List<IAction> actions = futureActionList.get();
            // On applique la simulation (non thread)
            simulation.applyUpdate(actions);

            Thread.sleep(100); // TODO utiliser un delta

        }

    }

    private static Simulation getSimulation() {

        Map map = new Map(50, 50, Tile.class);

        java.util.Map<Class<? extends AEntity>, Integer> entityConf = new Hashtable<>();

        entityConf.put(TestEntity.class, 10);

        return new Simulation(map, entityConf);

    }

}
