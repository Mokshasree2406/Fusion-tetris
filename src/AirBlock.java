public class AirBlock extends Block {
    public AirBlock(Position position) {
        super(position);
    }

    public boolean interact(GameBoard board) {

        board.shuffleTopLayer();
        return false;
    }
}
