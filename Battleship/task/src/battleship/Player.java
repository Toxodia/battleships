package battleship;

public class Player {
    private PlayingBoard playingBoard = new PlayingBoard();

    public Player() {
        playingBoard.printFullBoard();
        fillBoardWithShips();
    }

    public PlayingBoard getPlayingBoard() {
        return playingBoard;
    }

    private void fillBoardWithShips() {
        placeShip("Aircraft Carrier", 5);
        placeShip("Battleship", 4);
        placeShip("Submarine", 3);
        placeShip("Cruiser", 3);
        placeShip("Destroyer", 2);
    }

    private void placeShip(String name, int size) {
        System.out.println("Enter the coordinates of the " + name + " (" + size + " cells):\n");
        Ship ship = new Ship(name, size);
        ship.collectCoordinates(playingBoard);
        ship.placeOnBoard(playingBoard);
        playingBoard.addToShipList(ship);
        playingBoard.printFullBoard();
    }

    private void startGame() {
        playingBoard.printFogOfWarBoard();
        startShooting();
    }

    private void startShooting() {
        System.out.println("Take a shot!\n");
        boolean gameRunning = true;
        while (gameRunning) {
            gameRunning = playingBoard.shoot(System.in);
        }
    }
}
