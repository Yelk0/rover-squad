package task.plateau.constant;

public class General {
    public static final String PLATEAU_FORMAT = "\\d \\d";
    public static final String ROVER_FORMAT = "\\d \\d [N|E|S|W]";
    public static final String INSTRUCTION_FORMAT = "[L|R|M]+";

    public static final String DEFAULT_TEXT_FILE = "rover_input.txt";

    public static final String SPACE = " ";

    public static final char INSTRUCTION_TO_CLOCKWISE_SPIN = 'R';
    public static final char INSTRUCTION_TO_COUNTER_CLOCKWISE_SPIN = 'L';
    public static final char INSTRUCTION_TO_MOVE = 'M';

    public static final String ERROR_MESSAGE_PLATEAU_WRONG_SPEC = "Could not create Plateau with specification provided";
    public static final String ERROR_MESSAGE_PLATEAU_WRONG_FORMAT = "Plateau does not have the right format";
    public static final String ERROR_MESSAGE_ROVERS_SAME_SPOT = "At least 2 rovers are landing on the same spot";
    public static final String ERROR_MESSAGE_ROVER_OUT_OF_BOUNDRIES = "Specified position is out of the plateau´s boundries";
    public static final String ERROR_MESSAGE_UNKNOWN_FORMAT = "Line with unknows format";

    public static final String ERROR_MESSAGE_PLATEAU_TOO_SMALL = "Plateau is too small to be created";

    public static final String ERROR_MESSAGE_OCCUPIED_POSITION = "This position is occupied";
    public static final String ERROR_MESSAGE_OUT_OF_BOUNDRIES_POSITION = "This position is occupied";

    public static final String ERROR_MESSAGE_FORMAT_COLLISION = "Collision avoided at Coordinates {%d, %d}";
}
