import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameBoard {
    private Block[][] board;
    public GameBoard(){
        board = new Block[10][10];
        for(int i=0;i<10;i++){
            for(int j=0; j<10; j++){
                board[i][j] = new EmptyBlock(new Position(i,j));
            }
        }
    }

    public boolean dropBlock(Block block, int col) {
        for (int row = 9; row >= 0; row--) {
//            System.out.println("h");
            if (board[row][col].isEmpty()) {
//                System.out.println("hi");
                if (row != 9) {
                    block.position = new Position(row - 1, col);
                    board[row][col] = block;
                    System.out.println("hello");
                    return true;
                }
            }
        }

        return false;
    }

    public void updateBoard() {
        boolean a = true;
        while(a) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (!board[i][j].isEmpty()) {
                        a = board[i][j].interact(this);
                    }
                }
                this.applyGravity();
            }
        }
    }
    public void shuffleRow(int row) {
        List<Block> rowBlocks = new ArrayList<>();
        for (int col = 0; col < 10; col++) {
            rowBlocks.add(board[row][col]);
        }
        Collections.shuffle(rowBlocks);
        for (int col = 0; col < 10; col++) {
            Block b = rowBlocks.get(col);
            b.position = new Position(row, col);
            board[row][col] = b;
        }
    }
    public void shuffleTopLayer(){
        List<Block> rowBlocks = new ArrayList<>();
        for (int col = 0; col < 10; col++) {
            boolean a =false;
            for(int r =0; r<10;r++){
                if(board[r][col].isEmpty())continue;
                rowBlocks.add(board[r][col]);
                a = true;
                break;
            }
            if(!a){
                rowBlocks.add(board[9][col]);
            }
        }
        Collections.shuffle(rowBlocks);
        for (int col = 0; col < 10; col++) {
            for(int r =0; r<10;r++){
                if(board[r][col].isEmpty())continue;
                Block b = rowBlocks.get(col);
                b.position = new Position(r, col);
                board[r][col] = b;
            }

        }
    }
    public Block getBlockAt(int row, int col) {
        return board[row][col];
    }

    public void setBlockAt(int row, int col, Block block) {
        board[row][col] = block;
    }
    public void applyGravity() {
        for (int col = 0; col < 10; col++) {
            List<Block> nonEmpty = new ArrayList<>();
            for (int row = 9; row >= 0; row--) {
                if (!board[row][col].isEmpty()) {
                    nonEmpty.add(board[row][col]);
                }
            }
            int rowIndex = 9;
            for (Block b : nonEmpty) {
                b.position = new Position(rowIndex, col);
                board[rowIndex][col] = b;
                rowIndex--;
            }
            while (rowIndex >= 0) {
                board[rowIndex][col] = new EmptyBlock(new Position(rowIndex, col));
                rowIndex--;
            }
        }
    }
    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Block b = board[i][j];
                String name = b instanceof EmptyBlock ? " . " :
                        b instanceof FireBlock ? " F " :
                                b instanceof WaterBlock ? " W " :
                                        b instanceof EarthBlock ? " E " :
                                                b instanceof AirBlock ? " A " :
                                                        b instanceof NoPowerBlock ? " N " : " ? ";
                System.out.print(name);
            }
            System.out.println();
        }
        System.out.println();
    }

}
