package test.yannholo.core.entities;

import java.util.List;

import test.yannholo.core.actions.Action;
import test.yannholo.core.actions.Move;
import test.yannholo.core.actions.Nothing;
import test.yannholo.core.maps.tiles.Tile;

public class Entity {
    
    private Tile laCase;
    
    public Entity(Tile depart) {
        laCase = depart;
    }

    public Tile getLaCase() {
        return laCase;
    }

    public void setLaCase(Tile laCase) {
        this.laCase = laCase;
    }
    
    public int getVision() {
        return 1;
    }

    public Action update(List<Tile> vues) {
        Action todo;
        if(vues.size() <= 0) {
            todo = new Nothing();
        } else {
            todo = new Move(this, vues.get((int)(Math.random() * vues.size())));
        }
        
        return todo;
    }

}
