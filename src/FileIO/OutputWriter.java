package FileIO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Gabriel on 23/02/2017.
 */
public class OutputWriter {

    private static OutputWriter instance = null;
    private PrintWriter fileWriter;

    private OutputWriter() {
        try {
            fileWriter = new PrintWriter("./files/output_"
                    +
                    ThreadLocalRandom.current().nextInt(10, Integer.MAX_VALUE)
                    + ".txt");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static OutputWriter GetInstance()
    {
        if(instance == null)
            instance = new OutputWriter();
        return instance;
    }

    public void writeToFile(String s) {
        try {
            fileWriter.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CloseWriter()
    {
        fileWriter.close();
    }
}
