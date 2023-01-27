public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        while(!game.isOver()) {
            for (Player player : game.getPlayers()) {
                System.out.println(String.format("A toi de jouer %s", player.getName()));
            }
        }
    }
}