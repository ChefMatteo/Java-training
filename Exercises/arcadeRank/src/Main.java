import classi.*;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Salagiochi salagiochi = new Salagiochi();
        Player pussykiller = salagiochi.addPlayer("pussykiller");
        Game ww3 = salagiochi.addGame("ww3", 5);
        salagiochi.newPartita(pussykiller, ww3, 200);
        salagiochi.newPartita(pussykiller, ww3, 500);
        salagiochi.newPartita(pussykiller, ww3, 400);
        salagiochi.newPartita(pussykiller, ww3, 900);
        salagiochi.newPartita(pussykiller, ww3, 4);

        salagiochi.classificaVideogame(ww3);
    }
}
