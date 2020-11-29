public class Cliente implements Comparable{
    int age;
    String name;

    public Cliente(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        return -Integer.compare(age, ((Cliente) o).age);
    }
}
