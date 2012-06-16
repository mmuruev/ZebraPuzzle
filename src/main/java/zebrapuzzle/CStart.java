package zebrapuzzle;

import zebrapuzzle.parse.CCVSRuleParser;
import zebrapuzzle.parse.IRuleParser;
import zebrapuzzle.resolve.CResolver;
import zebrapuzzle.resolve.rules.CRule;
import zebrapuzzle.utils.CUtils;

import java.io.FileInputStream;
import java.io.IOException;
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
    public static void main(final String[] args) throws IOException {
        String inputFileName = CUtils.getArg(args, 0, INPUT_FILE_DEFAULT_NAME);
        String outputFileName = CUtils.getArg(args, 1, OUTPUT_FILE_DEFAULT_NAME);
        IRuleParser parser = new CCVSRuleParser(new FileInputStream(inputFileName));
        List<CRule> rules = parser.getRules();
        int iNumberOfHouses = parser.getNumberOfHouses();

        CResolver resolver = new CResolver(iNumberOfHouses);
        resolver.setTablePropertyNames(parser.getProperties());
        resolver.setSource(rules);
        boolean result = resolver.find();
        ArrayList<Map<String, String>> table = resolver.getTableResult();
        System.out.println(iNumberOfHouses);
    }
}
