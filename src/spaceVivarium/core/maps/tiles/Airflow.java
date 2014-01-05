package spaceVivarium.core.maps.tiles;

import java.awt.Point;

public class Airflow extends BlockingTile {

    private static String[] chemins = { "Airflow.png" };

    public Airflow(Point coord) {
        super(coord, chemins);

    }

}
