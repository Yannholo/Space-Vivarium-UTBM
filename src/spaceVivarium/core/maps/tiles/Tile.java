package spaceVivarium.core.maps.tiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Tile extends ATile {

    public static final int SIZE = 25;

    public static final double COEF_PENCHAGE = 2.5; // != 0

    private static final double COEF_PENCHAGE_ABS = COEF_PENCHAGE < 0 ? -COEF_PENCHAGE
            : COEF_PENCHAGE;

    private static final double COEF_PENCHAGE_DOUBLE = COEF_PENCHAGE_ABS * 2;

    public Tile(Point coord) {
        super(coord);
    }

    public void print(Graphics2D g2d) {

        /*
         * int screenX = (coord.x - coord.y) * SIZE / 2 + 25 * SIZE; int screenY
         * = (int) ((coord.x + coord.y) * SIZE / COEF_PENCHAGE_DOUBLE);
         * 
         * 
         * 
         * // @formatter:off g2d.drawPolygon(new int[] { screenX, screenX+SIZE/2
         * , screenX, screenX-SIZE/2}, new int[] { screenY,(int)
         * (screenY+SIZE/COEF_PENCHAGE_DOUBLE),(int)
         * (screenY+SIZE/COEF_PENCHAGE_ABS),(int)
         * (screenY+SIZE/COEF_PENCHAGE_DOUBLE)}, 4); // @formatter:on
         */
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill3DRect(coord.x * SIZE, coord.y * SIZE, SIZE, SIZE, true);
        // g2d.fillRect(coord.x * SIZE, coord.y * SIZE, SIZE, SIZE);
        // g2d.setColor(Color.GRAY);
        // g2d.drawRect(coord.x * SIZE, coord.y * SIZE, SIZE, SIZE);

        if (entity != null) {
            entity.print(g2d);
        }

    }
}
