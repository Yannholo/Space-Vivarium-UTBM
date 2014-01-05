package spaceVivarium.core.entities;

import java.util.ArrayList;

import spaceVivarium.core.behaviour.Behaviour;
import spaceVivarium.core.behaviour.Escape;
import spaceVivarium.core.behaviour.Reproduce;

public class Human extends Entity {

    public Human() {
        super(8, 100, 25, "human.png");
        this.comportements = new ArrayList<Behaviour>();
        this.comportements.add(new Escape(Ant.class));
        this.comportements.add(new Escape(Spider.class));
        this.comportements.add(new Escape(Cthuli.class));
        this.comportements.add(new Reproduce(Human.class));

    }

    /*
     * @Override public void print(Graphics2D g, Point point) {
     * g.setColor(Color.BLUE); g.fill3DRect(point.x * 10, point.y * 10, 10, 10,
     * true); }
     */

}
