public class DittaRiparazioni {
    int index = 0;
    Riparazione[] listaRiparazioni;

    public Riparazione newRiparazione(String address, Tecnico tecnico, int priority, boolean assigned){
        Riparazione riparazione = new Riparazione(address, priority, assigned);
        tecnico.status = StatusTecnico.OCCUPATO;
        listaRiparazioni = new Riparazione[index];
        listaRiparazioni[index] = riparazione;
        index++;
        return riparazione;
    }

    public Riparazione[] getLista(){
        for(int i =0; i < listaRiparazioni.length; i++)
            if(i == listaRiparazioni.length-1)
                System.out.print(listaRiparazioni[i].address + " Done = " + listaRiparazioni[i].done);
            else
                System.out.print(listaRiparazioni[i].address + " Done = " + listaRiparazioni[i].done + "; ");
        return listaRiparazioni;
    }

    public Riparazione riparazioneAltaPriorità(){
        Riparazione riparazioneAltaPriorità = new Riparazione();
        for(int i = 0; i < listaRiparazioni.length; i++){
            if(listaRiparazioni[i].priority > riparazioneAltaPriorità.priority)
                riparazioneAltaPriorità = listaRiparazioni[i];
        }
        return riparazioneAltaPriorità;
    }

    public void concludi(Riparazione riparazioneDaConcludere){

    }
}
