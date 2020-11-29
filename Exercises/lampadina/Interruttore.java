public class Interruttore {
    private Lampadina lampadinaCollegata;

    public Interruttore(Lampadina lampadina){
        this.lampadinaCollegata = lampadina;
    }

    public void click() {
        switch (lampadinaCollegata.getStato()) {
            case OFF:
                lampadinaCollegata.setStato(Stato.ON);
                lampadinaCollegata.setClickMax(lampadinaCollegata.getClickMax()-1);
                break;
            case ON:
                lampadinaCollegata.setStato(Stato.OFF);
                lampadinaCollegata.setClickMax(lampadinaCollegata.getClickMax()-1);
                break;
            case BRK:
                System.out.println("Lampadina rotta!");
                break;
        }
        if (lampadinaCollegata.getClickMax() == 0) {
            lampadinaCollegata.setStato(Stato.BRK);
            System.out.println(" Si è rotta la lampadina!");
        }
    }
}
