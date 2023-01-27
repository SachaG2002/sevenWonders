import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Player {
    private String name;
    private ArrayList<Object> hand = new ArrayList<>();
    private ArrayList<Card> pile = new ArrayList<>();
    private Wonder wonder;
    private boolean catToken;
    private int victoryPoints;

    public Player(String name, String wonderName, Game game) {
        this.name = name;
        this.wonder = new Wonder(wonderName);
        for (int i = 0; i < 20; i++) {
            String color = new ArrayList<>(Arrays.asList("Rouge", "Gris", "Jaune", "Bleu", "Vert")).get(i%5);
            switch (color) {
                case "Gris" -> {
                    String resource = game.getGreyCardsResourcesList().get(new Random().nextInt(game.getGreyCardsResourcesList().size()));
                    game.getGreyCardsResourcesList().remove(resource);
                    this.pile.add(new Card(true, color, resource));
                }

                case "Rouge" -> {
                    this.pile.add(new Card(true, color, new ArrayList<>(Arrays.asList("Une", "Deux", "Trois")).get(new Random().nextInt(3))));
                }

                case "Jaune" -> {
                    this.pile.add(new Card(true, color, "Pièce"));
                }

                case "Bleu" -> {
                    this.pile.add(new Card(true, color, new ArrayList<>(Arrays.asList("Chat", "Normal")).get(new Random().nextInt(2))));
                }

                case "Vert" -> {
                    this.pile.add(new Card(true, color, new ArrayList<>(Arrays.asList("Sculpture", "Architecture", "Physique")).get(new Random().nextInt(3))));
                }
            }
        }
        this.catToken = false;
        this.victoryPoints = 0;
    }

    public void makeAMove(Table table, Game game, String move) {
        Card card = null;
        if (move.equals("Table")) {
            card = table.getCardPile().get(0);
            this.hand.add(card);
            table.discard(card);
            table.getCardPile().remove(0);
        }

        if (card.getColor().equals("Gris") || card.getColor().equals("Jaune")) {
            for (WonderStage stage : this.wonder.getStagesList()){
                if (!stage.isConstructed()) {
                    stage.construct(this, table);
                    break;
                }
            }
        } else if (card.getColor().equals("Bleu")) {
            this.victoryPoints += 1;
        } else if (card.getColor().equals("Vert")) {
            this.hand.add(card);
            table.discard(card);
            table.getCardPile().remove(0);
        } else if (card.getColor().equals("Rouge")) {
            this.hand.add(card);
            table.discard(card);
            table.getCardPile().remove(0);
            if (card.getName().equals("Une")) {
                for (ConflictToken token : table.getConflictTokens()) {
                    if (token.getVisibleFace().equals("Paix")) {
                        token.turnToken("Guerre");
                        break;
                    }
                }
            } else if (card.getName().equals("Deux")) {
                int j = 0;
                for (ConflictToken token : table.getConflictTokens()) {
                    if (token.getVisibleFace().equals("Paix")) {
                        token.turnToken("Guerre");
                        j += 1;
                        if (j == 2) {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Carte piochée : " + card.getColor() + "(" + card.getName() + ")");
    }

    public int getVictoryPoints() {
        return this.victoryPoints;
    }

    public void addVictoryPoints(int victoryPoints) {
        this.victoryPoints += victoryPoints;
    }

    public void discard(Table table, Card card) {
        table.discard(card);
        this.getHand().remove(card);
    }

    public void useCatToken(Table table) {
        table.getCardPile().get(0).setVisibility(true);
    }

    public ArrayList<Object> getHand() {
        return this.hand;
    }

    public ArrayList<Card> getPile() {
        return this.pile;
    }

    public Wonder getWonder() {
        return this.wonder;
    }

    public boolean hasCatToken() {
        return this.catToken;
    }

    public void setCatToken(boolean catToken) {
        this.catToken = catToken;
    }

    public void takeCatToken(Table table, Game game) {
        table.setCatToken(false);
        for (Player player : game.getPlayers()) {
            player.setCatToken(false);
        }
        this.catToken = true;
    }

    public String getName() {
        return this.name;
    }
}
