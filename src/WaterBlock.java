public class WaterBlock extends Block {
    public WaterBlock(Position position) {
        super(position);
    }

    @Override
    public boolean interact(GameBoard board) {
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
        boolean a = false;
        if (rowCount >= 3) {
            for (int i = 0; i < 10; i++) {
                board.setBlockAt(row, i, new EmptyBlock(new Position(row, i)));
            }
            a = true;
        }

        if (colCount >= 3) {
            for (int i = 0; i < 10; i++) {
                board.setBlockAt(i, col, new EmptyBlock(new Position(i, col)));
            }
            a = true;
        }
        return a;
    }
}
