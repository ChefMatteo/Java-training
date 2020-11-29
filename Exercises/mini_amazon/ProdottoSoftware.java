public class ProdottoSoftware extends Prodotto {
    byte dimensione;
    String versione;

    public ProdottoSoftware(String nome, double prezzo, byte dimensione, String versione) {
        super(nome, prezzo);
        this.dimensione = dimensione;
        this.versione = versione;
    }
}
