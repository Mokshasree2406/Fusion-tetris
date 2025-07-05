import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FireBlock extends Block {
    public FireBlock(Position position) {
        super(position);
    }

    @Override
    public boolean interact(GameBoard board) {
        if (isPartOfFirePattern(board)) {
            List<Position> affected = getAdjacentPositions(position);
            boolean a = false;
            System.out.println(affected.size()+"is size");
            for (Position pos : affected) {
                if(pos.getRow()>-1 && pos.getCol()>-1 && pos.getCol()<10 && pos.getRow()<10 ) {
                    a = true;
                    board.setBlockAt(pos.getRow(), pos.getCol(), new EmptyBlock(pos));
                    removeSurroundings(pos,board);
                }
            }
            return a;

        }

        return false;
    }
    public void removeSurroundings(Position p,GameBoard board){
        int r = p.getRow();
        int c = p.getCol();
        Block pos = board.getBlockAt(r,c);
        if (r-1>=0) board.setBlockAt(r ,c, new EmptyBlock(new Position(r-1,c)));
        if (c-1>=0) board.setBlockAt(r ,c, new EmptyBlock(new Position(r,c-1)));
        if (r+1<10) board.setBlockAt(r ,c, new EmptyBlock(new Position(r+1,c)));
        if (c+1<10) board.setBlockAt(r ,c, new EmptyBlock(new Position(r,c+1)));
    }

    private static final int[][][] FIRE_PATTERNS = {
            {{0, -1}, {0, +1}},  // horizontal
            {{-1, 0}, {+1, 0}},  // vertical
            {{0, 1}, {1, 0}},    // L-shapes
            {{0, -1}, {1, 0}},
            {{0, 1}, {-1, 0}},
            {{0, -1}, {-1, 0}}
    };

    private boolean isPartOfFirePattern(GameBoard board) {
        int r = position.getRow();
        int c = position.getCol();
        for (int[][] pattern : FIRE_PATTERNS) {
            boolean match = true;
            for (int[] offset : pattern) {
                int nr = r + offset[0];
                int nc = c + offset[1];
                if (nr < 0 || nr >= 10 || nc < 0 || nc >= 10) {
                    match = false;
                    break;
                }
                if (!(board.getBlockAt(nr, nc) instanceof FireBlock)) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;
    }


    private List<Position> getAdjacentPositions(Position pos) {
        return Arrays.asList(
                new Position(pos.getRow() - 1, pos.getCol()),
                new Position(pos.getRow() + 1, pos.getCol()),
                new Position(pos.getRow(), pos.getCol() - 1),
                new Position(pos.getRow(), pos.getCol() + 1),
                new Position(pos.getRow() - 1, pos.getCol() - 1),
                new Position(pos.getRow() + 1, pos.getCol() + 1)
        );

    }
}
