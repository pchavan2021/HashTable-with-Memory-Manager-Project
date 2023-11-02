/**
 * 
 */

/**
 * /**
 * 
 * @author Pallavi Chavan pallucha21 and Evan Howe evanhowe03
 * @version (2023-09-14) The seminar database class
 */
public class SemDB {
    // create instances of the HashTable
    // and the memoryManager
    private HashTable table;
    private MemoryManager mem;

    /**
     * 
     * @param initialMemorySize
     *            initial memory size by args
     * @param initialHashSize
     *            initial hash size by args
     */
    public SemDB(int initialMemorySize, int initialHashSize) {
        // initialize the memManager and hashTable
        // with the initial sizes from the main method.
        int memSize = initialMemorySize;
        int hashSize = initialHashSize;

        table = new HashTable(hashSize);
        mem = new MemoryManager(memSize);

    }


    /**
     * Call the memManager and hashtable insert methods
     * 
     * @param id
     *            also the key
     * @param sem
     *            the seminar
     * @throws Exception
     */
    public void add(int id, Seminar sem) throws Exception {
        // calculate the byte[] x value for the semianr
        byte[] x = sem.serialize();

        // Handle identifier = mem.getHandle(x, x.length);
        // Handle identifier = mem.getHandle(x.length);
        // mem.insert(x.length);
        boolean test = table.searchTest(id);
        // call the hashtable insert

        if (!test) {
            Handle identifier = mem.getHandle(x.length);
            mem.earlyDouble(identifier);

            //System.out.println("THE FREE BLOCKS BEFOrE THE INSERT");
            //mem.dump();
            mem.insert(x.length);

            table.insert(id, sem, identifier);

        }
        else {
            System.out.println("Search FAILED --" + " There"
                + " is no record with ID " + id);
        }
        //System.out.println("THE FREE BLOCKS AFTER THE INSERT");
       // mem.dump();
    
    }


    /**
     * Call the memManager and hashtable remove methods
     * 
     * @param key
     *            the key
     */
    public void remove(int key) {

        // retrieve the handle associated with the key
        Handle thisHandle = table.returnHandle(key);

        // if the hashtable deletion works
        // then delete it in the memPool
        boolean result = table.delete(key);
        if (result == true) {

            System.out.println("BEFORE DELETE");
            mem.dump();
            mem.remove(thisHandle);
            System.out.println("AFTER DELETE");
            mem.dump();
        }
        
       

    }


    /**
     * Call the hashtable search method
     * 
     * @param key
     *            the key
     */
    public void search(int key) {

        table.search(key);

    }


    /**
     * Call the memManager and hashtable print and dump methods
     * 
     * @param text
     *            the command
     */
    public void print(String text) {

        boolean result = table.print(text);

        if (result == true) {
            mem.dump();
        }

    }

}
