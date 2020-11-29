import java.util.ArrayList;

public abstract class Wagon{
    Treno treno = null;
    int nPassengers;
    int maxPassengers;
    int wagonId;
    ArrayList<Passeggero> passengersList = new ArrayList<>();
    ArrayList<Door> doorList = new ArrayList<>();

    public Wagon(int wagonId, int maxPassengers, Treno treno){
        this.wagonId = wagonId;
        nPassengers = 0;
        this.maxPassengers = maxPassengers;
        this.treno = treno;
        Door frontDoor = new Door();
        Door backDoor = new Door();
        doorList.add(frontDoor);
        doorList.add(backDoor);

    }

    //METODI

    public boolean closeD() {
        if(treno.statoTreno == StatoTreno.InSTAZIONE){
            for(Door door : doorList) {
                if(door.doorState == StatoPorte.GUASTE) {
                    System.out.println("Le porte sono guaste!!!");
                    return false;
                }
                if(door.doorState == StatoPorte.CHIUSE) {
                    System.out.println("Le porte sono già chiuse");
                    return false;
                }
                else
                    door.doorState = StatoPorte.CHIUSE;
            }
            System.out.println("Le porte sono state chiuse");
            return true;
        }
        else {
            System.out.println("Il treno non è in stazione: impossibile chiudere le porte");
            return false;
        }
    }

    public boolean openD() {
        if(treno.statoTreno == StatoTreno.InSTAZIONE){
            for(Door door : doorList) {
                if(door.doorState == StatoPorte.GUASTE) {
                    System.out.println("Le porte sono guaste!!!");
                    return false;
                }
                if(door.doorState == StatoPorte.APERTE) {
                    System.out.println("Le porte sono già aperte");
                    return false;
                }
                else
                    door.doorState = StatoPorte.APERTE;
            }
            System.out.println("Le porte sono state aperte");
            return true;
        }
        else {
            System.out.println("Il treno non è in stazione: impossibile aprire le porte");
            return false;
        }
    }

    public boolean enterPassenger(Passeggero passenger) {
        boolean flag = false;
        Wagon wagon = null;
        for(Wagon w : treno.wagonList){
            if(w.wagonId == passenger.wagonAssigned) {
                wagon = w;
                flag = true;
                break;
            }
        }
        if(flag) {
            if (treno.statoTreno == StatoTreno.InSTAZIONE) {
                if (passengersList.size() <= maxPassengers) {
                    passengersList.add(passenger);
                    nPassengers++;
                    passenger.train = treno;
                    System.out.println("È salito un passeggero");
                    return true;
                } else {
                    System.out.println("Massimo numero di passeggeri raggiunto");
                    return false;
                }
            } else {
                System.out.println("Il treno non è in stazione: impossibile modificare numero passeggeri");
                return false;
            }
        }
        else{
            System.out.println("Biglietto non valido: vagone assegnato non trovato");
            return false;
        }
    }
        public class Door {
        StatoPorte doorState = StatoPorte.CHIUSE;
        int maxOperation = 250;
    }

}
