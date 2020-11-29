import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapFromFile {
    HashMap<String, ArrayList<String>> mapFromFile = new HashMap<>();

    public void mappingFromFile(String path){
        ArrayList<String> arrayToClean = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            while(br.ready()){
                String line = br.readLine();
                wordsCleaner(line, mapFromFile);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void wordsCleaner(String lineToClean, HashMap<String, ArrayList<String>> mappingFromFile){
        //trova la chiave
        String[] keyAndValues = lineToClean.split(":");
        String key = keyAndValues[0];
        //trova le values
        String[] valuesToClean = keyAndValues[1].split(",");
        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < valuesToClean.length; i++) {
            values.add(valuesToClean[i].replaceAll("[^A-Za-z]","").toLowerCase());
        }
        mappingFromFile.put(key, values);
    }

}

class Main{
    public static void main(String[] args) {
        MapFromFile mapFromFile = new MapFromFile();
        mapFromFile.mappingFromFile("D:/Programmi/IntelliJ/IntelliJ IDEA Community Edition 2020.2.3/Progetti/CorsoTreeJavaDeveloper2020/Exercises/righe2/src/righe2.txt");
        System.out.println(mapFromFile.mapFromFile);
    }
}
