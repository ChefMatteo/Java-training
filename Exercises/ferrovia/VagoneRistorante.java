import java.util.ArrayList;

public class VagoneRistorante extends Wagon {
    ArrayList<Tavoli> tableList = null;
    int maxPassengers;
    FrecciaRossa frecciaRossa = null;

    public VagoneRistorante(int wagonId, FrecciaRossa frecciaRossa) {
        super(wagonId, 24, frecciaRossa);
        maxPassengers = 24;
        this.frecciaRossa = frecciaRossa;
        ArrayList<Tavoli> tableList = new ArrayList<>();
        Tavoli tavolo1 = new Tavoli();
        Tavoli tavolo2 = new Tavoli();
        Tavoli tavolo3 = new Tavoli();
        Tavoli tavolo4 = new Tavoli();
        Tavoli tavolo5 = new Tavoli();
        Tavoli tavolo6 = new Tavoli();
        tableList.add(tavolo1);
        tableList.add(tavolo2);
        tableList.add(tavolo3);
        tableList.add(tavolo4);
        tableList.add(tavolo5);
        tableList.add(tavolo6);
    }

    //CLASSI NIDIFICATE
    public class Tavoli {
        ArrayList<PasseggeroAffamato> hungryPassengersList = new ArrayList<>();
        int chairs;

        public Tavoli() {
            chairs = 4;
        }

        public boolean setAtTable(PasseggeroAffamato hungryPassenger) {
            if (hungryPassengersList.size() < chairs) {
                hungryPassengersList.add(hungryPassenger);
                return true;
            } else
                return false;
        }
    }

    //METODI
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
        Wagon wagonAssigned = null;
        boolean flagId = false;
        for (Wagon wagon : treno.wagonList) {
            if (wagon.wagonId == passenger.wagonAssigned) {
                flagId = true;
                wagonAssigned = wagon;
            }
        }
        if (flagId) {
            if (treno.statoTreno == StatoTreno.InSTAZIONE) {
                if (passenger instanceof PasseggeroAffamato) {
                    boolean flag = false;
                    for (Tavoli tavolo : tableList) {
                        tavolo.setAtTable((PasseggeroAffamato) passenger);
                        passenger.train = treno;
                        flag = true;
                        break;
                    }
                    System.out.println("È salito un passeggero");
                    nPassengers++;
                    return flag;
                } else {
                    System.out.println("Massimo numero di passeggeri raggiunto");
                    return false;
                }
            } else {
                System.out.println("Il treno non è in stazione: impossibile modificare numero passeggeri");
                return false;
            }
        } else {
            System.out.println("Biglietto non valido: vagone assegnato non trovato");
            return false;
        }
    }
}
