package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.entities.Entity;

public class Airflow extends ATile {

    private static String[] chemins = { "Airflow.png" };

    public Airflow(Point coord) {
        super(coord, chemins);

    }

    @Override
    public void affect(Entity entity) {
        // bloqued

    }
}
