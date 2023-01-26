import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private Table table;
    private ArrayList<String> greyCardsResourcesList = new ArrayList<>(Arrays.asList("Bois","Bois","Bois","Bois","Bois","Bois","Bois","Bois","Pierre","Pierre","Pierre","Pierre","Pierre","Pierre","Pierre","Pierre","Brique","Brique","Brique","Brique","Brique","Brique","Brique","Brique","Papier","Papier","Papier","Papier","Papier","Papier","Papier","Papier","Eau","Eau","Eau","Eau","Eau","Eau","Eau","Eau"));

    public Game() {
        int numberOfPlayers = new Scanner(System.in).nextInt();
        this.table = new Table(numberOfPlayers, this);

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = new Scanner(System.in).next("Quel est votre nom ?");
            String wonderName = new Scanner(System.in).next("Quelle merveille voulez-vous choisir ?");
            this.players.add(new Player(playerName, wonderName, this));
        }
    }

    public ArrayList<String> getGreyCardsResourcesList() {
        return this.greyCardsResourcesList;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public boolean isOver() {
        boolean result = false;
        for (Player player : this.players) {
            if (player.getWonder().isFinished()) {
                result = true;
                break;
            }
        }
        return result;
    }
}
