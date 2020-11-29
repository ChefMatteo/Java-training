import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LettersCount {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Felix");
        names.add("Filippo");
        names.add("Francesco");
        names.add("giovanni");
        names.add("maria");
        names.add("salvatore");
        names.add("junior");
        names.add("mario");
        names.add("Fausto");
        names.add("giuseppe");
        names.add("Fortunato");

        //metodo imperativo
        int counter = 0;
        for (String name : names) {
            if(name.toLowerCase().startsWith("f")){
                counter += name.length();
                System.out.println(name);
            }
        }
        System.out.println(counter);

        //metodo stream
        counter = 0;
        int finalCounter = counter;
        counter = names.stream()
                .filter(x -> x.toLowerCase().startsWith("f"))
                .mapToInt(a -> Integer.sum(0, a.length()))
                .sum();
        System.out.println(counter);

    }

}
