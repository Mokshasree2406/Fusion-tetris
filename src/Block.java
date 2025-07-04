import javax.swing.text.Position;

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

public class NoPowerBlock extends Block{
    public NoPowerBlock(Position position){
        super(position);
    }
    public void interact(GameBoard board){
        //we usually do nothing if the block is of nopwr , no function
    }
}


public class FireBlock extends Block{
    public FireBlock(Position position){
        super(position);
    }
}