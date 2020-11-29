import java.util.Arrays;

public class DistributoreDiBevande {
    /*


    scegliProdotto: dato in ingresso un codice di prodotto, restituisca il prodotto associato a quel numero
    (se l’importo inserito lo consente) e decrementi il saldo disponibile nel distributore

    */

    private int quantity = 0;
    private int caffèQuantity = 0;
    private int cappuccinoQuantity = 0;
    private double ammount = 0;
    private Bevanda[] bevandeCaricate = new Bevanda[2];

    public double getAmmount() {
        return ammount;
    }

    public boolean setAmmount(double ammount) {
        if(ammount == 0 || ammount == 0.1 || ammount == 0.2 || ammount == 0.5 || ammount == 1.0 || ammount == 2.0) {
            this.ammount = ammount;
            if(ammount == 2.0)
                System.out.println("Sono stati inseriti 2 euro");
            else if(ammount == 1.0)
                System.out.println("È stato inserito 1 euro");
            else
                System.out.println("Sono stati inseriti " + (int)(ammount*100) + " centesimi");
            return true;
        }
        else{
            System.out.println("Moneta non valida.");
            return false;
        }
    }

    public int getCaffèQuantity() {
        return caffèQuantity;
    }

    public boolean setCaffèQuantity(int caffèQuantity) {
        if(quantity == 30){
            System.out.println("Distributore pieno, impossibile caricare altri prodotti.");
            return false;
        }
        if(this.caffèQuantity == 15) {
            System.out.println("Serbatoio pieno, impossibile caricare altro caffè.");
            return false;
        }
        else {
            this.caffèQuantity = caffèQuantity;
            return true;
        }
    }

    public int getCappuccinoQuantity() {
        return cappuccinoQuantity;
    }

    public boolean setCappuccinoQuantity(int cappuccinoQuantity) {
        if(quantity == 30){
            System.out.println("Distributore pieno, impossibile caricare altri prodotti.");
            return false;
        }
        if(this.cappuccinoQuantity == 15) {
            System.out.println("Serbatoio pieno, impossibile caricare altro cappuccino.");
            return false;
        }
        else {
            this.cappuccinoQuantity = caffèQuantity;
            return true;
        }
    }

    public void caricaProdotto(Bevanda bevanda){
        quantity++;
        if (bevanda instanceof Caffè){
            setCaffèQuantity(getCaffèQuantity()+1);
            bevandeCaricate[0] = bevanda;
        }
        if (bevanda instanceof Cappuccino){
            setCappuccinoQuantity(getCappuccinoQuantity()+1);
            bevandeCaricate[1] = bevanda;
        }
    }

    public Bevanda scegliProdotto(String code) {
        Bevanda bevanda = null;
        for (int i = 0; i < bevandeCaricate.length; i++) {
            if (code.equals(bevandeCaricate[i].getCode())) {
                if (ammount >= bevandeCaricate[i].getPrice()) {
                    if(bevandeCaricate[i].getPrice() == 1.0)
                        System.out.println("Sto pagando un euro per un " + bevandeCaricate[i].getCode());
                    else if(bevandeCaricate[i].getPrice() == 2.0)
                        System.out.println("Sto pagando due euro per un " + bevandeCaricate[i].getCode());
                    else
                        System.out.println("Sto pagando " + (int)(bevandeCaricate[i].getPrice()*100) +" centesimi per un " + bevandeCaricate[i].getCode());
                    ammount -= bevandeCaricate[i].getPrice();
                    bevanda = bevandeCaricate[i];
                } else {
                    System.out.println("Denaro insufficiente per " + code);
                    bevanda = null;
                }
            }
        }
        return bevanda;
    }



    public double saldoAttuale(){
        return ammount;
    }

    public void inserisciImporto(double n){
        setAmmount(getAmmount() + n);
    }

    public double getResto(){
        double resto = getAmmount();
        ammount = 0;
        if(resto == 1.0)
            System.out.println("Si prega di ritirare il resto di un euro");
        else if(resto > 1.0)
            System.out.println("Si prega di ritirare il resto di " + (int)resto + " euro");
        else if(resto == 0)
            System.out.println("Resto non disponibile");
        else
            System.out.println("Si prega di ritirare il resto di " + (int)(resto*100) + " centesimi");
        return resto;
    }

}
