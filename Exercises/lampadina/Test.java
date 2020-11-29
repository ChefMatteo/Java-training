public class Test {
    public static void main(String[] args) {
        Generatore generatore = new Generatore();
        Lampadina lampadina = new Lampadina(4, generatore);
        generatore.interruttore();
        generatore.interruttore();


        Interruttore interruttore = new Interruttore(lampadina);
        interruttore.click();
        Interruttore interruttore2 = new Interruttore(lampadina);
        interruttore2.click();
        interruttore.click();
        interruttore.click();






    }
}
