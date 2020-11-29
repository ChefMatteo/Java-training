public class Main {
    public static void main(String[] args) {
        Giocatore pg1 = new Giocatore(1, "ajeje", "brazorf");
        Giocatore pg2 = new Giocatore(2, "ajeje2", "brazorf3");
        Giocatore pg3 = new Giocatore(3, "ajeje2", "brazorf3");
        Squadra inter = new Squadra(1, "inter", pg1, pg2, pg3);
        Squadra giappo = new Squadra(2, "giappo", pg1, pg2, pg3);
        Squadra fana = new Squadra(3, "fana", pg1, pg2, pg3);
        Squadra ita = new Squadra(4, "ita", pg1, pg2, pg3);
        Squadra[] serieA = new Squadra[]{inter, giappo, fana, ita};
        Classifica classificaSerieA = new Classifica(serieA);

        classificaSerieA.esitoPartita(giappo, 0, inter,3);
        classificaSerieA.esitoPartita(fana, 0, inter,3);
        classificaSerieA.esitoPartita(giappo, 0, fana,3);
        classificaSerieA.esitoPartita(ita, 0, fana,3);
        classificaSerieA.getClassifica();
        classificaSerieA.getMigliorAttacco();
        classificaSerieA.getPeggiorDifesa();
    }
}
