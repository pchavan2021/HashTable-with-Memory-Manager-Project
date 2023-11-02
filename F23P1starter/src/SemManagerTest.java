
import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

import student.TestCase;

/**
 * 
 * This class tests semManager
 * 
 * @author Pallavi Chavan pallucha21 and
 * @author Evan Howe evanhowe03
 * @version 09/14/23
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx() {
        SemManager sem = new SemManager();

        assertNotNull(sem);

    }


    /**
     * tests that scanner parses correctly
     * 
     * @Test
     */
    public void testScanner() {
        String testInput = "word1 word2 word3";
        Scanner scanner = new Scanner(testInput);

        assertTrue(scanner.hasNext());
        assertEquals("word1", scanner.next());
        assertTrue(scanner.hasNext());
        assertEquals("word2", scanner.next());
        assertTrue(scanner.hasNext());
        assertEquals("word3", scanner.next());

        assertFalse(scanner.hasNext());

    }


    /**
     * @Test
     *       tests that scanner parses
     *       the commands
     */
    public void testWords() {
        String test = "insert search delete print";

        Scanner scanner = new Scanner(test);

        assertTrue(scanner.hasNext());
        assertEquals("insert", scanner.next());
        assertEquals("search", scanner.next());
        assertEquals("delete", scanner.next());
        assertEquals("print", scanner.next());

    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testMain() throws NumberFormatException, Exception {

        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "P1Sample_input.txt";
        // SemManager sem = new SemManager();
        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("editedoutput.txt");
        // assertFuzzyEquals(referenceOutput, output);
        assertFuzzyEquals(referenceOutput, output);

        assertNotNull(sem);
    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testMain2() throws NumberFormatException, Exception {
        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "P1Sample_input.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();

        String referenceOutput = readFile("P1Sample_output.txt");
        // assertFuzzyEquals(referenceOutput, output);
        assertFuzzyEquals(referenceOutput, output);

    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testMain3() throws NumberFormatException, Exception {
        SemManager sem = new SemManager();
        String[] args = new String[3];
        args = null;
        SemManager.main(args);
        String output = systemOut().getHistory();

        String referenceOutput = readFile("editedoutput.txt");
        // assertFuzzyEquals(referenceOutput, output);
        assertFuzzyEquals(referenceOutput, output);

    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testTombstone() throws NumberFormatException, Exception {

        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "tombstone.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("tombstoneOUT.txt");
        assertFuzzyEquals(referenceOutput, output);

    }


    /**
     * tests the main method
     * 
     * @Test
     * @param name
     * @return
     */
    public String readFile(String name) {
        String fileFish = "";
        try {

            Scanner scan = new Scanner(new File(name));

            while (scan.hasNextLine()) {
                fileFish += scan.nextLine() + "\n";
            }
            scan.close();
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        return fileFish;
    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testGet() throws NumberFormatException, Exception {
        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "memInput.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        assertFuzzyEquals("freeblocklist\r\n" + "512 512", output);
    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testGet2() throws NumberFormatException, Exception {
        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "memInput2.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("mout.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testGet3() throws NumberFormatException, Exception {
        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "help.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("m.txt");
        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * tests the main method
     * 
     * @Test
     * @throws NumberFormatException
     * @throws Exception
     */
    public void testGet4() throws NumberFormatException, Exception {
        SemManager sem = new SemManager();
        String[] args = new String[3];
        args[0] = "512";
        args[1] = "4";
        args[2] = "h.txt";

        SemManager.main(args);
        String output = systemOut().getHistory();
        String referenceOutput = readFile("hout.txt");
        assertFuzzyEquals(referenceOutput, output);
    }
}
