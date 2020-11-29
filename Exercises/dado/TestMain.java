import java.util.HashMap;
import java.util.Map;

public class TestMain {

	public static void main(String[] args) {
		  Dado d = new Dado(6);
	        System.out.println(d.getFacce() == 6 ? "OK" : "ERROR");;
	        System.out.println(d.getFacciaAttuale() == 1 ? "OK" : "ERROR");
	        d.tiraFinoA(5);
	        System.out.println(d.getFacciaAttuale() == 5 ? "OK" : "ERROR");
	 
	 
	        d = new Dado(6);
	 
	        int sum = 0;
	        for (int i = 0; i < 10; i++) {
	            d.nuovoTiro();
	            sum += d.getFacciaAttuale();
	        }
	        
	        System.out.println(d.somma() == sum ? "OK" : "ERROR");
	 
	        Dado d2 = new Dado(6);
	        d.doppioTiro(d2);
	 
	        System.out.println(d.getFacciaAttuale() == d2.getFacciaAttuale() ? "OK" : "ERROR");
	 
	 
	        d = new Dado(6);
	        Map<Integer, Integer> h = new HashMap<>();
	        
	        for (int i = 1; i <= 6; i++) {
	            h.put(i, 0);
	        }
	 
	        for (int i = 0; i < 50; i++) {
	            d.nuovoTiro();
	            h.put(d.getFacciaAttuale(), h.get(d.getFacciaAttuale())+1);
	        }
	        System.out.println(h);
	        d.statistiche();
	        
	}

}
