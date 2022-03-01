package com.company.collection;

import com.company.Difficulty;
import com.company.superclass.Coordinates;
import com.company.superclass.Discipline;
import com.company.superclass.LabWork;
import com.company.superclass.NewLab;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class NewLabWork implements NewLab {
    private PrintStream printStream;
    private Scanner scanner;

    public NewLabWork(PrintStream printStream, Scanner scanner) {
        this.printStream = printStream;
        this.scanner = scanner;
    }

    @Override
    public int getNewId() {
        int id = (int) java.time.Instant.now().getEpochSecond();
        while (id == java.time.Instant.now().getEpochSecond()) {

        }
        return id;
    }

    @Override
    public void inputFromConsole(int id, Date creationDate) {
        LabWork labWork = new com.company.classes.LabWork();
        labWork.setID(id);
        printStream.println("Please input {name} {minimalPoint} {maximumPoint}");
        boolean success = false;
        do {
            try {
                String[] input = scanner.nextLine().split(" ", 3);
                if (input.length != 3) {
                    throw new IllegalArgumentException("Invalid arguments.");
                } else {
                    labWork.setName(input[0]);
                    labWork.setMinimalPoint(Integer.valueOf(input[1]));
                    labWork.setMaximumPoint(Double.parseDouble(input[2]));
                }
                success = true;
            } catch (RuntimeException e) {
                printStream.println("Invalid arguments, " + e.getMessage());
            }
        } while (!success);
        printStream.println("Please input coordinates {x} {y}");
        success = false;
        Coordinates coordinates = new com.company.classes.Coordinates();
        do {
            try {
                String[] arguments = scanner.nextLine().split(" ", 2);
                if (arguments.length != 2) {
                    throw new IllegalArgumentException("Invalid arguments.");
                } else {
                    coordinates.setX(Integer.parseInt(arguments[0]));
                    coordinates.setY(Double.parseDouble(arguments[1]));
                }
                success = true;
            }  catch (RuntimeException e) {
                printStream.println("Invalid arguments, " + e.getMessage());
            }
        } while (!success);
        labWork.setCoordinates(coordinates);
        Difficulty difficulty = null;
        printStream.println("Please input {Difficulty} from" + Arrays.toString(Difficulty.values()));
        success = false;
        do {
            try {
                String[] arguments = scanner.nextLine().split(" ", 1);
                if (arguments.length != 1) {
                    throw new IllegalArgumentException("Invalid arguments.");
                } else {
                    difficulty = Difficulty.valueOf(arguments[0]);
                }
                success = true;
            }  catch (RuntimeException e) {
                printStream.println("Invalid arguments, " + e.getMessage() + "Please input {Difficulty} from" + Arrays.toString(Difficulty.values()));
            }
        } while (!success);
        labWork.setDifficulty(difficulty);
        Discipline discipline = new com.company.classes.Discipline();
        printStream.println("Please input discipline: {name} {lecture Hours (can be null)} {practice Hours} {selfStudyHours} {labsCount}");
        success = false;
        do {
            try {
                String[] arguments = scanner.nextLine().split(" ");
                if (arguments.length != 4 && arguments.length != 5) {
                    throw new IllegalArgumentException("Invalid arguments.");
                } else {
                    if (arguments.length == 4) {
                        discipline.setName(arguments[0]);
                        discipline.setLectureHours(null);
                        discipline.setPracticeHours(Integer.parseInt(arguments[1]));
                        discipline.setSelfStudyHours(Long.parseLong(arguments[2]));
                        discipline.setLabsCount(Integer.valueOf(arguments[3]));
                    }
                    else {
                        discipline.setName(arguments[0]);
                        discipline.setLectureHours(Long.valueOf(arguments[1]));
                        discipline.setPracticeHours(Integer.parseInt(arguments[2]));
                        discipline.setSelfStudyHours(Long.parseLong(arguments[3]));
                        discipline.setLabsCount(Integer.valueOf(arguments[4]));
                    }
                }
                success = true;
            }  catch (RuntimeException e) {
                printStream.println("Invalid arguments, " + e.getMessage());
            }
        } while (!success);
        labWork.setDiscipline(discipline);
        labWork.setCreationDate(creationDate);
    }
}
