package task.plateau.model;

import static task.plateau.constant.General.ERROR_MESSAGE_FORMAT_COLLISION;
import static task.plateau.constant.General.ERROR_MESSAGE_OCCUPIED_POSITION;
import static task.plateau.constant.General.ERROR_MESSAGE_OUT_OF_BOUNDRIES_POSITION;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import task.plateau.definition.Coordinates;
import task.plateau.enumerate.CardinalStep;

public class Rover {

    private Coordinates coordinates;
    private CardinalStep cardinalStep;
    private Plateau plateau;
    private List<Coordinates> collisions;

    private static final Logger LOGGER = Logger.getLogger(Rover.class.getName());

    public Rover() {
    }

    public Rover(int longitud, int latitud, CardinalStep cardinalStep, Plateau plateau) throws InstanceAlreadyExistsException, InstantiationException {
        this.coordinates = new CoordinatesImpl(longitud, latitud);
        this.cardinalStep = cardinalStep;
        this.plateau = plateau;
        this.collisions = new ArrayList<>();

        if (plateau.isFree(this.coordinates)){
            plateau.setOccupy(this.coordinates);
        } else {
            if (plateau.isWithinBoundries(this.coordinates)) {
                LOGGER.log(Level.SEVERE, ERROR_MESSAGE_OCCUPIED_POSITION);
                throw new InstanceAlreadyExistsException(ERROR_MESSAGE_OCCUPIED_POSITION);
            } else {
                LOGGER.log(Level.SEVERE, ERROR_MESSAGE_OUT_OF_BOUNDRIES_POSITION);
                throw new InstantiationException(ERROR_MESSAGE_OUT_OF_BOUNDRIES_POSITION);
            }
        }
    }

    public void move(){
        Coordinates potentialCoordinates = this.cardinalStep.getCoordinates().sum(this.coordinates);
        if (this.plateau.isFree(potentialCoordinates)){
            this.plateau.setFree(this.coordinates);
            this.coordinates.sum(cardinalStep.getCoordinates());
            this.plateau.setOccupy(this.coordinates);
        } else {
            LOGGER.log(Level.SEVERE, String.format(ERROR_MESSAGE_FORMAT_COLLISION, potentialCoordinates.getLongitud(), potentialCoordinates.getLatitud()));
            this.collisions.add(new ImmutableCoordinates(potentialCoordinates));
        }
    }

    public void spinRight(){
        cardinalStep = CardinalStep.rotatingClockwise(cardinalStep);
    }

    public void spinLeft(){
        cardinalStep = CardinalStep.rotatingCounterClockwise(cardinalStep);

    }

    public Coordinates getCoordinates() {
        return new ImmutableCoordinates(coordinates);
    }

    public CardinalStep getCardinalStep() {
        return cardinalStep;
    }

    public List<Coordinates> getCollisions() {
        return collisions;
    }

    public String getFormattedOutput() {
        return String.format("%d %d %s",this.coordinates.getLongitud(),this.coordinates.getLatitud(),this.cardinalStep);
    }

    @Override
    public String toString() {
        return "Rover{" +
                "coordinates=" + coordinates +
                ", cardinalStep=" + cardinalStep +
                ", plateau=" + plateau +
                ", collisions=" + collisions +
                '}';
    }
}
