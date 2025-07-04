

public abstract class Block {
    protected Position position;

    public Block(Position position){
        this.position = position;
    }

    public Position  getPosition(){
        return position;
    }
    public void setPosition(Position position){
        this.position = position;
    }

    public abstract void interact(GameBoard board);

    public boolean isEmpty(){
        return this instanceof EmptyBlock;
    }

    public String getType(){
        return this.getClass().getSimpleName();
    }
}


public class FireBlock extends Block{
    public FireBlock(Position position){
        super(position);
    }

    @Override
    public void interact(GameBoard board){
        if(isPartOfFirePattern(board)){
            List<Position> affected = getAdjacentPositions(position);
            for(Position pos : affected){
                board.setBlockAt(pos.getRow() ,  pos.getCol() , new EmptyBlock(pos));
            }
        }
    }
    private static final int[][][] FIRE_PATTERNS = {
            { {0, -1}, {0, +1} },  // horizontal
            { {-1, 0}, {+1, 0} },  // vertical
            { {0, 1}, {1, 0} },    // L-shapes
            { {0, -1}, {1, 0} },
            { {0, 1}, {-1, 0} },
            { {0, -1}, {-1, 0} }
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


    private List<Position> getAdjacentPositions(Position pos){
        return Array.asList(
                new Position(pos.getRow()-1 , pos.getCol()),
                new Position(pos.getRow()+1 , pos.getCol()),
                new Position(pos.getRow() , pos.getCol()-1),
                new Position(pos.getRow() , pos.getCol()+1),
                new Position(pos.getRow()-1 , pos.getCol()-1),
                new Position(pos.getRow()+1 , pos.getCol()+1)
        );

    }
}




public class WaterBlock extends Block {
    public WaterBlock(Position position) {
        super(position);
    }

    @Override
    public void interact(GameBoard board) {
        int row = position.getRow();
        int col = position.getCol();

        int rowCount = 1;
        int colCount = 1;

        for (int i = col - 1; i >= 0; i--) {
            Block b = board.getBlockAt(row, i);
            if (b instanceof WaterBlock) rowCount++;
            else break;
        }

        for (int i = col + 1; i < 10; i++) {
            Block b = board.getBlockAt(row, i);
            if (b instanceof WaterBlock) rowCount++;
            else break;
        }

        for (int i = row - 1; i >= 0; i--) {
            Block b = board.getBlockAt(i, col);
            if (b instanceof WaterBlock) colCount++;
            else break;
        }

        for (int i = row + 1; i < 10; i++) {
            Block b = board.getBlockAt(i, col);
            if (b instanceof WaterBlock) colCount++;
            else break;
        }

        if (rowCount >= 3) {
            for (int i = 0; i < 10; i++) {
                board.setBlockAt(row, i, new EmptyBlock(new Position(row, i)));
            }
        }

        if (colCount >= 3) {
            for (int i = 0; i < 10; i++) {
                board.setBlockAt(i, col, new EmptyBlock(new Position(i, col)));
            }
        }
    }
}




public class EarthBlock extends Block{
    public EarthBlock(Position position){
        super(position);
    }

    public void interact(GameBoard board){
        board.shuffleColumn(9);
    }
}



public class AirBlock extends Block{
    public AirBlock(Position position){
        super(position);
    }
    public void interact(GameBoard board){
        board.shuffleTopLayer();
    }
}

