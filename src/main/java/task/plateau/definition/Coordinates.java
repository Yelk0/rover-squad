package task.plateau.definition;

public interface Coordinates {

    public int getLongitud();
    public int getLatitud();
    Coordinates sum(final Coordinates coordinates);
}
