package classi;

import java.util.Objects;
import java.util.UUID;

public class Player {
    String nickname;
    UUID id;

     Player(String nickname) {
        this.nickname = nickname;
        id = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
