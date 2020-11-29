public class Automobile extends Veicolo{
    int numeroPorte;

    public Automobile(String targa, int marca, int modello){
        super(targa, marca, modello);
        int numeroPosti = 5;
        int numeroPorte = 3;
        this.numeroPorte = numeroPorte;
    }

    public String toString(){
        return super.toString() + numeroPorte;
    }
}
