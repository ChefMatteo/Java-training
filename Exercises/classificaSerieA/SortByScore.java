import java.util.*;

public class SortByScore implements Comparator<Squadra> {

    public int compare(Squadra a, Squadra b)
    {
        return a.getPunteggio() - b.getPunteggio();
    }
}
