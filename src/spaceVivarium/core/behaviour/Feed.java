package spaceVivarium.core.behaviour;

import java.util.ArrayList;

import spaceVivarium.core.entities.Entity;

public class Feed {
    // l'entit� qui s'�chappe
    private Entity entity;
    // le type des entit�s � manger
    private ArrayList<Class<? extends Entity>> typeEnemy;

    public Feed(Entity entity, ArrayList<Class<? extends Entity>> typeEnemy) {
        this.entity = entity;
        this.typeEnemy = typeEnemy;
    }

}
