import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ordine extends Entità {
    int idUtente;
    ArrayList<Prodotto> listaProdotti;
    LocalDateTime data;

    public Ordine(int idUtente) {
        this.idUtente = idUtente;
        listaProdotti = new ArrayList<>();
        data = LocalDateTime.now();
    }
}
