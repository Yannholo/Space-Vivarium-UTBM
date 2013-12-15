package spaceVivarium.core.maps.tiles;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import spaceVivarium.core.entities.Entity;

public abstract class ATile {
    Point coord;
    protected Entity entity;

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
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public ArrayList<Point> getAdjacentCoords() {
        ArrayList<Point> adjTiles = new ArrayList<Point>();
        for (int i = coord.x - 1; i <= coord.x + 1; i++) {
            for (int j = coord.y - 1; i <= coord.y + 1; j++) {
                if (i >= 0 && j >= 0)
                    adjTiles.add(new Point(i, j));
            }
        }
        return adjTiles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile tile = (Tile) obj;
        return coord.equals(tile.coord);
    }

    public abstract void print(Graphics2D g2d);
}
