import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Dado {
	
	//Attributi
	private int facce;
	private int facciaAttuale;
	private int totale;
	private int[] memoriaFaccia;
	
	
	//Costruttore
	public Dado (int n) {
		this.facce = n;
		this.facciaAttuale = 1;
		this.totale = 0;
		memoriaFaccia = new int[n];
	}
	
	//Metodi
	public int getFacce() {
		return this.facce;
	}
	
	public int getFacciaAttuale() {
		return this.facciaAttuale;
	}
	
	public int nuovoTiro() {
		Random random = new Random();
		int numeroRandom;
		
		do {
			numeroRandom = random.nextInt(facce + 1);
			this.facciaAttuale = numeroRandom;
			
		} while (numeroRandom == 0);
		
		totale += facciaAttuale;
		
		memoriaFaccia[facciaAttuale - 1] += 1;
		
		return facciaAttuale;
	}
	
	private int getTotale (){
		return this.totale;
	}
	
	public int somma() {
		return this.getTotale();
	}
	
	public void tiraFinoA (int faccia) {
		int tiro;
		
		if(faccia <= this.facce && faccia > 0) {
			do {
				tiro = this.nuovoTiro();
				System.out.println("Tiro: " + tiro);
				
			} while (tiro != faccia);
			
		}
	}
	
	public void doppioTiro(Dado secondoDado) {
		int tiroDado;
		int tiroSecondoDado;
		
		do {
			tiroDado = this.nuovoTiro();
			tiroSecondoDado = secondoDado.nuovoTiro();
			System.out.println("Dado: " + tiroDado + " Secondo dado: " + tiroSecondoDado);
			
		}while( tiroDado != tiroSecondoDado);
			
	}
	
	public void statistiche() {
		
		for(int i = 0; i < memoriaFaccia.length; i++) {
			System.out.println("Faccia (" + (i+1) +") � uscita: " +memoriaFaccia[i] + " volte");
			for(int j = 0; j < memoriaFaccia[i]; j++) {
				if(memoriaFaccia[i] != 0) {
					System.out.print('*');
				}
			}
			System.out.println("\n");
		}
	}
	
}
