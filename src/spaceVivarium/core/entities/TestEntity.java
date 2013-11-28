package spaceVivarium.core.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import spaceVivarium.core.actions.IAction;
import spaceVivarium.core.actions.Move;
import spaceVivarium.core.actions.Nothing;
import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.core.maps.tiles.Tile;

/**
 * 
 * @author Yannholo
 */
public class TestEntity extends AEntity {

    private static BufferedImage img;

    private static BufferedImage getImage() {
        if (img == null) {
            try {
                img = ImageIO.read(TestEntity.class.getResource("ant.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    /**
     * Crée l'entitée de test avec sa tile de depart
     * 
     * @param depart
     *            la tile de depart
     */
    public TestEntity(ATile depart) {
        super(depart);
        vision = 1;
    }

    public IAction update(List<ATile> vues) {
        IAction todo;
        if (vues.size() <= 0) {
            // si l'entité ne vois rien, elle ne fait rien
            todo = new Nothing();
        } else {
            // TODO sinon elle bouge aléatoirement sur une Tile
            todo = new Move(this, vues.get((int) (Math.random() * vues.size())));
        }

        return todo;
    }

    @Override
    public void print(Graphics2D g2d) {
        g2d.drawImage(getImage(), getCurrentTile().getX() * Tile.SIZE,
                getCurrentTile().getY() * Tile.SIZE, Tile.SIZE, Tile.SIZE, null);

    }

}
