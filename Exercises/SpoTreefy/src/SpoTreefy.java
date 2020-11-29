import java.util.*;


public class SpoTreefy {
    //singleton
    private static SpoTreefy instance = null;
    private SpoTreefy() {
    }
    public static SpoTreefy getSpoTreefy() {
        if (instance == null) {
            instance = new SpoTreefy();
        }
        return instance;
    }

    //parametri
    Scanner sc = new Scanner(System.in);
    private static HashSet<User> users = new HashSet<>();
    private static HashSet<Song> songs = new HashSet<>();
    private static HashSet<String> authors = new HashSet<>();
    private static HashSet<Playlist> playlists = new HashSet<>();

    //metodi search
    private User returnUserByEmail(String email){
        User userSearched = null;
        for (User user : users) {
            if(user.email.equals(email)) {
                userSearched = user;
                break;
            }
        }
        if(userSearched == null)
            System.out.println("USER == NULL");
        return userSearched;
    }
    private Song returnSongByTitle(String title){
        Song songSearched = null;
        for (Song song : songs) {
            if(song.title.equals(title)) {
                songSearched = song;
                break;
            }
        }
        if(songSearched == null)
            System.out.println("SONG == NULL");
        return songSearched;
    }

    //metodi menu
    public boolean addUser(String email, String name, String password){
        if(!users.contains(returnUserByEmail(email))){
            users.add(new User(email, name, password));
            System.out.println("Utente registrato con successo!");
            return true;
        }else {
            System.out.println("Email già registrata");
            return false;
        }
    }
    public boolean addSong(String title, String author, MusicType musicType){
        if(!songs.contains(returnSongByTitle(title)) && !authors.contains(author)){
            songs.add(new Song(title, author, musicType));
            authors.add(author);
            System.out.println("Canzone aggiunta e autore aggiunto!");
            return true;
        }else if(!songs.contains(returnSongByTitle(title)) && authors.contains(author)){
            songs.add(new Song(title, author, musicType));
            System.out.println("Canzone aggiunta!");
            return true;
        }else{
            System.out.println("Canzone già presente!");
            return false;
        }
    }
    public boolean signIn(String email, String password){
        if(!users.contains(returnUserByEmail(email))) {
            System.out.println("Utente non registrato!");
            return false;
        } else if(!returnUserByEmail(email).password.equals(password)){
            System.out.println("Password sbagliata!");
            return false;
        } else
            return true;
    }

    //metodi signIn
    public boolean follow(String email, String emailToFollow){
        if(!returnUserByEmail(email).usersFollowed.contains(emailToFollow)){
            returnUserByEmail(email).usersFollowed.add(emailToFollow);
            returnUserByEmail(email).playlistsFollowed.add((returnUserByEmail(emailToFollow).playlist));
            System.out.println("Followed!");
            return true;
        }else {
            System.out.println("Utente già seguito");
            return false;
        }
    }
    public boolean unFollow(String email, String emailToUnFollow){
        if(returnUserByEmail(email).usersFollowed.contains(emailToUnFollow)){
            returnUserByEmail(email).usersFollowed.remove(emailToUnFollow);
            returnUserByEmail(email).playlistsFollowed.remove(returnUserByEmail(emailToUnFollow).playlist);
            System.out.println("Unfollowed!");
            return true;
        }else {
            System.out.println("Utente non seguito!");
            return false;
        }
    }
    public boolean loadPlaylist(String email){
        if(returnUserByEmail(email).playlist != null) {
            returnUserByEmail(email).queue.clear();
            returnUserByEmail(email).queue.addAll(returnUserByEmail(email).playlist.songs);
            returnUserByEmail(email).nameOfPlaylistLoaded = returnUserByEmail(email).playlist.name;
            return true;
        }else{
            System.out.println("Playlist non presente!");
            return false;
        }
    }
    public boolean loadPlaylistFromFollowed(String email, String emailOfFollowed){
        if(returnUserByEmail(emailOfFollowed).playlist == null){
            System.out.println("L'utente non ha caricato nessuna playlist!");
            return false;
        }else if(returnUserByEmail(email).usersFollowed.contains(emailOfFollowed)){
            returnUserByEmail(email).queue.clear();
            returnUserByEmail(email).queue.addAll(returnUserByEmail(emailOfFollowed).playlist.songs);
            returnUserByEmail(email).nameOfPlaylistLoaded = returnUserByEmail(emailOfFollowed).playlist.name;
            return true;
        }else{
            System.out.println("Utente non seguito!");
            return false;
        }
    }
    public boolean addSongInPlaylist(String email, String title){
        if(returnUserByEmail(email).playlist == null) {
            System.out.println("Nessuna playlist presente\nInserire un nome per la playlist");
            String reply = sc.next().toLowerCase();
            returnUserByEmail(email).playlist = new Playlist(reply);
        }
        return returnUserByEmail(email).playlist.songs.add(returnSongByTitle(title));
    }
    public boolean removeSongInPlaylist(String email, String title){
        return returnUserByEmail(email).playlist.songs.remove(returnSongByTitle(title));
    }
    public void listenQueue(String email)throws InterruptedException {
        if(returnUserByEmail(email).queue.isEmpty())
            System.out.println("Nessuna canzone in coda!");
        else {
            String reply = "si";
            Song songPlayed = null;
            while (true) {
                if (reply.equals("si")) {
                    songPlayed = (Song) returnUserByEmail(email).queue.peek();
                    returnUserByEmail(email).queue.poll();
                    returnUserByEmail(email).songsPlayed.add(songPlayed);
                    int i = 0;
                    while (i == 5){
                        System.out.print("♩ ");
                        Thread.sleep(1000);
                        System.out.println("♫");
                        Thread.sleep(1000);
                        i++;
                    }
                    System.out.println("Vuoi riprodurre la prossima canzone?\nDigita SI o NO");
                    while (true) {
                        reply = sc.next().toLowerCase();
                        if (reply.equals("si")) {
                            break;
                        } else if (reply.equals("no"))
                            break;
                        else
                            System.out.println("Comando errato: ripetere");
                    }
                } else
                    break;
            }
        }
    }
    public void nextSong(String email){
        System.out.println("In cima alla coda c'è: " + returnUserByEmail(email).queue.peek());
    }
    public void songsListenedByUser(String email){
        int counter;
        System.out.println("L'utente ha ascoltato CANZONE per TOT VOLTE:");
        for (Song songs : returnUserByEmail(email).songsPlayed) {
            counter = 0;
            for (Song songsPlayed : returnUserByEmail(email).songsPlayed){
                if(songs.title.equals(songsPlayed.title))
                    counter++;
            }
            System.out.println(songs.title + " " + counter);
        }
    }
    public Playlist mostListenedPlaylistByUser(String email){
        Playlist mostPlaylistPlayed = null;
        ArrayList <Song> setSongCounter = null;
        int counter = 0;
        if(returnUserByEmail(email).playlist != null) {
            mostPlaylistPlayed = returnUserByEmail(email).playlist;
            setSongCounter = returnUserByEmail(email).songsPlayed;
            setSongCounter.retainAll(returnUserByEmail(email).playlist.songs);
            counter = setSongCounter.size();
        }
        for (Playlist playlist : returnUserByEmail(email).playlistsFollowed) {
            setSongCounter = returnUserByEmail(email).songsPlayed;
            setSongCounter.retainAll(playlist.songs);
            if (setSongCounter.size() > counter){
                counter = setSongCounter.size();
                mostPlaylistPlayed = playlist;
            }
        }
        System.out.println("La playlist più ascoltata è:\n" + mostPlaylistPlayed.name);
        return mostPlaylistPlayed;
    }
    public void allPlaylistListened(String email){
        ArrayList <Song> setSongCounter = null;
        int counter = 0;
        if(returnUserByEmail(email).playlist != null) {
            setSongCounter = returnUserByEmail(email).songsPlayed;
            setSongCounter.retainAll(returnUserByEmail(email).playlist.songs);
            counter = setSongCounter.size();
            System.out.println("Hai ascoltato i brani della tua playlist " + counter + " volte");
        }
        for (Playlist playlist : returnUserByEmail(email).playlistsFollowed) {
            setSongCounter = returnUserByEmail(email).songsPlayed;
            setSongCounter.retainAll(playlist.songs);
            counter = setSongCounter.size();
            System.out.println("Hai ascoltato i brani della playlist " + playlist.name + " " + counter + " volte");
        }
    }
    public void discover(String email) {
        ArrayList<Song> setSongCounter = null;
        int counter = 0;
        if (returnUserByEmail(email).playlist == null) {
            System.out.println("L'utente non ha playlist!");
        }else{
            for (Playlist playlist : returnUserByEmail(email).playlistsFollowed) {
                setSongCounter.addAll(returnUserByEmail(email).playlist.songs);
                setSongCounter.removeAll(returnUserByEmail(email).songsPlayed);
                setSongCounter.retainAll(playlist.songs);
                counter = setSongCounter.size();
                if(counter > 0) {
                    System.out.println("Nella playlist " + playlist.name + " ci sono " + counter +
                            " brani presenti nella tua playlist che non hai mai ascoltato");
                    counter = 0;
                }
            }
        }
    }
    public void reccomendation(String email, int n){
        playlists.clear();
        for (User user : users) {
            playlists.add(user.playlist);
        }
        ArrayList<Song> setSongCounter = null;
        TreeMap<Integer, HashSet<Playlist>>  playlistsReccomendated = new TreeMap<>();
        int counter = 0;
        for (Playlist playlist : playlists) {
            setSongCounter.addAll(returnUserByEmail(email).songsPlayed);
            setSongCounter.retainAll(playlist.songs);
            counter = setSongCounter.size();
            if (!playlistsReccomendated.containsKey(counter)){
                HashSet<Playlist> values = new HashSet<>();
                values.add(playlist);
                playlistsReccomendated.put(counter, values);
            }else{
                playlistsReccomendated.get(counter).add(playlist);
            }
        }
        int max = 0;
        for (Integer integer : playlistsReccomendated.keySet()) {
            if(playlistsReccomendated.get(integer).size() > 1) {
                for (Playlist playlist : playlistsReccomendated.get(integer)) {
                    max++;
                }
            }else
                max++;
        }
        int i = 0;
        ArrayList<Playlist> playlistsRaccomendatedFinal = new ArrayList<>();
        boolean flag = false;
        for (Integer integer : playlistsReccomendated.keySet()) {
            if(i == n)
                break;
            else if(i == max){
                flag = true;
                break;
            }else {
                if (playlistsReccomendated.get(integer).size() > 1) {
                    for (Playlist playlist : playlistsReccomendated.get(integer)) {
                        if (i != n) {
                            playlistsRaccomendatedFinal.add(playlist);
                            i++;
                        } else {
                            break;
                        }
                    }
                } else {
                    playlistsRaccomendatedFinal.addAll(playlistsReccomendated.get(integer));
                    i++;
                }
            }
        }
        if(!flag)
            System.out.println("Le " + n + " playlist raccomandate sono:");
        else
            System.out.println("Le " + max + " playlist raccomandate sono:");
        for (Playlist playlist : playlistsRaccomendatedFinal) {
            System.out.println(playlist.name);
        }
    }
    public void reccomendationPlus(){
    }

    //classi nidificate
    private class Song implements Comparable{
        private String title;
        private String author;
        private MusicType musicType;

        public Song(String title, String author, MusicType musicType) {
            this.title = title.toLowerCase();
            this.author = author.toLowerCase();
            this.musicType = musicType;
        }

        //getter
        public String getTitle() {
            return title;
        }
        public String getAuthor() {
            return author;
        }
        public MusicType getMusicType() {
            return musicType;
        }

        public int compareTo(Object o) {
            return 0;
        }
    }

    private class Playlist{
        private String name;
        private HashSet<Song> songs;

        protected Playlist(String name){
            this.name = name.toLowerCase();
            songs = new HashSet<>();
        }
    }

    private class User {
        private String email;
        private String nome;
        private String password;
        private Playlist playlist;
        private String nameOfPlaylistLoaded;
        private Queue queue;
        private HashSet<String> usersFollowed;
        private HashSet<Playlist> playlistsFollowed;
        private ArrayList<Song> songsPlayed;

        protected User(String email, String nome, String password) {
            this.email = email;
            this.nome = nome;
            this.password = password;
            nameOfPlaylistLoaded = "";
            queue = new PriorityQueue(50);
            playlistsFollowed = new HashSet<>();
            playlist = null;
            songsPlayed = new ArrayList<>();
        }

        //getter
        public String getEmail() {
            return email;
        }
        public String getNome() {
            return nome;
        }

        //classi nidificate

    }

}
