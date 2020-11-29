public class BinarioSemaforo extends Binari {
    double redLight;

    public BinarioSemaforo(Treno train) {
        super(train);
        redLight = 3.0;
    }

    @Override
    public Binari percorri() {
        if (train instanceof TrenoRegionale) {
            System.out.println("Semaforo rosso: attendere...");
            train.brake();
            for (int i = 0; i <= redLight; i += 0.5) {
                if (i == redLight) {
                    System.out.println("Semaforo verde");
                }
            }
            train.start();
            return super.percorri();
        } else
            return super.percorri();
    }
}
