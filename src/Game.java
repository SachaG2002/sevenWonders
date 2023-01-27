import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private Table table;
    private ArrayList<String> greyCardsResourcesList = new ArrayList<>(Arrays.asList("Bois","Bois","Bois","Bois","Bois","Bois","Bois","Bois","Pierre","Pierre","Pierre","Pierre","Pierre","Pierre","Pierre","Pierre","Brique","Brique","Brique","Brique","Brique","Brique","Brique","Brique","Papier","Papier","Papier","Papier","Papier","Papier","Papier","Papier","Eau","Eau","Eau","Eau","Eau","Eau","Eau","Eau"));

    public Game() {
        System.out.println("Combien de joueurs dans la partie ?");
        int numberOfPlayers = new Scanner(System.in).nextInt();
        this.table = new Table(numberOfPlayers, this);

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(String.format("Joueur %x :", i));
            System.out.println("Quel est votre nom ?");
            String playerName = new Scanner(System.in).nextLine();
            System.out.println("Quelle merveille voulez-vous choisir ?");
            String wonderName = new Scanner(System.in).nextLine();
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
