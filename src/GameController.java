import java.util.Scanner;

public class GameController {
    private GameBoard board;
    public GameController() { board = new GameBoard(); }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fusion Tetris Started!");
        while (true) {
            board.printBoard();
            System.out.print("Enter block type to drop (fire, water, earth, air, nopower) or 'exit': ");
            String input = scanner.nextLine().trim().toLowerCase();
            System.out.println("enter the column: ");
            int n = scanner.nextInt();

            if (input.equals("exit")) break;
            dropBlock(input,n);
        }
        scanner.close();
        System.out.println("Game Over.");
    }
    public void dropBlock(String type,int n) {
        Block b = BlockFactory.createBlock(type, null);
        System.out.println("hi");
        boolean a = board.dropBlock(b, n);
        board.printBoard();
        board.updateBoard();
        board.printBoard();
    }

}