public class Lampadina {
    private Stato stato;
    static Generatore generatore;

    public int getClickMax() {
        return clickMax;
    }

    public void setClickMax(int clickMax) {
        this.clickMax = clickMax;
    }

    private int clickMax;

    public Lampadina(int clickMax, Generatore generatore){
        this.generatore = generatore;
        stato = Stato.OFF;
        this.clickMax = clickMax;
    }

    public Stato getStato(){
        return stato;
    }
    public void setStato(Stato stato) {
        if(generatore.getStato() == Stato.ON)
        this.stato = stato;
        else
            System.out.println("La corrente è assente!");
    }

}
