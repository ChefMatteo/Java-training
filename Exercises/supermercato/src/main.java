import java.util.TreeSet;

public class main {
    public static void main(String[] args) {
        TreeSet<Cliente> codaClienti = new TreeSet<>();
        codaClienti.add(new Cliente(25,"ajeje"));
        codaClienti.add(new Cliente(56,"brazorf"));
        codaClienti.add(new Cliente(85,"tizio"));
        codaClienti.add(new Cliente(30,"caio"));
        System.out.println(codaClienti.pollFirst().name);
    }
}
