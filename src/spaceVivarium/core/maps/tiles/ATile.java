package spaceVivarium.core.maps.tiles;

import java.awt.Graphics2D;
import java.awt.Point;
import java.net.URL;

import spaceVivarium.core.actions.Action;
import spaceVivarium.core.entities.Entity;
import spaceVivarium.core.maps.Board;
import spaceVivarium.utils.ImagesUtils;

public abstract class ATile {

    public static int size = 10;
    protected URL cheminImage;

    public ATile(String[] cheminImages) {
        String chemin = cheminImages[((int) Math.random() * cheminImages.length)];

        this.cheminImage = getClass().getResource(chemin);

    }

    public void print(Graphics2D g2d, Point point) {
        g2d.drawImage(ImagesUtils.getImage(cheminImage), point.x * size,
                point.y * size, size, size, null);
    }

    public abstract Action affect(Entity entity, Point point, Board map);
}