import java.util.Scanner;
public class Main {

    public static void main (String[] args) {

        Scanner scanObj = new Scanner(System.in);
        CLI.Manager cli = new CLI.Manager(scanObj);
        cli.start(scanObj);
 //! Remove the comment after implementing GUI   (to make same exe file run GUI/CLI)
//        if (args.length > 1 && args[1].equals("--cli")) {
//            Scanner scanObj = new Scanner(System.in);
//            Manager cli = new Manager(scanObj);
//            cli.start(scanObj);
//        } else {
//            GUI.Manager gui = new GUI.Manager();
//        }
    }
}
