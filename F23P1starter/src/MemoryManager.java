/**
 * 
 */

/**
 * 
 */
public class MemoryManager {

    // taken bkocks
    private byte[] memoryPool;
    private int size;
    private int totalBites;

    // free blocks an array of linked lists
    private FreeBlockList freeBlockList;

    // Constructor. poolsize defines the size of the memory pool in bytes
    public MemoryManager(int poolSize) {

        totalBites = poolSize;

        this.memoryPool = new byte[poolSize];
        this.freeBlockList = new FreeBlockList(poolSize);
        this.size = poolSize;
    }


    public void intializeFreeBlock(int size) {
        int x = (int)(Math.log10(size) / Math.log10(2));
        // x = x + 1;

        int pow = (int)Math.pow(2, x);

        freeBlockList = new FreeBlockList(pow);
    }// Insert a record and return its position handle.
     // space contains the record to be inserted, of length size.


    public void insert(int size) {

        Handle member = freeBlockList.createHandle(size);

        // System.out.println("totalBites: " + totalBites + " size: " + size +
        // "");
         System.out.println("before: ");
         freeBlockList.dump();

        // totalBites = totalBites - size;
        // memoryPool[member.getStart()] = (byte) size;
        // remember to un comment and use
        // remove the space from the freeBlockLisy
        // System.out.println(freeBlockList.allEmpty());
        if (freeBlockList.allEmpty()) {
            // System.out.println(freeBlockList.allEmpty());
            expandMemory();

        }
        else if (!freeBlockList.spaceToInsert(member)) {

            expandMemory();
        }
        // CHANGW
        // .boolean result =
        freeBlockList.addBlock(size);
   
         System.out.println("after: ");
         freeBlockList.dump();

    }


    public Handle getHandle(int size) {

        Handle member = freeBlockList.createHandle(size);

        // return the handle (to the database)
        return member;

    }


    private void expandMemory() {

        byte[] newPool = new byte[size * 2];

        for (int i = 0; i < size; i++) {
            newPool[i] = memoryPool[i];
        }
        // System.out.print(" BItES: " + totalBites + "");

        totalBites = totalBites + size;
        this.size = (size * 2);

        // System.out.print(" Bites2: " + totalBites + "");
        this.memoryPool = newPool;
        System.out.println("Memory pool expanded to " + memoryPool.length
            + " bytes");
        // freeBlockList.dump();
    }


    // Return the length of the record associated with theHandle
    public int length(Handle theHandle) {

        // call the getLength method from the handle
        return theHandle.getLength();

    }


    public void earlyDouble(Handle ham) {

        while (totalBites < ham.getLength()) {

            expandMemory();
        }

    }// Free a block at the position specified by theHandle.
     // Merge adjacent free blocks.


    public void remove(Handle theHandle) {

        // remove the byte from the byteArray

        // add a block of appropriate size to FBL
        freeBlockList.releaseBlock(theHandle);

        // System.out.println("SISISIISSIISISIS: " + theHandle.getLength());
        // freeBlockList.addBack(theHandle.getLength());
        // System.out.println("before merge");
        // freeBlockList.dump();
        // merge free blocks
        //System.out.println("BEFORE MERGE");
        //dump();
        
        freeBlockList.mergeFreeBlocks();
        
        //System.out.println("AFTER MERGE");
        //dump();
        // System.out.println("after merge");
        // freeBlockList.dump();

        // add back to the totalBytes
        totalBites += theHandle.getLength();
    }


    // Return the record with handle posHandle, up to size bytes, by
    // copying it into space.
    // Return the number of bytes actually copied into space.
    public int get(byte[] space, Handle theHandle, int size) {

        // get the index for space from the handle
        int pos = theHandle.getStart();

        // grab the byte stored in the index of space
        byte bite = space[pos];

        // return the integer value of the byte
        return (int)bite;

    }


    // Dump a printout of the freeblock list
    public void dump() {

        freeBlockList.dump();

    }

}
