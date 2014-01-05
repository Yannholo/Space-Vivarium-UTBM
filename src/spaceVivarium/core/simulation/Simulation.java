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
    private List<Action> userActions;
    private List<Action> environmentalActions;
    private boolean pause;
    private boolean end;

    int i = 0;

    /* sim objects */
    private Field field;

    private int j = 0;

    public Simulation(Board map,
            Map<Class<? extends Entity>, Integer> entityConf) {
        this.map = map;
        this.entityConf = entityConf;
        this.userActions = new ArrayList<Action>();
        end = false;
        pause = false;
    }

    public void init() {
        field = new Field(map, entityConf);

    }

    public Field getField() {
        return field;
    }

    public boolean isPaused() {
        return pause;
    }

    public boolean isEnded() {
        return end;
    }

    public void setPause(boolean p) {
        this.pause = p;
    }

    public void setEnd(boolean e) {
        this.end = e;
    }

    public List<Action> prepareUpdate() {
        i++;
        // System.out.println("prepareUpdate " + i);
        List<Action> actions = askActions();
        actions.removeAll(handleConflict(actions));
        // System.out.println("prepareUpdend " + i);
        return actions;
    }

    public void print(Graphics g) {
        field.print((Graphics2D) g);
    }

    private List<Action> askActions() {
        List<Action> actions = field.askActions();
        if (this.userActions != null) {
            actions.addAll(this.userActions);
            this.userActions.clear();
        }
        if (this.environmentalActions != null) {
            actions.addAll(environmentalActions);
            this.environmentalActions.clear();
        }
        return actions;
    }

    public void addActions(List<Action> uActions) {
        this.userActions.addAll(uActions);
    }

    private List<Action> handleConflict(List<Action> actions) {
        List<Action> toRemove = new ArrayList<>();
        Action tmp = null;
        for (Action action1 : actions) {
            for (Action action2 : actions) {
                if (!toRemove.contains(action2)) {
                    if ((tmp = action1.inConflict(action2)) != null) {
                        toRemove.add(tmp);
                        // System.out.println("remove " + tmp);
                    }
                }
            }
        }
        return toRemove;
    }

    public void applyUpdate(List<Action> actions) {
        j++;
        // System.out.println("applyUpdate " + j);
        environmentalActions = field.applyUpdates(actions);
        // System.out.println("applyUpdend " + j);
    }

}
