package classi;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Game {
    UUID id;
    String name;
    int difficulty;

    Game(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
        id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
