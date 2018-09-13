package task.plateau.model;

import static task.plateau.constant.General.ERROR_MESSAGE_PLATEAU_TOO_SMALL;

import task.plateau.definition.Coordinates;

public class Plateau {

    private boolean[][] matrix;

    public Plateau(final int maxX, final int maxY) throws InstantiationException {
        if (maxX < 0 || maxY < 0){
            throw new InstantiationException(ERROR_MESSAGE_PLATEAU_TOO_SMALL);
        }
        this.matrix = new boolean[maxY + 1][maxX + 1];
    }

    public void setOccupy(final Coordinates coordinates){
        if (isWithinBoundries(coordinates)){
            this.matrix[coordinates.getLatitud()][coordinates.getLongitud()] = true;
        }
    }

    public void setFree(final Coordinates coordinates){
        if (isWithinBoundries(coordinates)){
            this.matrix[coordinates.getLatitud()][coordinates.getLongitud()] = false;
        }
    }

    public boolean isFree(final Coordinates coordinates){
        if (isWithinBoundries(coordinates)){
            return !this.matrix[coordinates.getLatitud()][coordinates.getLongitud()];
        }
        return false;
    }

    public boolean isWithinBoundries(final Coordinates coordinates){
        return coordinates.getLongitud() >= 0 && coordinates.getLongitud() < this.matrix.length &&
                coordinates.getLatitud() >= 0 && coordinates.getLatitud() < this.matrix[0].length;
    }

    @Override
    public String toString() {
        String plateauString = "Plateau{\n";
        for(int positiony = this.matrix.length - 1; positiony >= 0; positiony--){
            for(int positionx = 0; positionx < this.matrix.length; positionx++){
                plateauString += String.valueOf(this.matrix[positiony][positionx]) + '\t';
            }
            plateauString += "\n";
        }
        plateauString += '}';
        return plateauString;
    }
}
