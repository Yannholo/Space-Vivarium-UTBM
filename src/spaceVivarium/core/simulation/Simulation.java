package spaceVivarium.core.simulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Board;
import spaceVivarium.core.maps.Field;

/**
 * Cet object sera synchronisé avec l'IHM, C'est l'interface entre L'IHM et le
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
            Map<Class<? extends Entity>, Integer> entityConf) {
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
        actions.removeAll(handleConflict(actions));
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

    private List<Action> handleConflict(List<Action> actions) {
        List<Action> toRemove = new ArrayList<>();
        Action tmp = null;
        for (Action action1 : actions) {
            for (Action action2 : actions) {
                if (!toRemove.contains(action2)) {
                    if ((tmp = action1.inConflict(action2)) != null) {
                        toRemove.add(tmp);
                        System.out.println("remove " + tmp);
                        break;
                    }
                }
            }
        }
        return toRemove;
    }

    public void applyUpdate(List<Action> actions) {
        j++;
        System.out.println("applyUpdate " + j);
        for (Action iAction : actions) {
            System.out.println("Apply : " + iAction);
            iAction.doIt();
        }
        System.out.println("applyUpdend " + j);
    }

}
