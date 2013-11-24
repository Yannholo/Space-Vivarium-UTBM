package test.yannholo.core.simulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import test.yannholo.core.actions.IAction;
import test.yannholo.core.entities.AEntity;
import test.yannholo.core.maps.Field;
import test.yannholo.core.maps.Map;

/**
 * Cet object sera synchronisé avec l'IHM, C'est l'interface entre L'IHM et le
 * "core"
 * 
 * @author Yannholo
 * 
 */
public class Simulation {
    /* Configurations */
    private Map map;
    private java.util.Map<Class<? extends AEntity>, Integer> entityConf;

    int i = 0;

    /* sim objects */
    private Field field;
    private List<? extends AEntity> entities;
    private int j = 0;

    public Simulation(Map map,
            java.util.Map<Class<? extends AEntity>, Integer> entityConf) {
        this.map = map;
        this.entityConf = entityConf;
    }

    public void init() {
        field = new Field(map, entityConf);
        entities = field.getEntities();
    }

    public List<IAction> prepareUpdate() {
        i++;
        System.out.println("prepareUpdate " + i);
        List<IAction> actions = askActions();
        handleConflict(actions);
        System.out.println("prepareUpdend " + i);
        return actions;
    }

    public void print(Graphics g) {
        field.print((Graphics2D) g);
    }

    private List<IAction> askActions() {
        List<IAction> actions = new ArrayList<>(entities.size());
        for (AEntity entity : entities) {
            actions.add(entity.update(field.getView(entity)));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return actions;
    }

    private void handleConflict(List<IAction> actions) {
        // TODO Handle Conflicts
    }

    public void applyUpdate(List<IAction> actions) {
        j++;
        System.out.println("applyUpdate " + j);
        for (IAction iAction : actions) {
            iAction.doit();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("applyUpdend " + j);
    }

}
