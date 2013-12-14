package spaceVivarium.core.maps.tiles;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import spaceVivarium.core.entities.Entity;

public abstract class ATile {
    Point coord;
    protected Entity bestiole;

    public ATile(Point coord) {
        this.coord = coord;
    }

    public int getX() {
        return coord.x;
    }

    public int getY() {
        return coord.y;
    }

    public Point getCoord() {
        return coord;
    }

    public Entity getEntity() {
        return bestiole;
    }

    public void setBestiole(Entity bestiole) {
        this.bestiole = bestiole;
    }

    public ArrayList<Point> getAdjacentCoords() {
        ArrayList<Point> adjTiles = new ArrayList<Point>();
        for (int i = coord.x - 1; i < coord.x + 1; i++) {
            for (int j = coord.y - 1; i < coord.y + 1; j++) {
                if (i >= 0 && j >= 0)
                    adjTiles.add(new Point(i, j));
            }
        }
        return adjTiles;
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
