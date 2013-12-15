package spaceVivarium;

import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingUtilities;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.entities.TestEntity;
import spaceVivarium.core.entities.TestSmartEntity;
import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.xml.XmlReader;
import spaceVivarium.core.simulation.Simulation;

import spaceVivarium.utils.thread.IHMThread;
import spaceVivarium.utils.thread.SimulationThread;
import spaceVivarium.utils.thread.ThreadUtil;

public class Test {

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {

        Simulation simulation = getSimulation();
        simulation.init();

        IHMThread ihmThread = new IHMThread(simulation);


        SwingUtilities.invokeLater(ihmThread);

   
        ThreadUtil.execute(new SimulationThread(simulation, ihmThread
                .getSimulationPanel()));

        
    }

    private static Simulation getSimulation() {

        /* test xml reader */

       

       
        Board testmap = null;
        try {
            testmap = XmlReader
                    .xmlToBoard("src\\spaceVivarium\\core\\maps\\xml\\board.xml");
        } catch (XmlFailureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Board map = new Board(50, 50, Tile.class);

      
        java.util.Map<Class<? extends Entity>, Integer> entityConf = new Hashtable<>();

        entityConf.put(TestEntity.class, 20);
        entityConf.put(TestSmartEntity.class, 20);

        return new Simulation(testmap, entityConf);

    }

}
