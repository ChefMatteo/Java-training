package classi;

import com.google.gson.Gson;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Salagiochi {
    HashSet<Player> listOfPlayers = new HashSet<>();
    HashSet<Game> listOfGames = new HashSet<>();
    LinkedList<Partita> listOfPartita = new LinkedList<>();

    public Player addPlayer(String nickname){
        Player player = new Player(nickname);
        listOfPlayers.add(player);
        return player;

    }

    public Game addGame(String name, int difficulty){
        Game game = new Game(name, difficulty);
        listOfGames.add(game);
        return game;
    }

    public void newPartita(Player player, Game game, int score){
        listOfPartita.addFirst(new Partita(player, game, score));
    }

    public void classificaVideogame(Game game) {
        Map<Player, Optional<Partita>> classifica = listOfPartita.stream()
                .filter(x -> x.game.equals(game))
//                .sorted((x,y)-> Integer.compare(y.score,x.score))
//                .sorted(Comparator.comparingInt(Partita::getScore).reversed()
                .collect(Collectors.groupingBy
                        (Partita::getPlayer, Collectors.reducing
                                (BinaryOperator.maxBy(Comparator.comparingInt(Partita::getScore)))));

        Map<Player, Integer> classificaDaTre = classifica.entrySet().stream()
                .sorted((x,y)->Integer.compare(y.getValue().get().score, x.getValue().get().score))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, x->x.getValue().get().score));
        classificaDaTre.entrySet().forEach(x->{
            System.out.println(x.getKey().nickname + ": " + x.getValue());
        });
    }

}
