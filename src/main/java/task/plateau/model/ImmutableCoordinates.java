package task.plateau.model;

import java.util.Objects;

import task.plateau.definition.Coordinates;

public class ImmutableCoordinates implements Coordinates {

    private int longitud;
    private int latitud;

    public ImmutableCoordinates() {
    }

    public ImmutableCoordinates(int longitud, int latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public ImmutableCoordinates(final Coordinates coordinates){
        this(coordinates.getLongitud(), coordinates.getLatitud());
    }

    @Override
    public int getLongitud() {
        return longitud;
    }

    @Override
    public int getLatitud() {
        return latitud;
    }

    @Override
    public Coordinates sum(Coordinates coordinates) {
        int lon = this.longitud + coordinates.getLongitud();
        int lat = this.latitud + coordinates.getLatitud();
        return new CoordinatesImpl(lon, lat);
    }

    @Override
    public String toString() {
        return "ImmutableCoordinates{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableCoordinates that = (ImmutableCoordinates) o;
        return longitud == that.longitud &&
                latitud == that.latitud;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitud, latitud);
    }
}
