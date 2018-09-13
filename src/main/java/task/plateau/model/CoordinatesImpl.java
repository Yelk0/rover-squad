package task.plateau.model;

import java.util.Objects;

import task.plateau.definition.Coordinates;

public class CoordinatesImpl implements Coordinates {

    private int longitud;
    private int latitud;

    public CoordinatesImpl() {
    }

    public CoordinatesImpl(int longitud, int latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
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
    public Coordinates sum(final Coordinates coordinates){
        this.longitud += coordinates.getLongitud();
        this.latitud += coordinates.getLatitud();
        return this;
    }

    @Override
    public String toString() {
        return "CoordinatesImpl{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesImpl that = (CoordinatesImpl) o;
        return Objects.equals(longitud, that.longitud) &&
                Objects.equals(latitud, that.latitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitud, latitud);
    }
}
