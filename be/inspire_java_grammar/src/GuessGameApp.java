import features.game.GuessGame;

public class GuessGameApp {
    public static void main(String[] args) {
        GuessGame guess = new GuessGame();
        String message = guess.gameFor();
        System.err.println(message);
        // guess.gameFor();
    }
}
