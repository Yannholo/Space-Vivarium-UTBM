package spaceVivarium.core.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import spaceVivarium.core.behaviour.Behaviour;
import spaceVivarium.core.behaviour.Feed;
import spaceVivarium.core.behaviour.Follow;
import spaceVivarium.core.behaviour.Reproduce;

public class TestSmartEntity extends Entity {

    /**
     * Crée l'entitée de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     */
    public TestSmartEntity() {
        super(5, 100, 25);
        this.comportements = new ArrayList<Behaviour>();
        ArrayList eaten = new ArrayList();
        eaten.add(TestEntity.class);
        this.comportements.add(new Follow(TestEntity.class));
        this.comportements.add(new Feed(eaten));
        this.comportements.add(new Reproduce(TestSmartEntity.class));
        System.out.println("new SmartEntity");
    }

    @Override
    public void print(Graphics2D g, Point point) {
        g.setColor(Color.GREEN);
        g.fill3DRect(point.x * 10, point.y * 10, 10, 10, true);
    }

}