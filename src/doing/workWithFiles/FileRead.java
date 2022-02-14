package doing.workWithFiles;

import Super.ReadFromFile;
import doing.HashSetHack;
import doing.mainFiles.LabWork;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class FileRead implements ReadFromFile {
    @Override
    public HashSet<LabWork> fileRead(HashSetHack hashSetHack, String nameVariable) {
        String variable = System.getenv(nameVariable);
        @SuppressWarnings("unchecked")
        HashSet<LabWork> hashSet = (HashSet<LabWork>) hashSetHack.HashSetter();
        if (variable == null) {
            throw new IllegalArgumentException("Can't find path \"" + nameVariable + "\".");
        } else {
            try (FileReader fileReader = new FileReader(variable)){

            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Can't find file in path \"" + nameVariable + "\".");
            } catch (SecurityException e) {
                throw new IllegalArgumentException("Can't access file in path \"" + nameVariable + "\".");
            } catch (IOException e) {
                throw new IllegalArgumentException("Error occurred accessing file in path \"" + nameVariable + "\".");
            }
        }
        return hashSet;
    }
}
