public class Generatore {
    private Stato stato;

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Generatore(){
        setStato(Stato.OFF);
    }

    public void interruttore(){
        if(getStato() == Stato.OFF)
            setStato(Stato.ON);
        else
            setStato(Stato.OFF);
    }



}
