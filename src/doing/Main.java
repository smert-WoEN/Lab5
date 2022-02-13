package doing;

import doing.workWithFiles.FileWrite;
import java.util.HashSet;

public class Main {
    static HashSetHack hashSetHack = new HashSetHack();
    static FileWrite fileWrite = new FileWrite();
    static HashSet<HashSet<String>> name = null;
    public static void main(String[] args) {
        name = (HashSet<HashSet<String>>) hashSetHack.HashSetter();
        HashSet<String> ntw = (HashSet<String>) hashSetHack.HashSetter();
        ntw.add("5");
        name.add((HashSet<String>) ntw.clone());
        System.out.println(ntw);
        System.out.println(name);
        System.out.println(ntw);
        ntw.add("1");
        System.out.println(name);
        System.out.println(ntw);
        name.add((HashSet<String>) ntw.clone());
        System.out.println(name);
        fileWrite.fileWrite(name, "JavaOut");
        System.out.println(name.getClass().getName());

    }
}
