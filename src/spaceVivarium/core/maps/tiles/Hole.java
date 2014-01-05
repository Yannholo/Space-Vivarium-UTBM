package spaceVivarium.core.maps.tiles;

import java.awt.Point;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Board;

public class Hole extends ATile {

    private static String[] chemins = { "void3.png" };

    public Hole() {
        super(chemins);

    }

    @Override
    public Action affect(Entity entity, Point point, Board map) {

        return new Move(point, new Point(
                (int) (map.getSizeX() * Math.random()),
                (int) (map.getSizeY() * Math.random())), 10);
    }
}
