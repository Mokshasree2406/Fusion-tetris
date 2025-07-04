public class EarthBlock extends Block {
    public EarthBlock(Position position) {
        super(position);
    }

    public boolean interact(GameBoard board) {
        board.shuffleRow(9);
        return false;
    }
}
