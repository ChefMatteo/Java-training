package classi;

import java.util.UUID;

public class Partita {
    Player player;
    Game game;
    int score;
    UUID id;

    public Partita(Player player, Game game, int score) {
        this.player = player;
        this.game = game;
        this.score = score;
        id = UUID.randomUUID();
    }

    public int getScore() {
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return player.nickname + ": " + game.name + " " + score;
    }
}
