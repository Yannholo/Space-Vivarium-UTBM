package spaceVivarium.core.maps.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Tile extends ATile {

    public Tile(Point coord) {
        super(coord);
    }

    public void print(Graphics2D g2d) {
        if (bestiole != null) {
            g2d.setColor(Color.RED);
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
        }
        g2d.fill3DRect(coord.x * 10, coord.y * 10, 10, 10, true);
    }

}
