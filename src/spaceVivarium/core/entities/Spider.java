package spaceVivarium.core.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import spaceVivarium.core.behaviour.Behaviour;
import spaceVivarium.core.behaviour.Escape;
import spaceVivarium.core.behaviour.Feed;
import spaceVivarium.core.behaviour.Follow;
import spaceVivarium.core.behaviour.Reproduce;

public class Spider extends Entity {

    public Spider() {
        super(5, 100, 25);
        this.comportements = new ArrayList<Behaviour>();
        ArrayList eaten = new ArrayList();
        eaten.add(Human.class);

        this.comportements.add(new Follow(Human.class));
        this.comportements.add(new Escape(Cthuli.class));
        this.comportements.add(new Escape(Ant.class));
        this.comportements.add(new Feed(eaten));
        this.comportements.add(new Reproduce(Spider.class));
    }

    @Override
    public void print(Graphics2D g, Point point) {
        g.setColor(Color.YELLOW);
        g.fill3DRect(point.x * 10, point.y * 10, 10, 10, true);
    }

}
