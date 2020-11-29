import java.util.HashSet;
import java.util.Objects;

public class User implements Comparable<User> {
    private int numberInterests;
    private String password;
    private String email;
    public String name;

    //costruttore
    public User(String name, String email, String password) {
        numberInterests = 0;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    //getter
    public String getEmail() {
        return email;
    }
    public int getNumberInterests() {
        return numberInterests;
    }

    //set
    public void setNumberInterests(int numberInterests) {
        this.numberInterests = numberInterests;
    }

    //metodi
    public boolean compareTo(String password){
        if(this.password.equals(password))
            return true;
        else
            return false;
    }//Ritorna true se la password in input coincide

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public int compareTo(User u) {
        return Integer.compare(this.numberInterests, u.numberInterests);
    }

    @Override
    public String toString() {
        return name;
    }
}
