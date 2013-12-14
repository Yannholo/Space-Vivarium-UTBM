package spaceVivarium.core.simulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Field;
import spaceVivarium.core.maps.Board;

/**
 * Cet object sera synchronisť avec l'IHM, C'est l'interface entre L'IHM et le
 * "core"
 * 
 * @author Yannholo
 * 
 */
public class Simulation {
    /* Configurations */
    private Board map;
    private java.util.Map<Class<? extends Entity>, Integer> entityConf;

    int i = 0;

    /* sim objects */
    private Field field;
    private List<? extends Entity> entities;
    private int j = 0;

    public Simulation(Board map,
            java.util.Map<Class<? extends Entity>, Integer> entityConf) {
        this.map = map;
        this.entityConf = entityConf;
    }

    public void init() {
        field = new Field(map, entityConf);
        entities = field.getEntities();
    }

    public List<Action> prepareUpdate() {
        i++;
        System.out.println("prepareUpdate " + i);
        List<Action> actions = askActions();
        handleConflict(actions);
        System.out.println("prepareUpdend " + i);
        return actions;
    }

    public void print(Graphics g) {
        field.print((Graphics2D) g);
    }

    private List<Action> askActions() {
        List<Action> actions = new ArrayList<>(entities.size());
        for (Entity entity : entities) {
            actions.add(entity.update(field.getView(entity)));
        }
        return actions;
    }

    private void handleConflict(List<Action> actions) {
        // TODO Handle Conflicts
    }

    public void applyUpdate(List<Action> actions) {
        j++;
        System.out.println("applyUpdate " + j);
        for (Action iAction : actions) {
            iAction.doIt();
        }
        System.out.println("applyUpdend " + j);
    }

}
