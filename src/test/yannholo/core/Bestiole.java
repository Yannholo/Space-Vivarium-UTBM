package test.yannholo.core;

import java.util.List;

public class Bestiole {
    
    private Case laCase;
    
    public Bestiole(Case depart) {
        laCase = depart;
    }

    public Case getLaCase() {
        return laCase;
    }

    public void setLaCase(Case laCase) {
        this.laCase = laCase;
    }
    
    public int getVision() {
        return 1;
    }

    public Action update(List<Case> vues) {
        Action todo;
        if(vues.size() <= 0) {
            todo = new Nothing();
        } else {
            todo = new Move(this, vues.get((int)(Math.random() * vues.size())));
        }
        
        return todo;
    }

}
