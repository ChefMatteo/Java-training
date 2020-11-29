import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class BooksReader implements Callable<int[]> {
FileReader bookToRead;

    public BooksReader(FileReader bookToRead) {
        this.bookToRead = bookToRead;
    }

    @Override
    public int[] call(){
        int[] arrayToReturn = new int [26];
        try(BufferedReader br = new BufferedReader(bookToRead)){
            while(br.ready()){
                int content = br.read();
                if(content >= 65 && content <= 90)
                   arrayToReturn[content-65]++;
               else if(content >= 97 && content <= 122)
                    arrayToReturn[content-97]++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (int i : arrayToReturn) {
            count += i;
        }
        System.out.println("poolThreadFinished" + count);
        return arrayToReturn;
    }
}
