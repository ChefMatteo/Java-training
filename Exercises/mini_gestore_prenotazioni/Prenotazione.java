import java.util.Objects;

public abstract class Prenotazione {
    String code;
    int postiDaPrenotare;

    public Prenotazione(String code, int postiDaPrenotare) {
        this.code = code;
        this.postiDaPrenotare = postiDaPrenotare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public int getnPosti() {
        return postiDaPrenotare;
    }
}
