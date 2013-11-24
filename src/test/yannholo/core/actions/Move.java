package test.yannholo.core.actions;

import test.yannholo.core.entities.AEntity;
import test.yannholo.core.entities.TestEntity;
import test.yannholo.core.maps.tiles.ATile;

public class Move implements IAction {

    private AEntity bestiole;
    private ATile destination;

    public Move(TestEntity bestiole, ATile aTile) {
        this.bestiole = bestiole;
        this.destination = aTile;
    }

    @Override
    public void doit() {
        bestiole.getLaCase().setBestiole(null);
        bestiole.setLaCase(destination);
        destination.setBestiole(bestiole);
    }

}
