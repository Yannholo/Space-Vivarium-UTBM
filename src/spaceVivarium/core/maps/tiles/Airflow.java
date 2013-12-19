package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Airflow extends ATile {

    private static String[] chemins = { "Airflow.png" };

    public Airflow(Point coord) {
        super(coord, chemins[getInt()]);

    }

    public static int getInt() {
        int retour = (int) (Math.random() * chemins.length);
        return retour;
    }
}
