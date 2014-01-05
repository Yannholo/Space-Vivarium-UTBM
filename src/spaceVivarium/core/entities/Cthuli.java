package spaceVivarium.core.entities;

import java.util.ArrayList;

import spaceVivarium.core.behaviour.Behaviour;
import spaceVivarium.core.behaviour.Feed;
import spaceVivarium.core.behaviour.Follow;

public class Cthuli extends Entity {

    public Cthuli() {
        super(8, 1000000000, "cthuli.png");
        this.comportements = new ArrayList<Behaviour>();
        ArrayList eaten = new ArrayList();
        eaten.add(Spider.class);
        eaten.add(Ant.class);
        eaten.add(Human.class);

        this.comportements.add(new Follow(Spider.class));
        this.comportements.add(new Follow(Ant.class));
        this.comportements.add(new Follow(Human.class));
        this.comportements.add(new Feed(eaten));
    }

    /*
     * @Override public void print(Graphics2D g, Point point) {
     * g.setColor(Color.MAGENTA); g.fill3DRect(point.x * 10, point.y * 10, 10,
     * 10, true); }
     */

}
