package spaceVivarium.core.behaviour;

import java.util.ArrayList;

import spaceVivarium.core.entities.Entity;

public class Feed {
    // l'entité qui s'échappe
    private Entity entity;
    // le type des entités à manger
    private ArrayList<Class<? extends Entity>> typeEnemy;

    public Feed(Entity entity, ArrayList<Class<? extends Entity>> typeEnemy) {
        this.entity = entity;
        this.typeEnemy = typeEnemy;
    }

}
