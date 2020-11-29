public class BinarioStazione extends Binari {
    int idStation;

    public BinarioStazione(Treno train, int idStation) {
        super(train);
        this.idStation = idStation;
    }

    @Override
    public Binari percorri() {
        return super.percorri();
    }
}
