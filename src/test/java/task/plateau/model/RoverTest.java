package task.plateau.model;

import static org.junit.Assert.assertEquals;

import javax.management.InstanceAlreadyExistsException;

import org.junit.Test;
import task.plateau.enumerate.CardinalStep;

public class RoverTest {

    @Test(expected = InstanceAlreadyExistsException.class)
    public void when2RoversOnSameSpotThenThrowException() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover rover = new Rover(1, 1, CardinalStep.N, plateau);
        new Rover(1,1, CardinalStep.E,plateau);
    }

    @Test(expected = InstantiationException.class)
    public void whenRoversCreatedOutOfBoundriesThenThrowException() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover rover = new Rover(5, 1, CardinalStep.N, plateau);
    }

    @Test
    public void whenRoversStepsOnOtherRoverThenDoNotMoveAndSaveCollision() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover staticRover = new Rover(1, 1, CardinalStep.N, plateau);
        Rover movingRover = new Rover(0, 1, CardinalStep.E, plateau);
        movingRover.move();
        assertEquals(movingRover.getCollisions().get(0), staticRover.getCoordinates());
        assertEquals(movingRover.getCoordinates(), new ImmutableCoordinates(0,1));
    }

    @Test
    public void whenRoversStepsOutEastOfTheBoundriesThenDoNotMoveAndSaveCollision() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover movingRover = new Rover(4, 4, CardinalStep.E, plateau);
        movingRover.move();
        assertEquals(movingRover.getCollisions().get(0), new ImmutableCoordinates(5,4));
        assertEquals(movingRover.getCoordinates(), new ImmutableCoordinates(4,4));

    }

    @Test
    public void whenRoversStepsOutSouthOfTheBoundriesThenDoNotMoveAndSaveCollision() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover movingRover = new Rover(0, 0, CardinalStep.S, plateau);
        movingRover.move();
        assertEquals(movingRover.getCollisions().get(0), new ImmutableCoordinates(0,-1));
        assertEquals(movingRover.getCoordinates(), new ImmutableCoordinates(0,0));

    }

    @Test
    public void whenRoversStepsOutWestOfTheBoundriesThenDoNotMoveAndSaveCollision() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover movingRover = new Rover(0, 0, CardinalStep.W, plateau);
        movingRover.move();
        assertEquals(movingRover.getCollisions().get(0), new ImmutableCoordinates(-1,0));
        assertEquals(movingRover.getCoordinates(), new ImmutableCoordinates(0,0));

    }

    @Test
    public void whenRoversStepsOutNorthOfTheBoundriesThenDoNotMoveAndSaveCollision() throws Exception {
        Plateau plateau = new Plateau(4, 4);
        Rover movingRover = new Rover(4, 4, CardinalStep.N, plateau);
        movingRover.move();
        assertEquals(movingRover.getCollisions().get(0), new ImmutableCoordinates(4,5));
        assertEquals(movingRover.getCoordinates(), new ImmutableCoordinates(4,4));

    }

    @Test
    public void whenRoverSpinsRightThenCardinalStepSwitches90DegreesClockwise() throws Exception{
        Plateau plateau = new Plateau(4, 4);
        Rover rover = new Rover(1, 1, CardinalStep.N, plateau);
        rover.spinRight();
        assertEquals(rover.getCardinalStep(),CardinalStep.E);
        rover.spinRight();
        assertEquals(rover.getCardinalStep(),CardinalStep.S);
        rover.spinRight();
        assertEquals(rover.getCardinalStep(),CardinalStep.W);
        rover.spinRight();
        assertEquals(rover.getCardinalStep(),CardinalStep.N);
    }
    @Test
    public void whenRoverSpinsLeftThenCardinalStepSwitches90DegreesCounterClockwise() throws Exception{
        Plateau plateau = new Plateau(4, 4);
        Rover rover = new Rover(1, 1, CardinalStep.N, plateau);
        rover.spinLeft();
        assertEquals(rover.getCardinalStep(),CardinalStep.W);
        rover.spinLeft();
        assertEquals(rover.getCardinalStep(),CardinalStep.S);
        rover.spinLeft();
        assertEquals(rover.getCardinalStep(),CardinalStep.E);
        rover.spinLeft();
        assertEquals(rover.getCardinalStep(),CardinalStep.N);
    }
}
