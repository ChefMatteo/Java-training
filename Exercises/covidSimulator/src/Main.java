import java.io.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        StringBuilder ids = new StringBuilder();
        try {
            FileWriter output = new FileWriter("D:/Programmi/IntelliJ/IntelliJ IDEA Community Edition 2020.2.3/Progetti/CorsoTreeJavaDeveloper2020/Exercises/covidSimulator/output.txt");
            for (File file : new File("D:/Programmi/IntelliJ/IntelliJ IDEA Community Edition 2020.2.3/Progetti/CorsoTreeJavaDeveloper2020/Exercises/covidSimulator/files").listFiles()) {
                ids.append(pool.submit(new Reader(new FileReader(file))).get());
            }
            pool.shutdown();
            new Writer(ids.toString(), output);
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println("Error main");
            e.printStackTrace();
        }
        pool.shutdown();
        System.out.println((double)(System.currentTimeMillis() - startTime)/1000 + " seconds");
    }
}
