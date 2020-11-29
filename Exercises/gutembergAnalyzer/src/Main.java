import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        HashMap<String, int[]> books = new HashMap<>();
        try {
            for (File file : new File("books").listFiles()) {
                books.put(file.getName().replaceAll(".txt", ""), pool.submit(new BooksReader(new FileReader(file))).get());
            }
        } catch (InterruptedException | ExecutionException | FileNotFoundException e) {
            System.out.println("Error main");
            e.printStackTrace();
        }
            pool.shutdown();
            System.out.println((double) (System.currentTimeMillis() - startTime)/1000 + " seconds");
    }
}