public class Main {
    public static void main(String[] args) {
        GestorePrenotazioni gestorePrenotazioni = new GestorePrenotazioni(3, 5);
        Prenotazione p1 = new PrenotazioneSingola("12", Preferenza.ESTERNO);
        Prenotazione p2 = new PrenotazioneSingola("23", Preferenza.ESTERNO);
        Prenotazione p3 = new PrenotazioneSingola("34", Preferenza.INTERNO);
        Prenotazione p4 = new PrenotazioneSingola("56", Preferenza.ESTERNO);
        gestorePrenotazioni.prenota(p1);
        gestorePrenotazioni.prenota(p2);
        gestorePrenotazioni.prenota(p3);
        gestorePrenotazioni.prenota(p4);

        Prenotazione[] prenotazioniInternoArray = gestorePrenotazioni.prenotazioniAttualiInterno();
        Prenotazione[] prenotazioniEsternoArray = gestorePrenotazioni.prenotazioniAttualiEsterno();
        int prenotazioniInterno = 0, prenotazioniEsterno = 0;
//contiamo e togliamo i null se presenti
        for (int i = 0; i < prenotazioniInternoArray.length; i++)
            if (prenotazioniInternoArray[i] != null) {
                prenotazioniInterno++;
            }
        for (int i = 0; i < prenotazioniEsternoArray.length; i++)
            if (prenotazioniEsternoArray[i] != null) {
                prenotazioniEsterno++;
            }
        System.out.println(prenotazioniInterno == 1);
        System.out.println(prenotazioniEsterno == 3);
        Prenotazione p5 = new PrenotazioneGruppo("45", 2);
        boolean a  = gestorePrenotazioni.prenota(p5);
        prenotazioniInterno = 0;
        prenotazioniEsterno = 0;
//contiamo e togliamo i null se presenti
        for (int i = 0; i < prenotazioniInternoArray.length; i++)
            if (prenotazioniInternoArray[i] != null) {
                prenotazioniInterno++;
            }
        for (int i = 0; i < prenotazioniEsternoArray.length; i++)
            if (prenotazioniEsternoArray[i] != null) {
                prenotazioniEsterno++;
            }
        System.out.println(prenotazioniInterno + prenotazioniEsterno == 5);
//verifichiamo i posti effettivamente riservati
        int postiTotali = 0;
        for (int i = 0; i < prenotazioniInternoArray.length; i++)
            if (prenotazioniInternoArray[i] != null) {
                postiTotali += prenotazioniInternoArray[i].getnPosti();
            }
        for (int i = 0; i < prenotazioniEsternoArray.length; i++)
            if (prenotazioniEsternoArray[i] != null) {
                postiTotali += prenotazioniEsternoArray[i].getnPosti();
            }
        System.out.println(postiTotali == 6);
        Prenotazione p6 = new PrenotazioneSingola("67", Preferenza.ESTERNO);
        boolean inserita = gestorePrenotazioni.prenota(p6);
        System.out.println(inserita);
    }
}
