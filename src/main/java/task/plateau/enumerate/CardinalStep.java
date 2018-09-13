package task.plateau.enumerate;

import task.plateau.definition.Coordinates;
import task.plateau.model.ImmutableCoordinates;

/**
 * The enum Cardinal step identifies the next step according to the cardinal position
 */
public enum CardinalStep {
    N(new ImmutableCoordinates(0,1)),
    E(new ImmutableCoordinates(1,0)),
    S(new ImmutableCoordinates(0,-1)),
    W(new ImmutableCoordinates(-1,0));


    private final ImmutableCoordinates coordinates;

    CardinalStep(final ImmutableCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates(){
        return this.coordinates;
    }

    public static CardinalStep rotatingClockwise(final CardinalStep cardinalStep){
        if (cardinalStep == null){
            return null;
        }

        CardinalStep[] cardinalSteps = CardinalStep.values();
        for (int position = 0; position < cardinalSteps.length; position++){
            if (cardinalSteps[position] == cardinalStep){
                return position < cardinalSteps.length - 1 ? cardinalSteps[position + 1] : cardinalSteps[0];
            }
        }
        return null;
    }

    public static CardinalStep rotatingCounterClockwise(final CardinalStep cardinalStep){
        if (cardinalStep == null){
            return null;
        }

        CardinalStep[] cardinalSteps = CardinalStep.values();
        for (int position = 0; position < cardinalSteps.length; position++){
            if (cardinalSteps[position] == cardinalStep){
                return position > 0 ? cardinalSteps[position - 1] : cardinalSteps[cardinalSteps.length - 1];
            }
        }
        return null;
    }
}
