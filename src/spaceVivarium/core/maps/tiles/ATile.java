package spaceVivarium.core.maps.tiles;

import java.awt.Graphics2D;
import java.awt.Point;

import spaceVivarium.core.entities.AEntity;

public abstract class ATile {
    Point coord;
    protected AEntity bestiole;

    public ATile(Point coord) {
        this.coord = coord;
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }

    public AEntity getEntity() {
        return bestiole;
    }

    public void setBestiole(AEntity bestiole) {
        this.bestiole = bestiole;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0.getClass().equals(this.getClass())) {
            return false;
        }
        Tile autre = (Tile) arg0;
        return coord == autre.coord;
    }

    public abstract void print(Graphics2D g2d);
}
