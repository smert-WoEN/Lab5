package lagacy.doing.mainFiles;

import java.io.PrintStream;
import java.util.Scanner;
@Deprecated

public class Discipline implements lagacy.Super.Discipline {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long lectureHours; //Поле может быть null
    private int practiceHours;
    private long selfStudyHours;
    private Integer labsCount; //Поле не может быть null
    private final Scanner scanner;
    private final PrintStream printStream;
    public Discipline(Scanner scanner, PrintStream printStream) {
        this.scanner = scanner;
        this.printStream = printStream;
    }

    @Override
    public void setDisciplines() {
        String name = "";
        do {
            try {
                printStream.print("Input name discipline: ");
                name = scanner.nextLine();
                if (name.length() == 0) {
                    printStream.println("input empty string, try again.");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        } while (name.length() == 0);
        this.name = name;
        Long lectureHours = -1L;
        do {
            try {
                printStream.print("Input non-negative count hours discipline: ");
                String input = scanner.nextLine();
                lectureHours = new Long(input);
            } catch (NumberFormatException e) {
                printStream.println("It's not number.");
            }
        } while (lectureHours <= 0);
        this.lectureHours = lectureHours;
        int practiceHours = -1;
        do {
            try {
                printStream.print("Input non-negative count practiceHours discipline: ");
                String input = scanner.nextLine();
                practiceHours = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printStream.println("It's not number. Try again");
            }
        } while (practiceHours <= 0);
        this.practiceHours = practiceHours;
        int selfStudyHours = -1;
        do {
            try {
                printStream.print("Input non-negative count selfStudyHours discipline: ");
                String input = scanner.nextLine();
                selfStudyHours = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printStream.println("It's not number. Try again");
            }
        } while (selfStudyHours <= 0);
        this.selfStudyHours = selfStudyHours;
        Integer labsCount = -1;
        do {
            try {
                printStream.print("Input non-negative count labsCount discipline: ");
                String input = scanner.nextLine();
                labsCount = new Integer(input);
            } catch (NumberFormatException e) {
                printStream.println("It's not number. Try again");
            }
        } while (labsCount <= 0);
        this.labsCount = labsCount;
    }

    @Override
    public String getDisciplinesCSV() {
        return name + ", " + lectureHours + ", " + practiceHours + ", " + selfStudyHours + ", " + labsCount;
    }
}
