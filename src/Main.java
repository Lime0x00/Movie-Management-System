import CLI.Manager;
import java.util.Scanner;
public class Main {

    public static void main (String[] args) {

        Scanner scanObj = new Scanner(System.in);
        Manager cli = new Manager(scanObj);
        cli.start(scanObj);

//        if (args.length > 1 && args[1].equals("--cli")) {
//            Scanner scanObj = new Scanner(System.in);
//            Manager cli = new Manager(scanObj);
//            cli.start(scanObj);
//        } else {
//            //USE MANAGER IN GUI
//        }
//

    }
}
