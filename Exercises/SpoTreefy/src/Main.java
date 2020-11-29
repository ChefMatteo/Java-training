import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static void actionMenu(int instruction) throws InterruptedException {
        if (instruction == 1) {
            System.out.println("addUser\nInserisci il nome");
            String name = sc.next().toLowerCase();
            System.out.println("Inserisci l'email");
            String email = sc.next().toLowerCase();
            System.out.println("Inserisci la password");
            String password = sc.next().toLowerCase();
            SpoTreefy.getSpoTreefy().addUser(email, name, password);
        } else if (instruction == 2) {
            System.out.println("addSong\nInserisci il titolo");
            String title = sc.next().toLowerCase();
            System.out.println("Inserisci il nome dell'autore");
            String author = sc.next().toLowerCase();
            System.out.println("Inserisci il genere musicale\n1 per rock; 2 per rap; 3 per pop");
            int reply = Integer.parseInt(sc.next());
            MusicType musicType = null;
            while (true) {
                if (reply == 1) {
                    musicType = MusicType.ROCK;
                    break;
                } else if (reply == 2) {
                    musicType = MusicType.RAP;
                    break;
                } else if (reply == 3) {
                    musicType = MusicType.POP;
                    break;
                } else
                    System.out.println("Comando errato: ripetere");
            }
            SpoTreefy.getSpoTreefy().addSong(title, author, musicType);
        } else if (instruction == 3) {
            System.out.println("Inserisci l'email");
            String email = sc.next().toLowerCase();
            System.out.println("Inserisci la password");
            String password = sc.next().toLowerCase();
            if(SpoTreefy.getSpoTreefy().signIn(email, password)) {
                actionSignedIn(20, email);
                whileSignedIn(email);
            }
        } else if (instruction == 4) {
            commandsMenu();
        }
    }
        private static void actionSignedIn(int instruction, String email) throws InterruptedException {
            if (instruction == 1) {
                System.out.println("follow\nInserisci l'email da followare");
                String emailToFollow = sc.next().toLowerCase();
                SpoTreefy.getSpoTreefy().follow(email, emailToFollow);
            } else if (instruction == 2) {
                System.out.println("follow\nInserisci l'email da unfolloware");
                String emailToUnfollow = sc.next().toLowerCase();
                SpoTreefy.getSpoTreefy().unFollow(email, emailToUnfollow);
            } else if (instruction == 3) {
                System.out.println("loadPlaylist");
                SpoTreefy.getSpoTreefy().loadPlaylist(email);
            } else if (instruction == 4) {
                System.out.println("loadPlaylistFromFollowed\nInserisci l'email del proprietario della playlist");
                String emailToFollow = sc.next().toLowerCase();
                SpoTreefy.getSpoTreefy().loadPlaylistFromFollowed(email, emailToFollow);
            } else if (instruction == 5) {
                System.out.println("addSongInPlaylist\nInserisci il titolo");
                String title = sc.next().toLowerCase();
                SpoTreefy.getSpoTreefy().addSongInPlaylist(email, title);
            } else if (instruction == 6) {
                System.out.println("removeSongInPlaylist\nInserisci il titolo");
                String title = sc.next().toLowerCase();
                SpoTreefy.getSpoTreefy().removeSongInPlaylist(email, title);
            } else if (instruction == 7) {
                System.out.println("listenQueue");
                SpoTreefy.getSpoTreefy().listenQueue(email);
            } else if (instruction == 8) {
                System.out.println("nextSong");
                SpoTreefy.getSpoTreefy().nextSong(email);
            } else if (instruction == 9) {
                System.out.println("songsListenedByUser");
                SpoTreefy.getSpoTreefy().songsListenedByUser(email);
            } else if (instruction == 10) {
                System.out.println("mostListenedPlaylistByUser");
                SpoTreefy.getSpoTreefy().mostListenedPlaylistByUser(email);
            } else if (instruction == 11) {
                System.out.println("allPlaylistListened");
                SpoTreefy.getSpoTreefy().allPlaylistListened(email);
            } else if (instruction == 12) {
                System.out.println("discover");
                SpoTreefy.getSpoTreefy().discover(email);
            } else if (instruction == 13) {
                System.out.println("reccomendation\nInserisci quante playlist rilevanti vuoi conoscere");
                int reply = Integer.parseInt(sc.next());
                SpoTreefy.getSpoTreefy().reccomendation(email, reply);

            } else if (instruction == 20) {
                commandsSignedIn();
            } else if (instruction == 21) {
                actionMenu(20);
            }

        }
    static void commandsMenu() {
        System.out.println("\n        menu methods\n" +
                "        1 for addUser (String name, String email, String password)\n" +
                "        2 for addSong (String title, String author, MusicType musicType)\n" +
                "        3 for signIn (String email, String password)\n" +
                "        \n" +
                "        4 for commands\n" +
                "        21 for EXIT\n");
    }
    static void commandsSignedIn() {
        System.out.println("\n        methods\n" +
                "        1 for follow(String emailToFollow)\n" +
                "        2 for unFollow(String emailToUnFollow)\n" +
                "        3 for loadPlaylist\n" +
                "        4 for loadPlaylistFromFollowed\n" +
                "        5 for addSongInPlaylist(String title)\n" +
                "        6 for removeSongInPlaylist(String title)\n" +
                "        7 for listenQueue\n" +
                "        8 for nextSong\n" +
                "        9 for songsListenedByUser\n" +
                "        10 for mostListenedPlaylistByUser\n" +
                "        11 for allPlaylistListened\n" +
                "        12 for discover\n" +
                "        13 for reccomendation\n" +
                "        \n" +
                "        14 for commands\n" +
                "        21 for Menù");
    }

    static void whileMenu() throws InterruptedException {
        while (true) {
            System.out.println("Inserire comando");
            int instruction = Integer.parseInt(sc.next());
            if (instruction == 21)
                break;
            else if(instruction >= 1 && instruction <= 4)
                actionMenu(instruction);
            else
                System.out.println("Comando errato: ripetere");
        }

    }
    static void whileSignedIn(String email) throws InterruptedException {
        while (true) {
            System.out.println("Inserire comando");
            int instruction = Integer.parseInt(sc.next());
            if (instruction == 21) {
                commandsMenu();
                break;
            }
            else if(instruction >= 1 && instruction <= 14)
                actionSignedIn(instruction, email);
            else
                System.out.println("Comando errato: ripetere");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Benvenuto in SpoTreefy!\nInserisci il primo utente");
        actionMenu(1);
        SpoTreefy.getSpoTreefy().addSong("Mood", "24Kgoldn", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Dynamite", "Bts", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Head & Heart", "Joel Corry", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Bella Storia", "Fedez", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Let's Love", "David Guetta", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Tick Tock", "Clean Bandit", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Contatto", "Negramaro", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Crepe", "Irama", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("22 Settembre", "Ultimo", MusicType.RAP);
        SpoTreefy.getSpoTreefy().addSong("Take You Dancing", "Jason Derulo", MusicType.RAP);
        commandsMenu();
        whileMenu();
    }
}
