public class ProdottoFisico extends Prodotto {
    int peso;
    int volume;
    int quantitàDisponibile;

    public ProdottoFisico(String nome, double prezzo, int peso, int volume, int quantitàDisponibile) {
        super(nome, prezzo);
        this.peso = peso;
        this.volume = volume;
        this.quantitàDisponibile = quantitàDisponibile;
    }

}
