import java.util.*;

public class Facebook {
    private static TreeSet<User> users = new TreeSet<>();
    private static HashSet<Post> posts = new HashSet<>();
    private static HashMap<User, HashSet<User>> friendships = new HashMap<>();
    private static HashMap<User, ArrayList<Post>> usersPosts = new HashMap<>();
    private static HashMap<User, ArrayList<Post>> usersComments = new HashMap<>();

    //Singleton
    private static Facebook instance = null;
    private Facebook() {
    }
    public static Facebook getFacebook() {
        if (instance == null) {
            instance = new Facebook();
        }
        return instance;
    }

    //getter
    public TreeSet<User> getUsers() {
        return users;
    }
    public HashSet<Post> getPosts() {
        return posts;
    }

    //setter
    public boolean addUser(User user){
        return users.add(user);
    }
    public boolean addPost(User user, String body, int n){
        userSignedIn(user);
        Post post = new Post(body, n);
        if(!usersPosts.containsKey(user)) {
            ArrayList<Post> postsOfUser = new ArrayList<>();
            postsOfUser.add(post);
            posts.add(post);
            usersPosts.put(user,postsOfUser);
        }else {
            posts.add(post);
            usersPosts.get(user).add(post);
        }
        System.out.println("Post aggiunto!");
        return true;
    }

    //metodi
    public Post searchPostById(int n){
        Post postToSearch = null;
        for (Post post : posts) {
            if(post.id == n) {
                postToSearch = post;
                break;
            }
        }
        return postToSearch;
    }
    public boolean userSignedIn(User user){
        Scanner s = new Scanner(System.in);
        boolean flag = false;
        if(!Facebook.getFacebook().getUsers().contains(user)){
            System.out.println("Utente " + user.getName() + " non registrato, vuoi registralo?\ndigita SI o NO");
            while(true) {
                String input = s.next().toLowerCase();
                if (input.equals("si")) {
                    Facebook.getFacebook().addUser(user);
                    return true;
                } else if (input.equals("no"))
                    return false;
                else
                    System.out.println("digitazione errata, ripetere il comando");
            }
        }
        return true;
    }
    public boolean newFriendship(User user1, User user2){
        //controllo se sono registrati
        userSignedIn(user1);
        userSignedIn(user2);
        //controllo se user1 ha almeno un'amicizia
        if(!friendships.containsKey(user1)){
            HashSet<User> friendsOf = new HashSet<>();
            friendsOf.add(user2);
            friendships.put(user1, friendsOf);
        //controllo se user1 e user2 sono già amici
        }else if(!friendships.get(user1).contains(user2))
            friendships.get(user1).add(user2);
        else{
            System.out.println("Gli utenti sono già amici!");
            return false;
        }
        //controllo se user2 ha almeno un'amicizia
        if(!friendships.containsKey(user2)){
            HashSet<User> friendsOf = new HashSet<>();
            friendsOf.add(user1);
            friendships.put(user2, friendsOf);
        }else{
            friendships.get(user2).add(user1);
        }
        System.out.println(user1.getName() + " e " + user2.getName() + " ora sono amici!");
        return true;
    }
    public boolean removeFriendship(User user1, User user2){
        //controllo se user1 ha almeno un'amicizia
        if(!friendships.containsKey(user1)) {
            System.out.println(user1 + " non ha amici!");
            return false;
        }
            //controllo se user1 e user2 sono amici
        else if(!friendships.get(user1).contains(user2)) {
            System.out.println(user1 + " e " + user2 + " non sono amici!");
            return false;
        } else{
            Scanner sc = new Scanner(System.in);
            System.out.println("Sei sicuro di voler rimuovere l'amicizia?\nDigita SI o NO");
            while(true) {
                String s = sc.next().toLowerCase();
                if (s.equals("si")) {
                    friendships.get(user1).remove(user2);
                    friendships.get(user2).remove(user1);
                    System.out.println("Amicizia rimossa!");
                    break;
                } else if (s.equals("no"))
                    return false;
                else
                    System.out.println("Digitazione errata: ripetere comando\nDigita SI o NO");
            }
        }
        return true;
    }
    public ArrayList<Post> postOfUser(User user){
        return usersPosts.get(user);
    }
    public User[] last3Users(){
        User[] last3Users = new User[3];
        int i = 0;
        for (User user : users) {
            if(i<3){
                last3Users[i]=user;
                System.out.println(last3Users[i].getName());
                i++;
            }
            else
                break;
        }
        return last3Users;
    }
    public ArrayList<String> commentsOfUser(User user){
        ArrayList<String> commentsOfUser = new ArrayList<>();
        for (Post post : posts) {
            if(post.comments.containsKey(user)){
                for (String s : post.comments.get(user)) {
                    commentsOfUser.add(s + " COMMENTO AL POST: " + post.body);
                }
            }
        }

        return commentsOfUser;
    }

    //classi nidificate
    protected class Post {
        int id;
        String body;
        HashSet<User> likes;
        HashMap<User, ArrayList<String>> comments;

        //costruttore
        public Post(String body, int n) {
            id = n;
            likes = new HashSet<>();
            comments = new HashMap<>();
            this.body = body;
        }

        //metodi
        public boolean addLike(User user){
            Facebook.getFacebook().userSignedIn(user);
            if(!likes.contains(user)) {
                likes.add(user);
                return true;
            }
            else {
                System.out.println("Like già inserito!");
                return false;
            }
        }
        public boolean addComment(User user, String body){
            Facebook.getFacebook().userSignedIn(user);
            if(comments.containsKey(user))
                comments.get(user).add(body);
            else {
                ArrayList<String> bodies = new ArrayList<>();
                bodies.add(body);
                comments.put(user, bodies);
            }
            return true;
        }
    }
}

