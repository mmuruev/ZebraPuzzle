package zebrapuzzle;

import zebrapuzzle.inputfile.CInputFileParser;
import zebrapuzzle.inputfile.IInputFileParser;
import zebrapuzzle.resolve.CResolver;
import zebrapuzzle.resolve.rules.CRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Main Class program entry point
 * User: mf
 * Date: 06.06.12
 * Time: 12:38
 */
public class CStart {
    private static final String INPUT_FILE_DEFAULT_NAME = "input.txt";
    private static final String OUTPUT_FILE_DEFAULT_NAME = "output.xml";

    /**
     * Main function provide files or will use default values.
     *
     * @param args sourceFile outputFile
     */
    public static void main(final String[] args) {
        String inputFileName = INPUT_FILE_DEFAULT_NAME;
        String outputFileName = OUTPUT_FILE_DEFAULT_NAME;
        if (args.length > 1 && !args[0].isEmpty()) {
            inputFileName = args[0];
        }
        if (args.length > 2 && args[1].isEmpty()) {
            outputFileName = args[1];
        }
        IInputFileParser parser = new CInputFileParser(inputFileName);
        parser.parse();
        List<CRule> rules = parser.getRules();
        int iNumberOfHouses = parser.getNumberOfHouses();

        CResolver resolver = new CResolver(iNumberOfHouses);
        resolver.setTablePropertyNames(parser.getFieldNames());
        resolver.setSource(rules);
        boolean result = resolver.find();
        ArrayList<Map<String, String>> table = resolver.getTableResult();
        System.out.println(iNumberOfHouses);
    }

}
