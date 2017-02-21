package FileIO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Gabriel on 21/02/2017.
 */
public class InputReader {

    private static InputReader instance = null;

    private ArrayList<String> fileData;

    private InputReader()
    {
        readFile();
    }

    public static InputReader getInstance()
    {
        if(instance == null)
            instance = new InputReader();
        return instance;
    }

    private void readFile() {
        FileReader file = null;
        try {
            file = new FileReader("./files/robots.mat");
            BufferedReader fileReader = new BufferedReader(file);
            fileData = new ArrayList<>();
            String line;

            while ((line = fileReader.readLine()) != null) {
                fileData.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GetLine(int line)
    {
        return fileData.get(line);
    }
}
