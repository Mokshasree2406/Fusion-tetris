public class NoPowerBlock extends Block{
    public NoPowerBlock(Position position){
        super(position);
    }
    public boolean interact(GameBoard board){
        return false;
        //we usually do nothing if the block is of nopwr , no function
    }
}
