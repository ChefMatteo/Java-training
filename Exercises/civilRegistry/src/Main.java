public class Main {
    public static void main(String[] args) {
        Anagrafe anagrafe = new Anagrafe();

        Person person1 = new Person("Ajeje", "Brazorf", 25, "Roma");
        anagrafe.addPerson(person1);

        Person person2 = new Person("Ajeje", "canes", 65, "tbn");
        anagrafe.addPerson(person2);

        person1.sons.add(new Person("Ajeje", "ozarck", 33, "seseciao"));
        person1.sons.add(new Person("Giacomo", "fazo", 25, "Roma"));
        person2.sons.add(new Person("mr", "adolf", 40, "Roma"));

        System.out.println(anagrafe.getSons("Ajeje"));
    }
}
