package lagacy.doing.mainFiles;

import java.io.PrintStream;
import java.util.Scanner;
@Deprecated
public class Coordinates implements lagacy.Super.Coordinates {
    private int x; //Значение поля должно быть больше -497
    private double y; //Максимальное значение поля: 268
    private final Scanner scanner;
    private final PrintStream printStream;
    public Coordinates(Scanner scanner, PrintStream printStream){
        this.scanner = scanner;
        this.printStream = printStream;
    }
    @Override
    public void setCoordinates() {
        int x = -500;
        double y = 300;
        do {
            try  {
                printStream.print("Input coordinates x (min: -497) and y (max 268) with separated by a space: ");
                String input = scanner.nextLine();
                String[] array = input.split(" ");
                x = Integer.parseInt(array[0]);
                y = Double.parseDouble(array[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                printStream.println("It's not number.");
            }
        } while (x < -497 || y > 268);
        this.x = x;
        this.y = y;
    }

    @Override
    public String getCoordinatesCSV() {
        return x + ", " + y;
    }
}
