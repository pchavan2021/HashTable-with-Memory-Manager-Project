/**
 * @author Pallavi Chavan pallucha21 and Evan Howe evanhowe03
 * @version 09/14/23
 * This the freeBlock class the memory manager uses
 */
public class FreeBlock {
    
    private int startPos;
    private int size;
    /**
     * The freeblock constructor
     * @param pos
     * @param big
     */
    public FreeBlock(int pos, int big) {
        //the position of the block
        //within its specific linked list
        this.startPos = pos;
        //the size of the data
        this.size = big;
        

    }

    /**
     * 
     * @return the starting 
     * position
     */
    public int getPos() {
        return startPos;
    }
    
    /**
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
    public void setSize(int big) {
        this.size = big;
    }
    
    public void setPos(int place) {
        this.startPos = place;
    }

}
