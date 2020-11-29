import java.util.*;

public class TinderLike {
    private static HashMap<User, HashSet<Interest>> usersAndInterests = new HashMap<>();
    private static HashSet<Interest> interestList = new HashSet<>();
    TreeSet<User> userTreeSet = new TreeSet<>();
    //getter
    public static Set<User> getUsersList() {
        return usersAndInterests.keySet();
    }
/*
    public void getInteresse(){
        for (User user : listaUtenti.keySet()) {
            System.out.println(listaUtenti.get(user).size());
        }
    }
*/

    public static void setInterest(Interest interest) {
        interestList.add(interest);
    }

    //metodi
    public Interest getInterestByName(String name) {
        Interest interestByName = null;
        for (Interest interest : interestList) {
            if (interest.getName().equals(name)) {
                interestByName = interest;
                break;
            }
        }
        return interestByName;
    }

    public User getUserByEmail(String email) {
        User userByEmail = null;
        for (User user : usersAndInterests.keySet()) {
            if (user.getEmail().equals(email)) {
                userByEmail = user;
                break;
            }
        }
        return userByEmail;
    }

    public boolean addUser(User user) {
        if (!usersAndInterests.containsKey(user)) {
            userTreeSet.add(user);
            usersAndInterests.put(user, new HashSet<>());
            return true;
        } else {
            System.out.println("Utente già registrato");
            return false;
        }
    }

    public static boolean eraseUser(String email, String password) {
        boolean flag = false;
        for (User user : getUsersList()) {
            if (user.getEmail().equals(email)) {
                if (user.compareTo(password)) {
                    usersAndInterests.remove(user);
                    return true;
                } else
                    System.out.println("Password sbagliata!");
            } else
                System.out.println("Email non presente!");
        }
        return flag;
    }

    public boolean addInterest(String email, Interest interest) {
        boolean flag = false;
        if (!interestList.contains(interest))//controllo se interest è presente nella lista altrimenti lo aggiungo
            interestList.add(interest);
        for (User user : usersAndInterests.keySet()) {
            if (user.getEmail().equals(email)) {
                usersAndInterests.get(user).add(interest);
                flag = true;
                user.setNumberInterests(+1);
                break;
            }
        }
        return flag;
    }

    public User similarUser(User user) {
        User userCounter = null;
        int counter = 0;
        for (User utente : usersAndInterests.keySet()) {
            HashSet<Interest> intersezione = new HashSet<>(usersAndInterests.get(utente));
            intersezione.retainAll(usersAndInterests.get(user));
            if (intersezione.size() > counter && !utente.equals(user)) {
                userCounter = utente;
                counter = intersezione.size();
            }
        }
        return userCounter;
    }


    public HashMap<User, User> similarUsers(){
        return null;
    }

}
