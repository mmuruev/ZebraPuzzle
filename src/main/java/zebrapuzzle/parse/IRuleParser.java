package zebrapuzzle.parse;

import zebrapuzzle.resolve.rules.CRule;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mf
 * Date: 06.06.12
 * Time: 16:29
 */
public interface IRuleParser {
    /**
     * N/A value for rule
     */
    public static final int POSITION_INDEX = -1;

    /**
     * Get number houses in the task
     */
    public int getNumberOfHouses();

    /**
     * Return parse result
     */
    public List<CRule> getRules();

    /**
     * Get all field names.
     */
    public String[] getProperties();

    public String[][] getValues();
}
