import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FireBlock extends Block {
    public FireBlock(Position position) {
        super(position);
    }

//    @Override
//    public boolean interact(GameBoard board) {
//        if (isPartOfFirePattern(board)) {
//            List<Position> affected = getAdjacentPositions(position);
//            boolean a = false;
//            System.out.println(affected.size()+"is size");
//            for (Position pos : affected) {
//                if(pos.getRow()>-1 && pos.getCol()>-1 && pos.getCol()<10 && pos.getRow()<10 ) {
//                    a = true;
//                    board.setBlockAt(pos.getRow(), pos.getCol(), new EmptyBlock(pos));
//                    removeSurroundings(pos,board);
//                }
//            }
//            return a;
//
//        }
//
//        return false;
//    }
//    public void removeSurroundings(Position p,GameBoard board){
//        int r = p.getRow();
//        int c = p.getCol();
//        Block pos = board.getBlockAt(r,c);
//        if (r-1>=0) board.setBlockAt(r ,c, new EmptyBlock(new Position(r-1,c)));
//        if (c-1>=0) board.setBlockAt(r ,c, new EmptyBlock(new Position(r,c-1)));
//        if (r+1<10) board.setBlockAt(r ,c, new EmptyBlock(new Position(r+1,c)));
//        if (c+1<10) board.setBlockAt(r ,c, new EmptyBlock(new Position(r,c+1)));
//    }
//
//    private static final int[][][] FIRE_PATTERNS = {
//            {{0, -1}, {0, +1}},  // horizontal
//            {{-1, 0}, {+1, 0}},  // vertical
//            {{0, 1}, {1, 0}},    // L-shapes
//            {{0, -1}, {1, 0}},
//            {{0, 1}, {-1, 0}},
//            {{0, -1}, {-1, 0}}
//    };
//
//    private boolean isPartOfFirePattern(GameBoard board) {
//        int r = position.getRow();
//        int c = position.getCol();
//        for (int[][] pattern : FIRE_PATTERNS) {
//            boolean match = true;
//            for (int[] offset : pattern) {
//                int nr = r + offset[0];
//                int nc = c + offset[1];
//                if (nr < 0 || nr >= 10 || nc < 0 || nc >= 10) {
//                    match = false;
//                    break;
//                }
//                if (!(board.getBlockAt(nr, nc) instanceof FireBlock)) {
//                    match = false;
//                    break;
//                }
//            }
//            if (match) return true;
//        }
//        return false;
//    }
//
//
//    private List<Position> getAdjacentPositions(Position pos) {
//        return Arrays.asList(
//                new Position(pos.getRow() - 1, pos.getCol()),
//                new Position(pos.getRow() + 1, pos.getCol()),
//                new Position(pos.getRow(), pos.getCol() - 1),
//                new Position(pos.getRow(), pos.getCol() + 1),
//                new Position(pos.getRow() - 1, pos.getCol() - 1),
//                new Position(pos.getRow() + 1, pos.getCol() + 1)
//        );
//
//    }
public boolean interact(GameBoard board) {
    boolean a = false;
    if (isPartOfFirePattern(board)) {
        List<Position> toClear = getAffectedPositions(position);
        for(Position p : toClear){
            System.out.print(p+" ");
        }
        for (Position pos : toClear) {
            if (isInsideBoard(pos)) {
                board.setBlockAt(pos.getRow(), pos.getCol(), new EmptyBlock(pos));
                a = true;
            }
        }
    }
    return a;
}

    private boolean isPartOfFirePattern(GameBoard board) {
        int r = position.getRow();
        int c = position.getCol();
        for (int[][] pattern : FIRE_PATTERNS) {
            boolean match = true;
            for (int[] offset : pattern) {
                int nr = r + offset[0];
                int nc = c + offset[1];
                if (!isInsideBoard(nr, nc) || !(board.getBlockAt(nr, nc) instanceof FireBlock)) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        return false;
    }
    private boolean isInsideBoard(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < 10 && pos.getCol() >= 0 && pos.getCol() < 10;
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < 10 && col >= 0 && col < 10;
    }

    private List<Position> getAffectedPositions(Position center) {
        List<Position> positions = new ArrayList<>();
        int r = center.getRow();
        int c = center.getCol();

        // center
        positions.add(new Position(r, c));
        // adjacent 4 directions
        positions.add(new Position(r - 1, c));
        positions.add(new Position(r + 1, c));
        positions.add(new Position(r, c - 1));
        positions.add(new Position(r, c + 1));
        // diagonals
        positions.add(new Position(r - 1, c - 1));
        positions.add(new Position(r - 1, c + 1));
        positions.add(new Position(r + 1, c - 1));
        positions.add(new Position(r + 1, c + 1));

        return positions;
    }

    private static final int[][][] FIRE_PATTERNS = {
            {{0, -1}, {0, +1}},  // horizontal
            {{-1, 0}, {+1, 0}},  // vertical
            {{0, 1}, {1, 0}},    // L-shapes
            {{0, -1}, {1, 0}},
            {{0, 1}, {-1, 0}},
            {{0, -1}, {-1, 0}}
    };
}
