package zebrapuzzle.inputfile;

import zebrapuzzle.resolve.rules.CRule;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mf
 * Date: 06.06.12
 * Time: 16:29
 */
public interface IInputFileParser {
    /**
     * N/A value for rule
     */
    public final int NA = -1;

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
     * Set all field names.
     */
    public Set<String> getFieldNames();

    /**
     * Return parse result
     *
     * @return
     */
    public List<CRule> getRules();

    /**
     * Get number houses in the task
     */
    public int getNumberOfHouses();
}
