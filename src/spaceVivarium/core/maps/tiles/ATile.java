package spaceVivarium.core.maps.tiles;

import java.awt.Graphics2D;
import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;

import spaceVivarium.core.entities.Entity;
import spaceVivarium.utils.ImagesUtils;

public abstract class ATile {

    public static int size = 10;

    protected Point coord;
    protected Entity bestiole;
    protected URL cheminImage;

    public ATile(Point coord, String[] cheminImages) {
        this.coord = coord;
        this.cheminImage = getClass().getResource(
                cheminImages[((int) Math.random() * cheminImages.length)]);
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
        for (int i = coord.x - 1; i <= coord.x + 1; i++) {
            for (int j = coord.y - 1; i <= coord.y + 1; j++) {
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
        ATile autre = (ATile) arg0;
        return coord == autre.coord;
    }

    public void print(Graphics2D g2d) {
        g2d.drawImage(ImagesUtils.getImage(cheminImage), getX() * size, getY()
                * size, size, size, null);
    }
}