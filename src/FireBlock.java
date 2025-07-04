import java.util.Arrays;
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
            for (Position pos : affected) {
                a =true;
                board.setBlockAt(pos.getRow(), pos.getCol(), new EmptyBlock(pos));
            }
            return a;

        }

        return false;
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
