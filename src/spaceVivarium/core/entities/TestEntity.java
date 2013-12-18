package spaceVivarium.core.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import spaceVivarium.core.behaviour.Craziness;

/**
 * 
 * @author Yannholo
 */
public class TestEntity extends Entity {

    public TestEntity() {
        super(1);
        comportements.add(new Craziness());
    }

    @Override
    public void print(Graphics2D g, Point point) {
        g.setColor(Color.RED);
        g.fill3DRect(point.x * 10, point.y * 10, 10, 10, true);
    }
}
