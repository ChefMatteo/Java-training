public abstract class Veicolo {
    String targa;
    int marca;
    int modello;
    int numeroPosti;

    public Veicolo(String targa, int marca, int modello) {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
    }

    public String toString(){
        return targa + " : ";
    }
}
