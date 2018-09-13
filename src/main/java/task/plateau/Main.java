package task.plateau;

import static task.plateau.constant.General.DEFAULT_TEXT_FILE;
import static task.plateau.constant.General.ERROR_MESSAGE_PLATEAU_WRONG_FORMAT;
import static task.plateau.constant.General.ERROR_MESSAGE_PLATEAU_WRONG_SPEC;
import static task.plateau.constant.General.ERROR_MESSAGE_ROVERS_SAME_SPOT;
import static task.plateau.constant.General.ERROR_MESSAGE_ROVER_OUT_OF_BOUNDRIES;
import static task.plateau.constant.General.ERROR_MESSAGE_UNKNOWN_FORMAT;
import static task.plateau.constant.General.INSTRUCTION_FORMAT;
import static task.plateau.constant.General.INSTRUCTION_TO_CLOCKWISE_SPIN;
import static task.plateau.constant.General.INSTRUCTION_TO_COUNTER_CLOCKWISE_SPIN;
import static task.plateau.constant.General.INSTRUCTION_TO_MOVE;
import static task.plateau.constant.General.PLATEAU_FORMAT;
import static task.plateau.constant.General.ROVER_FORMAT;
import static task.plateau.constant.General.SPACE;

import javax.management.InstanceAlreadyExistsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import task.plateau.model.Plateau;
import task.plateau.model.Rover;
import task.plateau.enumerate.CardinalStep;


public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        BufferedReader reader = initReader(DEFAULT_TEXT_FILE);
        Plateau plateau = initPlateau(reader);
        List<Rover> rovers = new ArrayList<>();
        List<String> instructions = new ArrayList<>();

        if (plateau != null) {
            initData(reader, rovers, instructions, plateau);
            processInstructions(instructions,rovers);
        }

        rovers.forEach(rover -> System.out.println(rover.getFormattedOutput()));

    }

    private static BufferedReader initReader(final String textFileName){
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(textFileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        return new BufferedReader(streamReader);
    }

    private static Plateau initPlateau(final BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String[] coordinates = null;

        if (line.matches(PLATEAU_FORMAT)) {
            coordinates = line.split(SPACE);
        }

        if (coordinates != null && coordinates.length >= 2) {
            try {
                return new Plateau(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
            } catch (InstantiationException e) {
                LOGGER.log(Level.SEVERE, ERROR_MESSAGE_PLATEAU_WRONG_SPEC, e);
            }
        }
        LOGGER.log(Level.SEVERE, ERROR_MESSAGE_PLATEAU_WRONG_FORMAT);
        return null;
    }

    private static void initData(final BufferedReader reader, final List<Rover> rovers, final List<String> instructions, final Plateau plateau) throws IOException {
        for (String line; (line = reader.readLine()) != null;) {
            if (line.matches(ROVER_FORMAT)){
                String[] rover = line.split(SPACE);
                int longitud = Integer.parseInt(rover[0]);
                int latitud = Integer.parseInt(rover[1]);
                CardinalStep cardinal = CardinalStep.valueOf(rover[2]);
                Rover newRover = null;
                try {
                    newRover = new Rover(longitud, latitud, cardinal, plateau);
                } catch (InstanceAlreadyExistsException e) {
                    LOGGER.log(Level.SEVERE, ERROR_MESSAGE_ROVERS_SAME_SPOT, e);
                    continue;
                } catch (InstantiationException e) {
                    LOGGER.log(Level.SEVERE, ERROR_MESSAGE_ROVER_OUT_OF_BOUNDRIES, e);
                    continue;
                }
                rovers.add(newRover);
            } else if (line.matches(INSTRUCTION_FORMAT)){
                instructions.add(line);
            } else {
                LOGGER.log(Level.WARNING, ERROR_MESSAGE_UNKNOWN_FORMAT);
            }
        }
    }

    private static void processInstructions(final List<String> instructions, final List<Rover> rovers){
        for (int roverNum = 0; roverNum < rovers.size(); roverNum++){
            String instruction = instructions.get(roverNum);
            Rover rover = rovers.get(roverNum);
            for (int position = 0; position < instruction.length(); position++){
                if (instruction.charAt(position) == INSTRUCTION_TO_CLOCKWISE_SPIN){
                    rover.spinRight();
                } else if (instruction.charAt(position) == INSTRUCTION_TO_COUNTER_CLOCKWISE_SPIN){
                    rover.spinLeft();
                } else if (instruction.charAt(position) == INSTRUCTION_TO_MOVE){
                    rover.move();
                }
            }
        }
    }
}
