package zebrapuzzle;

import zebrapuzzle.parse.CCVSRuleParser;
import zebrapuzzle.resolve.CResolver;
import zebrapuzzle.save.CXmlResultSaver;
import zebrapuzzle.utils.CUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
        CResolver resolver = new CResolver(new CCVSRuleParser(new FileInputStream(inputFileName)));
        resolver.saveResult(new CXmlResultSaver(new FileOutputStream(outputFileName)));
    }
}
