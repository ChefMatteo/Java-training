import java.time.Instant;

public class User implements Comparable<User>{
    private String name;
    private String surname;
    private Instant registredDate;

    //costruttore
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        registredDate = Instant.now();
    }

    //getter
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Instant getRegistredDate() {
        return registredDate;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int compareTo(User user) {
        return this.registredDate.compareTo(user.registredDate);
    }

}
