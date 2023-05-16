package battleship;

import java.util.Scanner;

public class BattleshipGame {
    private boolean player1Turn = true;

    public BattleshipGame() {
        System.out.println("Player 1, place your ships on the game field");
        Player player1 = new Player();
        waitForEnter();
        System.out.println("Player 2, place your ships on the game field");
        Player player2 = new Player();
        waitForEnter();
        boolean gameRunning = true;
        while (gameRunning) {
            if (player1Turn) {
                gameRunning = doTurn(player1, player2);
            } else {
                gameRunning = doTurn(player2, player1);
            }
            player1Turn = !player1Turn;
            waitForEnter();
        }
    }

    private boolean doTurn(Player activePlayer, Player opponent) {
        opponent.getPlayingBoard().printFogOfWarBoard();
        System.out.println("---------------------");
        activePlayer.getPlayingBoard().printFullBoard();
        System.out.println("Player " + (player1Turn ? "1" : "2") + ", it's your turn:");
        return opponent.getPlayingBoard().shoot(System.in);

    }

    private void waitForEnter() {
        System.out.println("Press Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
