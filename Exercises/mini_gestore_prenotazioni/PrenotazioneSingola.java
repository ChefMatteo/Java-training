public class PrenotazioneSingola extends Prenotazione {
    Preferenza preferenza;

    public PrenotazioneSingola(String code, Preferenza preferenza) {
        super(code, 1);
        this.preferenza = preferenza;
    }


}
