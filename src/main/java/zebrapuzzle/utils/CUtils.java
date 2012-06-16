package zebrapuzzle.utils;

public class CUtils {
    public static String getArg(String[] args, int position, String defaultValue) {
        if (args.length > position && !args[position].isEmpty()) {
            return args[position];
        }
        return defaultValue;
    }
}
