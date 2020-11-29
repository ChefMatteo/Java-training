import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Anagrafe {
    HashSet<Person> listOfPerson = new HashSet<>();

    public Person searchPersonById(UUID id){
        return listOfPerson.stream()
                .filter(x -> x.id == id)
                .findFirst().get();
    }

    public Boolean addPerson(Person person){
        return listOfPerson.add(person);
    }

    public Boolean removePerson(Person person){
        return listOfPerson.remove(person);
    }

    public List<Person> returnPersonByName(String name){
        return listOfPerson.stream()
                .filter(x->x.name.startsWith(name))
                .collect(Collectors.toList());
    }

    public List<Person> agestPeople(){
        return listOfPerson.stream()
                .sorted((x,y)->Integer.compare(y.age, x.age))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<String> peopleAddress(String name){
        return listOfPerson.stream()
                .filter(x->x.name.equals(name))
                .map(Person::getAddress)
                .collect(Collectors.toList());
    }

    public List<Person> getSons(String name){
        return listOfPerson.stream()
                .filter(x->x.name.startsWith(name))
                .flatMap(x->x.sons.stream())
                .collect(Collectors.toList());
    }

}
