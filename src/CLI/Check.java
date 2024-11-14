package CLI;

import java.util.List;
import java.util.Locale;

public class Check {
    enum Response {
        YES,
        NO,
        UNKNOWN,
    }

    public static <T> boolean isValidAnswer (int choice, T[] arr) {
        return choice > 0 && choice <= arr.length;
    }

    public static <T> boolean isValidAnswer (int choice, List<T> list) {
        return choice > 0 && choice <= list.size();
    }

    public static Response getResponse(String answer) {
        var normalizedChoice = answer.toLowerCase(Locale.ROOT);
        return switch (normalizedChoice) {
            case "y", "yes" -> Response.YES;
            case "n", "no" -> Response.NO;
            default -> Response.UNKNOWN;
        };
    }

}