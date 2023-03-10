import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Table {
    private ArrayList<Card> cardPile = new ArrayList<>();
    private ArrayList<ProgressToken> hiddenProgressTokens = new ArrayList<>();
    private ArrayList<ProgressToken> visibleProgressTokens = new ArrayList<>();
    private boolean catToken;
    private int militaryVictoryTokens;
    private ArrayList<ConflictToken> conflictTokens = new ArrayList<>();
    private ArrayList<Card> discardPile = new ArrayList<>();
    private ArrayList<String> progressTokensNameList = new ArrayList<>(Arrays.asList("Urbanisme", "Artisanat", "Joaillerie", "Science", "Propagande", "Architecture", "Economie", "Ingénierie", "Tactique", "Décoration", "Politique", "Stratégie", "Education", "Culture", "Culture"));

    public Table(int numberOfPlayers, Game game) {
        for (int i = 0; i < 200 - 20 * numberOfPlayers; i++) {
            String color = new ArrayList<>(Arrays.asList("Rouge", "Gris", "Jaune", "Bleu", "Vert")).get(i/((200 - 20 * numberOfPlayers)/5));

            switch (color) {
                case "Gris" -> {
                    String resource = game.getGreyCardsResourcesList().get(new Random().nextInt(game.getGreyCardsResourcesList().size()));
                    game.getGreyCardsResourcesList().remove(resource);
                    this.cardPile.add(new Card(false, color, resource));
                }

                case "Rouge" -> {
                    this.cardPile.add(new Card(false, color, new ArrayList<>(Arrays.asList("Zero", "Une", "Deux")).get(new Random().nextInt(3))));
                }

                case "Jaune" -> {
                    this.cardPile.add(new Card(false, color, "Pièce"));
                }

                case "Bleu" -> {
                    this.cardPile.add(new Card(false, color, new ArrayList<>(Arrays.asList("Chat", "Normal")).get(new Random().nextInt(2))));
                }

                case "Vert" -> {
                    this.cardPile.add(new Card(false, color, new ArrayList<>(Arrays.asList("Sculpture", "Architecture", "Physique")).get(new Random().nextInt(3))));
                }
            }
        }

        for (int i = 0; i < 12; i++) {
            String name = this.progressTokensNameList.get(new Random().nextInt(this.progressTokensNameList.size()));
            this.hiddenProgressTokens.add(new ProgressToken(name));
            this.progressTokensNameList.remove(name);
        }
        for (int i = 0; i < 3; i++) {
            String name = this.progressTokensNameList.get(new Random().nextInt(this.progressTokensNameList.size()));
            this.visibleProgressTokens.add(new ProgressToken(name));
            this.progressTokensNameList.remove(name);
        }

        switch (numberOfPlayers) {
            case 2 -> {
                for (int i = 0; i < 3; i++) {
                    this.conflictTokens.add(new ConflictToken());
                }
            }
            case 7 -> {
                for (int i = 0; i < 6; i++) {
                    this.conflictTokens.add(new ConflictToken());
                }
            }
            default -> {
                for (int i = 0; i < numberOfPlayers; i++) {
                    this.conflictTokens.add(new ConflictToken());
                }
            }
        }

        this.militaryVictoryTokens = 26;
        this.catToken = true;
    }

    public ArrayList<Card> getCardPile() {
        return this.cardPile;
    }

    public ArrayList<ProgressToken> getHiddenProgressTokens() {
        return this.hiddenProgressTokens;
    }

    public ArrayList<ProgressToken> getVisibleProgressTokens() {
        return this.visibleProgressTokens;
    }

    public boolean hasCatToken() {
        return this.catToken;
    }

    public int getMilitaryVictoryTokens() {
        return this.militaryVictoryTokens;
    }

    public ArrayList<ConflictToken> getConflictTokens() {
        return this.conflictTokens;
    }

    public ArrayList<Card> getDiscardPile() {
        return this.discardPile;
    }

    public void discard(Card card) {
        this.getDiscardPile().add(card);
    }

    public void setCatToken(boolean catToken) {
        this.catToken = catToken;
    }
}
