package doing.workWithFiles;

import Super.WriteToFile;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class FileWrite implements WriteToFile {

    @Override
    public void fileWrite(HashSet<HashSet<String>> hashSet, String nameVariable) {
        String variable = System.getenv(nameVariable);
        if (variable == null) {
            throw new IllegalArgumentException("Can't find path \"" + nameVariable + "\".");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(variable))) {
                for (HashSet<String> hashSet1 : hashSet) {
                    int lenHashSet1 = hashSet1.size();
                    int count = 0;
                    System.out.println(lenHashSet1);
                    for (String string : hashSet1) {
                        count += 1;
                        writer.write(string + ((count == lenHashSet1) ? " " : ", "));
                    }
                    writer.write("\n");
                }
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Can't find file in path \"" + nameVariable + "\".");
            } catch (SecurityException e) {
                throw new IllegalArgumentException("Can't access file in path \"" + nameVariable + "\".");
            } catch (IOException e) {
                throw new IllegalArgumentException("Error occurred accessing file in path \"" + nameVariable + "\".");
            }
        }
    }
}
