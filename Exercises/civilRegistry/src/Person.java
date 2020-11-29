import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Person {
    String name;
    String surname;
    int age;
    private String address;
    UUID id;
    HashSet<Person> sons;

    public Person(String name, String surname, int age, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.address = address;
        id = UUID.randomUUID();
        sons = new HashSet<>();
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
