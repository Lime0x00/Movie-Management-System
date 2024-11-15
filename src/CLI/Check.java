package CLI;

import java.util.List;
import java.util.Locale;

public class Check {
    public enum enResponse {
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

    public static enResponse getResponse(String answer) {
        var normalizedChoice = answer.toLowerCase(Locale.ROOT);
        return switch (normalizedChoice) {
            case "y", "yes" -> enResponse.YES;
            case "n", "no" -> enResponse.NO;
            default -> enResponse.UNKNOWN;
        };
    }

}