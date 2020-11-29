public class GestorePrenotazioni {
    private int postiInterni;
    private int postiEsterni;
    private Prenotazione [] sedutiEsterni;
    private Prenotazione [] sedutiInterni;
    private Prenotazione [] inAttesaInterni;
    private Prenotazione [] inAttesaEsterni;

    public GestorePrenotazioni(int postiInterni, int postiEsterni) {
        inAttesaEsterni = new Prenotazione[postiEsterni];
        inAttesaInterni = new Prenotazione[postiInterni];
        sedutiInterni = new Prenotazione[postiInterni];
        sedutiEsterni = new Prenotazione[postiEsterni];
        this.postiInterni = postiInterni;
        this.postiEsterni = postiEsterni;
    }

    public int getPostiInterni() {
        return postiInterni;
    }

    public void setPostiInterni(int postiInterni) {
        this.postiInterni = postiInterni;
    }

    public int getPostiEsterni() {
        return postiEsterni;
    }

    public void setPostiEsterni(int postiEsterni) {
        this.postiEsterni = postiEsterni;
    }

     /*
     prenota: data una Prenotazione p in input, se c’è posto nel ristorante viene accettata restituendo true
     (dando precedenza alla preferenza indicata), altrimenti viene rifiutata restituendo false

     terminaPrenotazione: data una Prenotazione p in input,
     termina la prenotazione eliminandola e liberando i posti associati.

     PLUS🏍: Se presenti prenotazioni singole la cui preferenza può ora essere soddisfatta in virtù dei nuovi posti liberi,
     modificare la prenotazione di conseguenza.
     Esempio: ci sono 2 posti disponibili all’esterno e 2 prenotazioni p1 e p2,
     entrambe da 2 persone all’esterno; nel momento in cui viene terminata la prenotazione p1, se p2 è ancora in corso,
     va spostata all’esterno.

     prenotazioniAttualiEsterno: ritorna un array con le attuali prenotazioni per l’esterno del ristorante

     prenotazioniAttualiInterno: ritorna un array con le attuali prenotazioni per l’interno del ristorante
     */

    public boolean prenota(Prenotazione p){

        boolean flag = false;
        if(p instanceof PrenotazioneSingola){
            if(((PrenotazioneSingola) p).preferenza == Preferenza.INTERNO && postiInterni>1) {
                for(int i = 0; i< sedutiInterni.length;i++){
                    if(sedutiInterni[i] == null){
                        sedutiInterni[i] = p;
                        postiInterni -= p.postiDaPrenotare;
                        flag = true;
                        break;
                    }
                }
            }
            if(((PrenotazioneSingola) p).preferenza == Preferenza.ESTERNO && postiEsterni>1) {
                for(int i = 0; i< sedutiEsterni.length; i++){
                    if(sedutiEsterni[i] == null){
                        sedutiEsterni[i] = p;
                        postiEsterni -= p.postiDaPrenotare;
                        flag = true;
                        break;
                    }
                }
            }
            //PLUS
            if((((PrenotazioneSingola) p).preferenza == Preferenza.INTERNO && postiInterni==0 && postiEsterni > 1)){
                for(int i = 0; i< sedutiEsterni.length; i++){
                    if(sedutiEsterni[i] == null){
                        sedutiEsterni[i] = p;
                        postiEsterni -= p.postiDaPrenotare;
                        flag = true;
                        break;
                    }
                }
                for(int i = 0; i<inAttesaEsterni.length; i++){
                    if(inAttesaEsterni[i] == null) {
                        inAttesaEsterni[i] = p;
                        break;
                    }
                }
            }
            if((((PrenotazioneSingola) p).preferenza == Preferenza.ESTERNO && postiEsterni==0 && postiInterni > 1)){
                for(int i = 0; i< sedutiInterni.length; i++){
                    if(sedutiInterni[i] == null){
                        sedutiInterni[i] = p;
                        postiInterni -= p.postiDaPrenotare;
                        flag = true;
                        break;
                    }
                }
                for(int i = 0; i<inAttesaInterni.length; i++){
                    if(inAttesaInterni[i] == null) {
                        inAttesaInterni[i] = p;
                        break;
                    }
                }
            }
        }

        if(p instanceof PrenotazioneGruppo){
            if(((PrenotazioneGruppo)p).postiDaPrenotare <= postiInterni){
                for(int i = 0; i< sedutiInterni.length;i++){
                    if(sedutiInterni[i] == null){
                        sedutiInterni[i] = p;
                        postiInterni -= p.postiDaPrenotare;
                        flag = true;
                        break;
                    }
                }
            }
            if(((PrenotazioneGruppo)p).postiDaPrenotare > postiInterni && ((PrenotazioneGruppo)p).postiDaPrenotare <= postiEsterni){
                for(int i = 0; i< sedutiInterni.length;i++){
                    if(sedutiEsterni[i] == null){
                        sedutiEsterni[i] = p;
                        postiEsterni -= p.postiDaPrenotare;
                        flag = true;
                        break;
                    }
                }
            }

        }
            return flag;
    }

    public void terminaPrenotazione(Prenotazione p){
        boolean flag = false;
        for(int i=0; i< sedutiInterni.length; i++){
            if(p.hashCode() == sedutiInterni[i].hashCode()){
                //PLUS
                for(int j =0; j<inAttesaEsterni.length; j++){
                    if(inAttesaEsterni[j] != null && p.postiDaPrenotare >= inAttesaEsterni[j].postiDaPrenotare){
                        sedutiInterni[i] = inAttesaEsterni[j];
                        inAttesaEsterni[j] = null;
                        flag = true;
                        break;
                    }
                    else{
                        sedutiInterni[i] = null;
                        flag = true;
                        break;
                    }
                }
            }
        }
        if(!flag){
            for(int i = 0; i < sedutiEsterni.length; i++){
                if(p.hashCode() == sedutiEsterni[i].hashCode()){
                    //PLUS
                    for(int j =0; j<inAttesaInterni.length; j++){
                        if(inAttesaInterni[j] != null && p.postiDaPrenotare >= inAttesaInterni[j].postiDaPrenotare){
                            sedutiEsterni[i] = inAttesaInterni[j];
                            inAttesaInterni[j] = null;
                            break;
                        }
                        else{
                            sedutiEsterni[i] = null;
                            break;
                        }
                    }
                }
            }
        }
    }

    public Prenotazione [] prenotazioniAttualiEsterno(){
        return sedutiEsterni;
    }

    public Prenotazione [] prenotazioniAttualiInterno(){
        return sedutiInterni;
    }

}
