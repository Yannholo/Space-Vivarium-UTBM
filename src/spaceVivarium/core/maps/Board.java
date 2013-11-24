package spaceVivarium.core.maps;

import java.awt.Point;
import java.util.HashMap;

import spaceVivarium.core.maps.tiles.ATile;
import spaceVivarium.core.maps.tiles.Tile;

public class Board {

    public static final Class<? extends ATile> DEFAULT_TILE_CLASS = Tile.class;

    private java.util.Map<Point, Class<? extends ATile>> map;

    private int sizeX;
    private int sizeY;
    private Class<? extends ATile> baseType;

    public Board(int sizeX, int sizeY) {
        this(sizeX, sizeY, DEFAULT_TILE_CLASS);
    }

    public Board(int sizeX, int sizeY, Class<? extends ATile> baseType) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.baseType = baseType;
        map = new HashMap<>(sizeX * sizeY);
        fillRect(0, 0, sizeX, sizeY, baseType);
    }

    public void set(int x, int y, Class<? extends ATile> type) {
        if (x < sizeX && y < sizeY) {
            map.put(new Point(x, y), type);
        }
    }

    public void fillRect(
            int x, int y, int width, int height, Class<? extends ATile> type) {
        for (int i = x; i < x + width && x < sizeX; i++) {
            for (int j = y; j < y + height && y < sizeY; j++) {
                map.put(new Point(i, j), type);
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Class<? extends ATile> getTileClass(int x, int y) {
        return map.get(new Point(x, y));
    }

}
