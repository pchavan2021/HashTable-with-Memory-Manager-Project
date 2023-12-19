
/**
 * The class containing the main method.
 *
 * @author Pallavi Chavan pallucha21 and Evan Howe evanhowe03
 * @version (2023-09-14)
 */
public class SemManager {
    /**
     * constructor
     */
    public SemManager() {
        // intentionally empty
    }


    /**
     * @param args
     *            Command line parameters
     * @throws Exception
     * @throws NumberFormatException
     */
    public static void main(String[] args)
        throws NumberFormatException,
        Exception {
        // This is the main file for the program.
        // checks for three arguments
        if (args != null && args.length == 3) {

            CommandProcessor processor = new CommandProcessor(args[2], Integer
                .parseInt(args[0]), Integer.parseInt(args[1]));

        }
        else {
            CommandProcessor hello = new CommandProcessor("P1Sample_input.txt",
                512, 4);

        }

    }
}
