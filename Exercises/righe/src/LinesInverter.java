import java.io.*;
import java.util.ArrayList;

public class LinesInverter {

    public void linesInverter(String path){
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            while(br.ready()){
                String line = br.readLine();
                lines.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path+"INVERTED.txt"))){
            for (int i = lines.size() - 1; i >= 0; i--) {
                    bw.write(lines.get(i) + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class Main {

    public static void main(String[] args) {
        LinesInverter linesInverter = new LinesInverter();
        linesInverter.linesInverter("D:/Programmi/IntelliJ/IntelliJ IDEA Community Edition 2020.2.3/Progetti/CorsoTreeJavaDeveloper2020/Exercises/righe/righe.txt");
    }
}