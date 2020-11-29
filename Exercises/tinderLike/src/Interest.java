import java.util.UUID;

public class Interest {
    private UUID idInteresse;
    private String name;

    public Interest(String name) {
        idInteresse = UUID.randomUUID();
        this.name = name;
    }

    public UUID getIdInteresse() {
        return idInteresse;
    }

    public String getName() {
        return name;
    }
}
