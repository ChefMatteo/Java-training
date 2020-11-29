public class VagoneLetto extends Wagon {
    int nPassengers;
    int maxPassengers = 40;
    int freeBed;

    public VagoneLetto(int wagonId, FrecciaRossa frecciaRossa) {
        super(wagonId, 40, frecciaRossa);
        nPassengers = 0;
        maxPassengers = 40;
        freeBed = maxPassengers;
    }

    @Override
    public boolean openD() {
        return super.openD();
    }

    @Override
    public boolean closeD() {
        return super.closeD();
    }

    @Override
    public boolean enterPassenger(Passeggero passenger) {
        if (passenger instanceof PasseggeroAssonnato) {
            if (super.enterPassenger(passenger) == true) {
                freeBed--;
                return true;
            } else {
                return false;
            }
        } else
            return false;
    }
}
