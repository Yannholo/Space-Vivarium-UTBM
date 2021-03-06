package spaceVivarium.core.maps.tiles;

public enum TileClass {

    VOID(Void.class, "void"), HOLE(Hole.class, "hole"), ASTEROID(
            Asteroid.class, "asteroid"), DEBRIS(Debris.class, "debris"), PLANET(
            Planet.class, "planet"), SUN(Sun.class, "sun"), AIRFLOW(
            Airflow.class, "airflow");

    public final Class<? extends ATile> tileClass;
    public final String string;

    private TileClass(Class<? extends ATile> tileClass, String string) {
        this.tileClass = tileClass;
        this.string = string;
    }

    public static Class<? extends ATile> getTileClass(String s) {
        Class<? extends ATile> tileClass = null;

        switch (s) {
        case "void":
            tileClass = VOID.tileClass;
            break;
        case "hole":
            tileClass = HOLE.tileClass;
            break;
        case "asteroid":
            tileClass = ASTEROID.tileClass;
            break;
        case "debris":
            tileClass = DEBRIS.tileClass;
            break;
        case "planet":
            tileClass = PLANET.tileClass;
            break;
        case "sun":
            tileClass = SUN.tileClass;
            break;
        case "airflow":
            tileClass = AIRFLOW.tileClass;
            break;
        default:
            tileClass = VOID.tileClass;
            break;
        }

        return tileClass;
    }

}
