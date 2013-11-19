package test.yannholo.core.actions;

import test.yannholo.core.entities.Entity;
import test.yannholo.core.maps.tiles.Tile;

public class Move implements Action {
    
    private Entity bestiole;
    private Tile destination;

    public Move(Entity bestiole, Tile destination) {
        this.bestiole = bestiole;
        this.destination = destination;
    }

    @Override
    public void doit() {
        bestiole.getLaCase().setBestiole(null);
        bestiole.setLaCase(destination);
        destination.setBestiole(bestiole);
    }

}
