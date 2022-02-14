package doing;

import Super.Coordinates;
import Super.Discipline;
import Super.LabWorks;
import doing.mainFiles.LabWork;
import doing.workWithFiles.FileWrite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static HashSetHack hashSetHack = new HashSetHack();
    static FileWrite fileWrite = new FileWrite();
    @SuppressWarnings("unchecked")
    static HashSet<LabWork> name = (HashSet<LabWork>) hashSetHack.HashSetter();
    static Scanner scanner = new Scanner(System.in);
    //static Coordinates coordinator = new doing.mainFiles.Coordinates(scanner, System.out);
    //static Discipline discipline = new doing.mainFiles.Discipline(scanner, System.out);
    static LabWork labWork = new LabWork(scanner, System.out, hashSetHack);
    public static void main(String[] args) {
        //coordinator.setCoordinates();
        //discipline.setDisciplines();
        //System.out.println(coordinator.getCoordinates());
        //System.out.println(discipline.getDisciplines());
        labWork.setLab();
        name.add(labWork);
        labWork = new LabWork(scanner,System.out, hashSetHack);
        labWork.setLab();
        name.add(labWork);
        for (LabWork a: name) {
            System.out.println(a.getLab());
        }
    }
}
