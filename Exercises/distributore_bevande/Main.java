public class Main {
    public static void main(String[] args) {
        Caffè caffè = new Caffè("caffe", 0.5);
        Cappuccino cappuccino = new Cappuccino("cappuccino", 1);
        DistributoreDiBevande distributoreDiBevande = new DistributoreDiBevande();
        distributoreDiBevande.caricaProdotto(caffè);
        distributoreDiBevande.caricaProdotto(caffè);
        distributoreDiBevande.caricaProdotto(cappuccino);
        distributoreDiBevande.caricaProdotto(caffè);
        distributoreDiBevande.caricaProdotto(caffè);
        distributoreDiBevande.caricaProdotto(cappuccino);
        distributoreDiBevande.caricaProdotto(caffè);
        System.out.println((distributoreDiBevande.saldoAttuale() == 0) + "*");
        System.out.println((distributoreDiBevande.getResto() == 0) + "*2");
        distributoreDiBevande.inserisciImporto(0.5);
        System.out.println((distributoreDiBevande.scegliProdotto("caffe") == null) + "*3");
        distributoreDiBevande.inserisciImporto(0.2);
        System.out.println((distributoreDiBevande.scegliProdotto("caffe") != null) + "*4");
        System.out.println((distributoreDiBevande.getResto()-0.10 < 1E-6) + "*5");
        System.out.println((distributoreDiBevande.saldoAttuale() == 0) + "*6");
        System.out.println((distributoreDiBevande.scegliProdotto("caffe") == null) + "*7");
        distributoreDiBevande.inserisciImporto(2.0);
        System.out.println((distributoreDiBevande.scegliProdotto("caffe") != null) + "*8");
        System.out.println((distributoreDiBevande.scegliProdotto("cappuccino") != null) + "*9");
        System.out.println((distributoreDiBevande.scegliProdotto("caffe") != null) + "*10");
        System.out.println((distributoreDiBevande.saldoAttuale() == 0) + "*11");
        System.out.println((distributoreDiBevande.getResto()-0.10 < 1E-6) + "*12");

    }
}
