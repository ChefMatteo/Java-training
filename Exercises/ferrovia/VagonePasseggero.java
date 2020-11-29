public class VagonePasseggero extends Wagon {
    int maxPassengers;
    Treno treno = null;

    public VagonePasseggero(int wagonId, Treno treno) {
        super(wagonId, 50, treno);
        maxPassengers = 50;
        this.treno = treno;
    }

    @Override
    public boolean enterPassenger(Passeggero passenger) {
        if (passenger instanceof PasseggeroSemplice ||
                passenger instanceof PasseggeroAssonnato ||
                passenger instanceof PasseggeroAffamato)
            return super.enterPassenger(passenger);
        else
            return false;
    }

    @Override
    public boolean openD() {
        return super.openD();
    }

    @Override
    public boolean closeD() {
        return super.closeD();
    }
}
