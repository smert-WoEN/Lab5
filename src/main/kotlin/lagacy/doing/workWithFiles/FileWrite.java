package lagacy.doing.workWithFiles;

import lagacy.Super.WriteToFile;
import lagacy.Super.LabWork;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
@Deprecated

public class FileWrite implements WriteToFile {

    @Override
    public void fileWrite(HashSet<LabWork> hashSet, String nameVariable) {
        String variable = System.getenv(nameVariable);
        if (variable == null) {
            throw new IllegalArgumentException("Can't find path \"" + nameVariable + "\".");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(variable))) {
                for (LabWork labWork : hashSet) {
                    writer.write(labWork.getLabStringCSV() + "\n");
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
