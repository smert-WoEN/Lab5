package lagacy.doing.mainFiles;

import lagacy.Super.*;
import lagacy.Super.Coordinates;
import lagacy.Super.Discipline;

import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;
@Deprecated

public class LabWork implements lagacy.Super.LabWork {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private double maximumPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private final Discipline discipline; //Поле не может быть null
    private final Scanner scanner;
    private final PrintStream printStream;


    public LabWork(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.coordinates = new lagacy.doing.mainFiles.Coordinates(scanner, printStream);
        this.discipline = new lagacy.doing.mainFiles.Discipline(scanner, printStream);
    }

    @Override
    public String getLabStringCSV() {
        return id + ", " + name + ", " + coordinates.getCoordinatesCSV() + ", " + creationDate + ", " + minimalPoint + ", " + maximumPoint + ", " + discipline.getDisciplinesCSV();
    }

    @Override
    public void setLab() {
        int id = (int) java.time.Instant.now().getEpochSecond();
        while (id == java.time.Instant.now().getEpochSecond()){

        }
        this.id = id;

        coordinates.setCoordinates();
        creationDate = new Date();
        Integer minimalPoint = -1;
        do {
            try {
                printStream.print("Input non-negative minimalPoint: ");
                String input = scanner.nextLine();
                minimalPoint = new Integer(input);
            } catch (NumberFormatException e) {
                printStream.println("It's not number. Try again");
            }
        } while (minimalPoint <= 0);
        this.minimalPoint = minimalPoint;
        double maximumPoint = -1;
        do {
            try {
                printStream.print("Input non-negative maximumPoint: ");
                String input = scanner.nextLine();
                maximumPoint = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                printStream.println("It's not number. Try again");
            }
        } while (maximumPoint <= 0);
        this.maximumPoint = maximumPoint;
        discipline.setDisciplines();

    }

    @Override
    public void setName() {
        String name = "";
        do {
            try {
                printStream.print("Input name LabWork: ");
                name = scanner.nextLine();
                if (name.length() == 0) {
                    printStream.println("input empty string, try again.");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } while (name.length() == 0);
        this.name = name;
    }
}
