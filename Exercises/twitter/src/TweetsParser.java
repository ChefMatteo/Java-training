import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TweetsParser {

    public void stiCazzi() throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("D:/Programmi/IntelliJ/IntelliJ IDEA Community Edition 2020.2.3/Progetti/CorsoTreeJavaDeveloper2020/Exercises/twitter/realdonaldtrump.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] stringArray = line.split(",");
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}





class main {

    public static void main(String[] args) {
        TweetsParser tweetsParser = new TweetsParser();
//        tweetsParser.stiCazzi();
    }
}