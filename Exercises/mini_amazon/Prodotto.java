public abstract class Prodotto extends Entità {
    String nome;
    double prezzo;

    protected Prodotto(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

}
