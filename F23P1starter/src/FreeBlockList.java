/**
 * 
 */
public class FreeBlockList {
    // FreeBlock help = new FreeBlock(0, 0);
    // private FreeBlock loveSosa;
    private SinglyLinkedList<SinglyLinkedList<FreeBlock>> freeBlocks;

    public FreeBlockList(int size) {

        double x = Math.log10(size) / Math.log10(2);
        int listSize = (int)x;

        this.freeBlocks = new SinglyLinkedList<SinglyLinkedList<FreeBlock>>();

        // creates a list of singly linked lists
        // got the singly linked list code from project 5 in 2114
        for (int i = 0; i < listSize; i++) {

            freeBlocks.add(i, new SinglyLinkedList<FreeBlock>());
        }
        FreeBlock intializer = new FreeBlock(size, (int)Math.pow(2, listSize));
        freeBlocks.get(listSize - 1).add(intializer);

    }


    private int findStartPos(int size) {

        // find the starting position of the data in the freeBlockList
        int indexPos = findBestIndex(size);

        // get the size of the index list
        int multiple = freeBlocks.get(indexPos).size();

        // get the size of the byte
        if (indexPos == 0) {
            indexPos = 1;
        }
        int byteSize = (int)Math.pow(2, indexPos + 1);

        // multiply the size of the byte by how many items
        // are in the list
        int result = multiple * byteSize;
        // System.out.println("this is the start pos" + result);
        return result;
    }


    // returns the literal index it should be inserted at
    private int findBestIndex(int size) {

        double x = (Math.log10(size) / Math.log10(2));
        int index = (int)x;
        return index;
    }


    // allocates freespace from the appropriate spot in the list
    // based on the size of the byte of data
    public Handle createHandle(int size) {

        int index = findBestIndex(size);

        int blockSize = (int)Math.pow(2, (index + 1));

        int position = freeBlocks.get(index).size() * blockSize;

        Handle newHandle = new Handle(position, blockSize);

        // return it to the memory manager
        return newHandle;

    }


    // adds free space back into the appropriate spot
    public void releaseBlock(Handle memHandle) {

        int size = memHandle.getLength();

        // extract the starting position from the handle
        int index = findBestIndex(size) - 1;
        int multiple = freeBlocks.get(index).size();
        // System.out.println("this is the index" + index);
        int start = findStartPos(size);

        FreeBlock block = new FreeBlock(memHandle.getStart(), size);
        freeBlocks.get(index).add(block);
        
        System.out.println("THIS IS THE SIZE OF THE REMOVE LIST: " + multiple);
        

    }


    public void addBlock(int size) {

        int bestIndex = findBestIndex(size);

        int nextHighest = findNextHighest(bestIndex);

        // System.out.println("this is the next highest " + nextHighest);

        if (nextHighest == -1) {
            doubleList(size);

            nextHighest = findNextHighest(bestIndex);

            // freeBlocks.get(nextHighest).remove(0);
        }
        else {
            while (nextHighest != bestIndex) {

                split(nextHighest);
                nextHighest--;
            }

            freeBlocks.get(bestIndex).remove(0);
        }
        System.out.println("THIS IS THE SIZE OF THE LIST: " + freeBlocks.get(bestIndex).size());

    }


    private void split(int index) {

        int currIndex = index;
        int lowerIndex = index - 1;

        int multiple = freeBlocks.get(lowerIndex).size();
        int newSize = (int)Math.pow(2, currIndex);

        int position = freeBlocks.get(lowerIndex).size() * newSize;
        // System.out.println("this is the position " + position);
        // System.out.println("this is the size "+newSize);
        // create two blocks of index i-1
        FreeBlock firstSplit = new FreeBlock(position, newSize);
        FreeBlock secondSplit = new FreeBlock(position + newSize, newSize);

        // add the two blocks to the lower index i -1
        freeBlocks.get(lowerIndex).add(firstSplit);
        freeBlocks.get(lowerIndex).add(secondSplit);

        // remove the last block off of the index
        freeBlocks.get(currIndex).remove(0);
    }


    // finds the next highest index that isn't null
    // returning the literal index
    private int findNextHighest(int best) {
        int nextHighest = best;

        for (int x = nextHighest; x < freeBlocks.size(); x++) {

            if (freeBlocks.get(x).size() > 0) {

                return x;
            }
        }

        if (freeBlocks.get(nextHighest).size() == 0) {
            return -1;
        }

        return nextHighest;
    }


    public void doubleList(int size) {

        int newIndex = freeBlocks.size();

        SinglyLinkedList<FreeBlock> list = new SinglyLinkedList<FreeBlock>();

        int fat = ((int)Math.pow(2, newIndex));
        FreeBlock freshBlock = new FreeBlock(fat, fat);
        // System.out.println(newIndex);
        list.add(freshBlock);

        freeBlocks.add(newIndex - 1, list);
        // System.out.println("FREEBLCOKS DOUBLED ; "
        // + freeBlocks.get(newIndex - 1).get(0).getSize() + "");

        addBlock(size);

        // mergeFreeBlocks();
    }


    // merges adjacent free blocks when possible
    public void mergeFreeBlocks() {
        System.out.println("THE MERGE HAS BEEN CALLED");
        // continue until you reach the end of the freeBlocks list
        for (int i = 0; i < freeBlocks.size() - 1; i++) {
            System.out.println("I AM WITHIN THE FIRST FOR LOOP");
            
            if (freeBlocks.get(i).size() > 1) {
                
            for (int j = 0; j < freeBlocks.get(i).size() - 2; j++) {
                System.out.println("I AM WITHIN THE TWO FOR LOOPS");
                
                
                    System.out.println("I AM INSIDE THE IF STATEMENT");
                    FreeBlock curr = freeBlocks.get(i).get(j);
                    FreeBlock next = freeBlocks.get(i).get(j + 1);
                    int sizeNext  = freeBlocks.get(i + 1).size();
                    int currStart = curr.getPos();
                    int nextStart = next.getPos();
                    int blockSize = curr.getSize();

                    // int startPos = findStartPos(blockSize * 2);
                    FreeBlock newBlock = new FreeBlock(currStart, blockSize
                        * 2);
                    
                    for (int x = 0; x < freeBlocks.get(i + 1).size(); x++) {
                        //if (freeBlocks.get(i + 1).get(sizeNext - 1).getPos() < currStart) {
                        
                            //if ((currStart ^ nextStart) == blockSize) {
                                System.out.println("THE LAST STEP OF THE MERGE");
                                freeBlocks.get(i + 1).add(sizeNext, newBlock);
                                freeBlocks.get(i).remove(j);
                                freeBlocks.get(i).remove(j + 1);

                            //}
                        //}
                    }
                    
            }
                
            }

        }

    }


    private boolean isEmpty() {

        int count = 0;

        for (int i = 0; i < freeBlocks.size(); i++) {
            if (freeBlocks.get(i).isEmpty()) {
                count++;
            }
        }

        if (count == freeBlocks.size()) {
            return true;
        }
        return false;
    }


    public void dump() {

        for (int x = 0; x < freeBlocks.size(); x++) {

            if (freeBlocks.get(x).size() > 0) {
                int power = (int)Math.pow(2, x + 1);
                System.out.print(power + ": ");
                for (int y = 0; y < freeBlocks.get(x).size(); y++) {
                    System.out.print("" + freeBlocks.get(x).get(y).getPos()
                        + " ");

                }
                System.out.println("");
            }

        }

        if (isEmpty()) {
            System.out.println("There are no freeblocks in the memory pool");
        }

    }


    public boolean allEmpty() {
        for (int x = 0; x < freeBlocks.size(); x++) {
            if (freeBlocks.get(x).size() > 0) {
                return false;

            }
        }

        return true;
    }


    public boolean spaceToInsert(Handle ham) {

        for (int x = 0; x < freeBlocks.size(); x++) {
            for (int y = 0; y < freeBlocks.get(x).size(); y++) {

                if (freeBlocks.get(x).get(y).getPos() >= ham.getLength()) {
                    return true;
                }
            }
        }

        return false;
    }

}
