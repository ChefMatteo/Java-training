import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Reader implements Callable<String> {
    FileReader fileToRead;

    public Reader(FileReader fileToRead){
        this.fileToRead = fileToRead;
    }

    @Override
    public String call() {
        StringBuilder partialIds = new StringBuilder();
        try(BufferedReader br = new BufferedReader(fileToRead)){
            while(br.ready()) {
                String[] splittedLine = br.readLine().split(";");
                if (Double.parseDouble(splittedLine[1]) >= 40.0)
                    partialIds.append(splittedLine[0]+"\n");
                else if (Double.parseDouble(splittedLine[1]) >= 38.0 &&
                        Boolean.parseBoolean(splittedLine[3]) == true &&
                        Boolean.parseBoolean(splittedLine[4]) == true &&
                        Boolean.parseBoolean(splittedLine[5]) == true)
                    partialIds.append(splittedLine[0]+"\n");
                else if (splittedLine[6].equals("CRITICA") &&
                        Double.parseDouble(splittedLine[1]) >= 38.5 || (Boolean.parseBoolean(splittedLine[3]) == true ||
                        Boolean.parseBoolean(splittedLine[4]) == true ||
                        Boolean.parseBoolean(splittedLine[5]) == true))
                    partialIds.append(splittedLine[0]+"\n");
                else if (Integer.parseInt(splittedLine[2]) >= 50 && Double.parseDouble(splittedLine[1]) >= 37.0)
                    partialIds.append(splittedLine[0]+"\n");
                else if (Integer.parseInt(splittedLine[2]) >= 60 &&
                        ((Boolean.parseBoolean(splittedLine[3]) == true && splittedLine[6].equals("CAUTELA")) ||
                                (Boolean.parseBoolean(splittedLine[4]) == true && splittedLine[6].equals("CRITICA"))))
                    partialIds.append(splittedLine[0]+"\n");
            }
        }catch(IOException e){
            System.out.println("Errore reader");
            e.printStackTrace();
        }
        System.out.println("finish");
        return partialIds.toString();
    }
}
