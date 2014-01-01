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

public class Ant extends Entity {

    public Ant() {
        super(5, 100, 25);
        this.comportements = new ArrayList<Behaviour>();
        ArrayList eaten = new ArrayList();
        eaten.add(Spider.class);
        eaten.add(Human.class);

        this.comportements.add(new Follow(Spider.class));
        this.comportements.add(new Follow(Human.class));
        this.comportements.add(new Escape(Cthuli.class));
        this.comportements.add(new Feed(eaten));
        this.comportements.add(new Reproduce(Ant.class));

    }

    @Override
    public void print(Graphics2D g, Point point) {
        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(point.x * 10, point.y * 10, 10, 10, true);
    }

}
