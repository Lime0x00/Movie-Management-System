import java.util.Scanner;

/**
 * The Main class serves as the entry point for the application. 
 * It initializes the CLI manager and starts the command-line interface for user interaction.
 */
public class Main {

    /**
     * The main method is the program's entry point. 
     * It creates a Scanner object for user input, initializes the CLI Manager, and starts the CLI process.
     *
     * @param args Command-line arguments passed to the application (not used in this program).
     */
    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanObj = new Scanner(System.in);

        // Initialize the CLI Manager with the Scanner object
        CLI.Manager cli = new CLI.Manager(scanObj);

        // Start the CLI process using the Scanner object
        cli.start(scanObj);
    }
}
