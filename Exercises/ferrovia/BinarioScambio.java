public class BinarioScambio extends Binari {
    BinarioScambio[] exchangeBinary = null;
    BinarioScambio counter = null;

    public BinarioScambio(Treno train) {
        super(train);
        BinarioScambio firstBinary = new BinarioScambio(train);
        BinarioScambio secondBinary = new BinarioScambio(train);
        BinarioScambio[] exchangeBinary = {firstBinary, secondBinary};
        this.exchangeBinary = exchangeBinary;
    }

    public void change() {
        counter = exchangeBinary[0];
        exchangeBinary[0] = exchangeBinary[1];
        exchangeBinary[1] = counter;
        counter = null;
    }

    @Override
    public Binari percorri() {
        return super.percorri();
    }
}
