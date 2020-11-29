import java.util.ArrayList;
import java.util.UUID;

public abstract class Treno implements Operazioni{
    UUID code;
    int speed;
    int maxWagons;
    int maxSpeed;
    boolean arrived;
    Binari binary = null;
    StatoTreno statoTreno;
    ArrayList<Wagon> wagonList = new ArrayList<>();
    ArrayList<Binari> binaryList = new ArrayList<>();



    public Treno(int maxSpeed, int maxWagons, ArrayList<Binari> binaryList) {
        code = UUID.randomUUID();
        speed = 0;
        this.maxWagons = maxWagons;
        this.maxSpeed = maxSpeed;
        wagonList.stream().limit(maxWagons);
        binary = binaryList.get(0);
    }

    @Override
    public void start() {
        //ciclo che aumenta la velocità
        if(!arrived) {
            System.out.println("Il treno è ripartito...");
            for (int i = 0; i <= maxSpeed; i += 20) {
                System.out.println("Il treno sta accellerando...");
                speed = i;
            }
            statoTreno = StatoTreno.InVIAGGIO;
            System.out.println("Il treno sta viaggiando a " + speed + "km/h");
        }
        else
            System.out.println("Treno arrivato al capolinea: impossibile ripartire");
    }

    @Override
    public void brake() {
        //ciclo che diminuisce la velocità
        for(int i = speed; i>=0; i-=20){
            System.out.println("Il treno sta frenando");
            speed = i;
        }
        if(speed < 0)
            speed =0;
        statoTreno = StatoTreno.FERMO;
        System.out.println("Il treno si è fermato");
    }

    @Override
    public void enterStation() {
        System.out.println("Il treno sta entrando in stazione");
        brake();
        statoTreno = StatoTreno.InSTAZIONE;
    }

    @Override
    public boolean addW(Wagon wagon, Treno treno) {
        if(treno.statoTreno == StatoTreno.InSTAZIONE) {
            if (treno.wagonList.size() < treno.maxWagons){
                if(wagon instanceof VagonePasseggero) {
                    treno.wagonList.add(wagon);
                    System.out.println("È stato aggiunto un vagone passeggero");
                }
                if(wagon instanceof VagoneLetto && treno instanceof FrecciaRossa) {
                    treno.wagonList.add(wagon);
                    System.out.println("È stato aggiunto un vagone letto");
                }
                if(wagon instanceof VagoneRistorante && treno instanceof FrecciaRossa) {
                    treno.wagonList.add(wagon);
                    System.out.println("È stato aggiunto un vagone ristorante");
                }
                return true;
            }
            else {
                System.out.println("Massimo numero di vagoni raggiunto");
                return false;
            }
        }
        else {
            System.out.println("Il treno non è in stazione: impossibile modificare numero vagoni");
            return false;
        }
    }

    @Override
    public boolean removeW(Wagon wagon, Treno treno) {
        if(treno.statoTreno == StatoTreno.InSTAZIONE) {
            boolean flag = false;
            for (Wagon elemento : treno.wagonList) {
                if (elemento.nPassengers == 0 && elemento.equals(wagon)) {
                    System.out.println("È stato rimosso un vagone");
                    flag = wagonList.remove(elemento);
                } else
                    System.out.println("C'è un passeggero nel vagone: impossibile rimuovere il vagone");
                return flag;
            }
            return flag;
        }
        else {
            System.out.println("Il treno non è in stazione: impossibile modificare numero vagoni");
            return false;
        }
    }

}
