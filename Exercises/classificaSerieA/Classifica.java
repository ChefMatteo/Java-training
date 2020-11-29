import java.util.*;
import java.lang.*;

public class Classifica {
    Squadra[] serieA = new Squadra[4];

    public Classifica(Squadra[] serieA){
        this.serieA=serieA;
    }

    public Squadra[] getSerieA() {
        return serieA;
    }

    public void setSerieA(Squadra[] serieA) {
        this.serieA = serieA;
    }

    public void esitoPartita(Squadra squadraCasa, int golCasa, Squadra squadraOspite, int golOspite){
        squadraCasa.setGolFatti(squadraCasa.getGolFatti()+golCasa);
        squadraCasa.setGolSubiti(squadraCasa.getGolSubiti()+golOspite);
        squadraOspite.setGolFatti(squadraOspite.getGolFatti()+golOspite);
        squadraOspite.setGolSubiti(squadraOspite.getGolSubiti()+golCasa);
        if(golCasa > golOspite){
            squadraCasa.setPunteggio(squadraCasa.getPunteggio()+2);
            System.out.println("Ha vinto " + squadraCasa.getNome() + "! (in casa+2)");
        }
        else if (golOspite > golCasa){
            squadraOspite.setPunteggio(squadraOspite.getPunteggio()+3);
            System.out.println("Ha vinto " + squadraOspite.getNome() + "! (fuori casa+3)");
        }
        else
           System.out.println("Pareggio!");
    }

    public void getClassifica(){

        Arrays.sort(serieA, new SortByScore());

        for(int i = 0; i< serieA.length; i++)
            System.out.println("N°" + (i+1) + " " + serieA[(serieA.length-1)-i].getNome() + " con " + serieA[(serieA.length-1)-i].getPunteggio() + " punti");

    }

    public void getMigliorAttacco() {
        int contatore = 0;
        int contatorePariPunti = 1;
        Squadra squadraContatore = new Squadra();
        for(int i = 0; i < serieA.length; i++){
            if(serieA[i].getGolFatti() > contatore){
                contatore = serieA[i].getGolFatti();
                squadraContatore = serieA[i];
            }
        }
        for(int i = 0; i<serieA.length; i++){
            if(serieA[i].getGolFatti() == squadraContatore.getGolFatti() && serieA[i].getNome() != squadraContatore.getNome()){
                contatorePariPunti++;
            }
        }
        Squadra[] squadrePariPunti = new Squadra[contatorePariPunti];
        contatorePariPunti = 1;
        squadrePariPunti[0] = squadraContatore;
        for (int i =0; i< serieA.length; i++){
            if(serieA[i].getGolFatti() == squadraContatore.getGolFatti() && serieA[i].getNome() != squadraContatore.getNome()){
                squadrePariPunti[contatorePariPunti] = serieA[i];
                contatorePariPunti++;
            }
        }
        if(contatorePariPunti == 1){
            System.out.println("La squadra che ha fatto più gol è: " + squadraContatore.getNome());
        }
        else {
            System.out.println("La squadra che ha fatto più gol è: ");
            System.out.println(squadraContatore.getNome() + "(gol: " + squadraContatore.getGolFatti() + ")");
            System.out.println(" a pari numeri con: ");
            for (int i = 1; i < squadrePariPunti.length; i++)
                System.out.println(squadrePariPunti[i].getNome());
        }

    }

    public void getPeggiorDifesa(){
        int contatore = 0;
        int contatorePariPunti = 1;
        Squadra squadraContatore = new Squadra();
        for(int i = 0; i < serieA.length; i++){
            if(serieA[i].getGolSubiti() > contatore){
                contatore = serieA[i].getGolSubiti();
                squadraContatore = serieA[i];
            }
        }
        for(int i = 0; i<serieA.length; i++){
            if(serieA[i].getGolSubiti() == squadraContatore.getGolSubiti() && serieA[i].getNome() != squadraContatore.getNome()){
                contatorePariPunti++;
            }
        }
        Squadra[] squadrePariPunti = new Squadra[contatorePariPunti];
        contatorePariPunti = 1;
        squadrePariPunti[0] = squadraContatore;
        for (int i =0; i< serieA.length; i++){
            if(serieA[i].getGolSubiti() == squadraContatore.getGolSubiti() && serieA[i].getNome() != squadraContatore.getNome()){
                squadrePariPunti[contatorePariPunti] = serieA[i];
                contatorePariPunti++;
            }
        }
        if(contatorePariPunti == 1){
            System.out.println("La squadra che ha preso più gol è: " + squadraContatore.getNome());
        }
        else {
            System.out.println("La squadra che ha preso più gol è: ");
            System.out.println(squadraContatore.getNome() + "(gol subiti: " + squadraContatore.getGolSubiti() + ")");
            System.out.println(" a pari numeri con: ");
            for (int i = 1; i < squadrePariPunti.length; i++)
                System.out.println(squadrePariPunti[i].getNome());
        }

    }

}
