import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        while(!game.isOver()) {
            for (Player player : game.getPlayers()) {
                System.out.println(String.format("A toi de jouer %s:", player.getName()));
                System.out.println("");
                System.out.println("Ta main :");
                for (Object objet : player.getHand()) {
                    if (objet instanceof Card) {
                        System.out.println("Carte : "+ ((Card) objet).getColor() + ", Attribut : " + ((Card) objet).getName());
                    }
                }
                System.out.println("Tes points : " + player.getVictoryPoints());
                if (player.hasCatToken()) {
                    System.out.println("Jeton Chat : Oui");
                } else {
                    System.out.println("Jeton Chat : Non");
                }
                System.out.println("");
                System.out.println("Ta merveille :");
                System.out.println("Nom :" + player.getWonder().getName());
                for (int i = 0; i < player.getWonder().getStagesList().size(); i++) {
                    System.out.println(String.format("Etape %x : ", i) + player.getWonder().getStagesList().get(i).getNeededResources() + " Ressources Nécessaires");
                    if (player.getWonder().getStagesList().get(i).isConstructed()) {
                        System.out.print("Etat : Construite");
                    } else {
                        System.out.print("Etat : Pas Construite");
                    }
                    System.out.println("");
                }
                System.out.println("Où veux tu piocher ta carte ? (Table, Joueur de gauche, Joueur de droite)");
                player.makeAMove(game.getTable(), game, new Scanner(System.in).nextLine());
                System.out.println("Etat de la table :");
                for (ConflictToken token : game.getTable().getConflictTokens()) {
                    if (token.getVisibleFace().equals("Paix")) {
                        System.out.println("Jeton de Conflit : Paix");
                    } else {
                        System.out.println("Jeton de Conflit : Guerre");
                    }

                }
                for (ProgressToken token : game.getTable().getVisibleProgressTokens()) {
                    System.out.println("Jeton de Progrès : " + token.getName());
                }
                System.out.println("Jeton de Progrès : ?");
                System.out.println("Jetons Victoire Militaire Disponibles : " + game.getTable().getMilitaryVictoryTokens());
            }
        }
    }
}