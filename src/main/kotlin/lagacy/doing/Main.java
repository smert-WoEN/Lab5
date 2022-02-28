package lagacy.doing;

import lagacy.doing.mainFiles.LabWork;
import lagacy.doing.workWithFiles.FileWrite;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static HashSetHack hashSetHack = new HashSetHack();
    static FileWrite fileWrite = new FileWrite();
    @SuppressWarnings("unchecked")
    static HashSet<LabWork> name = (HashSet<LabWork>) hashSetHack.HashSetter();
    static Scanner scanner = new Scanner(System.in);
    //static Coordinates coordinator = new lagacy.doing.mainFiles.Coordinates(scanner, System.out);
    //static Discipline discipline = new lagacy.doing.mainFiles.Discipline(scanner, System.out);
    static LabWork labWork = new LabWork(scanner, System.out);
    public static void main(String[] args) {
        //coordinator.setCoordinates();
        //discipline.setDisciplines();
        //System.out.println(coordinator.getCoordinates());
        //System.out.println(discipline.getDisciplines());
        labWork.setLab();
        name.add(labWork);
        labWork = new LabWork(scanner,System.out);
        labWork.setLab();
        name.add(labWork);
        for (LabWork a: name) {
            System.out.println(a.getLabStringCSV());
        }
    }
}
