package zebrapuzzle.inputfile;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mf
 * Date: 06.06.12
 * Time: 16:29
 */
public interface IInputFileParser {
    /**
     * Begin parsing process
     */
    public void parse();

    /**
     * Set file name
     *
     * @param sFileName
     */
    public void setFileName(String sFileName);

    /**
     * Return parse result
     *
     * @return
     */
    public ArrayList<Map<String, Map<String, String>>> getColumnPieces();

    /**
     * Get number houses in the task
     */
    public int getNumberOfHouses();
}
