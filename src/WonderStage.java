import java.util.ArrayList;
import java.util.Arrays;

public class WonderStage {
    private int victoryPoints;
    private int neededResources;
    private boolean matchingResources;
    private boolean isConstructed;

    public WonderStage(int neededResources, boolean matchingResources) {
        this.isConstructed = false;
        this.neededResources = neededResources;
        this.victoryPoints = neededResources;
        this.matchingResources = matchingResources;
    }

    public boolean isConstructed() {
        return this.isConstructed;
    }

    public int getVictoryPoints() {
        return this.victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public int getNeededResources() {
        return this.neededResources;
    }

    public void construct(Player player, Table table) {
        for (String resource : new ArrayList<>(Arrays.asList("Bois", "Pierre", "Brique", "Papier", "Eau"))) {
            int freq = 0;
            ArrayList<Card> cards = new ArrayList<>();
            for (Object object : player.getHand()) {
                if (object instanceof Card && ((Card) object).getName().equals(resource)) {
                    freq += 1;
                    cards.add((Card) object);
                }
            }
            if (freq == this.neededResources) {
                this.isConstructed = true;
                for (Card card : cards) {
                    player.discard(table, card);
                }
            }
        }
    }
}
