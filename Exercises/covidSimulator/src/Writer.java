import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer{
    String idsToWrite;
    FileWriter output;

    public Writer(String idsToWrite, FileWriter output) {
        this.idsToWrite = idsToWrite;
        this.output = output;
        run();
    }

    public void run() {
        try (BufferedWriter bw = new BufferedWriter(output)) {
            bw.write(idsToWrite);
        } catch (IOException e) {
            System.out.println("errorWrite");
            e.printStackTrace();
        }
    }
}
