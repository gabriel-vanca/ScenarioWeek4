import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Map map = new Map();
        try {
            map.ReadFile();
            map.LoadMapDataFromLine(2);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
