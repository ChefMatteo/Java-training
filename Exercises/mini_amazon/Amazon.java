import java.util.ArrayList;
import java.util.Random;

public class Amazon{
    //SINGLETON
    Random r;
    ArrayList<Magazzino> listaMagazzini;
    ArrayList<SoftwareRepository> listaSoftwareRepository;
    ArrayList<Utente> listaUtenti;
    ArrayList<Ordine> listaOrdini;
    ArrayList<Integer> listaId;

    private static Amazon amazon = new Amazon();
    private Amazon(){
        r = new Random();
        listaMagazzini = new ArrayList<>();
        listaSoftwareRepository = new ArrayList<>();
        listaUtenti = new ArrayList<>();
        listaOrdini = new ArrayList<>();
        listaId = new ArrayList<>();
    }
    public static Amazon getInstance(){
        return amazon;
    }

    //SET ID CASUALE UNIVOCO
    public static int setId(){
        int id;
        do {
            id = amazon.r.nextInt(Integer.MAX_VALUE);
        }while(amazon.listaId.contains(id));
        return id;
    }

    //METODI
    /*ritorna true se è fisico e false se è software*/
    public boolean foundType(int idProdotto){
        boolean flag = false;
        for(Magazzino magazzino : Amazon.getInstance().listaMagazzini){
            for(Prodotto prodotto : magazzino.getListaPacchi()){
                if(prodotto.id == idProdotto)
                    return true;
            }
        }
        return flag;
    }

    public boolean registrazione(Utente utente){
        Amazon.getInstance().listaUtenti.add(utente);
        System.out.println("utente registrato = " + utente.nome);
        return true;
    }

    public boolean aggiungiProdotto(Prodotto prodottoDaAggiungere){
        if(prodottoDaAggiungere instanceof ProdottoSoftware){
            Amazon.getInstance().listaSoftwareRepository.get(0).listaSoftware.add((ProdottoSoftware)prodottoDaAggiungere);
            return true;
        }
        else if(prodottoDaAggiungere instanceof ProdottoFisico){
            int counter = Integer.MAX_VALUE;
            int nMagazzino = 0;
            for(Magazzino magazzino : Amazon.getInstance().listaMagazzini){
                if(counter > magazzino.sizeListaPacchi()){
                    counter = magazzino.sizeListaPacchi();
                    nMagazzino = Amazon.getInstance().listaMagazzini.indexOf(magazzino);
                }
            }
            Amazon.getInstance().listaMagazzini.get(nMagazzino).addPack((ProdottoFisico) prodottoDaAggiungere);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean nuovoOrdine(int idUtente, ArrayList<Integer> listaProdottiDaAcquistare){
        Ordine ordine = new Ordine(idUtente);
        for(Magazzino magazzino : Amazon.getInstance().listaMagazzini){
            for(int i = 0; i < listaProdottiDaAcquistare.size(); i++){
                for(int j =0; j < magazzino.getListaPacchi().size(); j++) {
                    if (listaProdottiDaAcquistare.get(i) == magazzino.getListaPacchi().get(j).id) {
                        ordine.listaProdotti.add(magazzino.getListaPacchi().get(j));
                    }
                }
            }
        }
        for(SoftwareRepository softwareRepository : Amazon.getInstance().listaSoftwareRepository){
            for(int i = 0; i < listaProdottiDaAcquistare.size(); i++){
                for(int j =0; j < softwareRepository.listaSoftware.size(); j++) {
                    if (listaProdottiDaAcquistare.get(i) == softwareRepository.listaSoftware.get(j).id) {
                        ordine.listaProdotti.add(softwareRepository.listaSoftware.get(j));
                    }
                }
            }
        }
        Amazon.getInstance().listaOrdini.add(ordine);
        return true;
    }

    public ArrayList<Ordine> ordiniUtente(int idUtente){
        ArrayList<Ordine> ordiniUtente = new ArrayList<>();
        ArrayList<Double> totale = new ArrayList<>();
        double somma = 0;
        double sommaTotale = 0;
        for(Ordine ordine : Amazon.getInstance().listaOrdini){
            if(ordine.idUtente == idUtente){
                ordiniUtente.add(ordine);
            }
        }
        for(Ordine ordine : ordiniUtente){
            for(Prodotto prodotto : ordine.listaProdotti){
                somma += prodotto.prezzo;
                totale.add(prodotto.prezzo);
            }
            System.out.println("Costo ordine: " + somma + "€");
            sommaTotale += somma;
            somma = 0;
        }
        System.out.println("Per un totale di: " + sommaTotale + "€");
        return ordiniUtente;
    }

    public Entità doveSiTrova(int idProdotto){
        boolean flag = foundType(idProdotto);
        int counterBigger = 0;
        int counter = 0;
        int index = 0;
        if(flag) {
            for (int i = 0; i < Amazon.getInstance().listaMagazzini.size(); i++) {
                for (Prodotto prodotto : Amazon.getInstance().listaMagazzini.get(i).getListaPacchi()) {
                    if (prodotto.id == idProdotto) {
                        counter++;
                        if (counter > counterBigger)
                            index = i;
                    }
                    counterBigger = counter;
                    counter = 0;
                }
            }
            return Amazon.getInstance().listaMagazzini.get(index);
        }
        else{
            for (int i = 0; i < Amazon.getInstance().listaSoftwareRepository.size(); i++) {
                for (Prodotto prodotto : Amazon.getInstance().listaSoftwareRepository.get(i).listaSoftware) {
                    if (prodotto.id == idProdotto) {
                        counter++;
                        if (counter > counterBigger)
                            index = i;
                    }
                    counterBigger = counter;
                    counter = 0;
                }
            }
            return Amazon.getInstance().listaSoftwareRepository.get(index);
        }
    }

    public void quantitàAcquistate(int idProdotto){
        boolean flag = foundType(idProdotto);
        int count = 0;
        for(Ordine ordine : Amazon.getInstance().listaOrdini){
            for(Prodotto prodotto : ordine.listaProdotti){
                if(prodotto.id == idProdotto)
                    count++;
            }
        }
        if(flag)
            System.out.println("Il prodotto è stato venduto " + count + " volte");
        else
            System.out.println("Il software è stato scaricato " + count + " volte");
    }




}
