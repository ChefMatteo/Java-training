public class Autocarro extends Veicolo{
    int quintaliCapacità;

    public Autocarro(String targa, int marca, int modello){
        super(targa, marca, modello);
        int numeroPosti = 5;
        int quintaliCapacità = 3;
        this.quintaliCapacità = quintaliCapacità;
    }

    public String toString(){
        return super.toString() + quintaliCapacità;
    }
}
