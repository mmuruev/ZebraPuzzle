package zebrapuzzle.resolve;

import zebrapuzzle.resolve.generator.CPermutator;
import zebrapuzzle.resolve.rules.CRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static zebrapuzzle.parse.IRuleParser.POSITION_INDEX;

/**
 * Class for felling tableResult
 * User: mf
 * Date: 06.06.12
 * Time: 19:51
 */
public class CResolver {
    private static final String POSITION_KEY = "position";
    private static final String SAME = "SAME";
    private static final String TO_THE_LEFT_OF = "TO_THE_LEFT_OF";
    private static final String NEXT_TO = "NEXT_TO";

    private int iNumberOfHouses;

    private ArrayList<Map<String, String>> tableResult;
    /**
     * Array represent the tableResult
     * where asPermTemp[position][KeyIndex]
     */
    private int[][] asPermTemp;
    /**
     * Array contain property names
     */
    private String[] tableProperty;


    /**
     * Contain all parsed column pieces
     * Order in List equal order lines in file
     * Map<Command<FieldName,Value>>
     */
    private List<CRule> rules;

    public CResolver() {
        tableResult = new ArrayList<>();
    }

    public CResolver(int iNumberOfHouses) {
        this.iNumberOfHouses = iNumberOfHouses;
        tableResult = new ArrayList<>(iNumberOfHouses);

    }

    public boolean find() {
        initArray(tableResult.size(), tableProperty.length); // todo wrong tableResult.size() as size value

        // setTableValuesNames(rules);
        List<int[]> indexes = CPermutator.generate(tableProperty.length);
        boolean result = true;
        for (CRule rule : rules) {
            result &= rulesEngine(rule, asPermTemp);
        }
        return result;
    }

    public boolean rulesEngine(CRule rule, int asPermTemp[][]) {
        boolean result = false;
        for (int position = 0; position < asPermTemp.length; position++) {


            switch (rule.type) {
                case SAME: // only equal positions
                {
                    boolean target = true;
                    if (rule.targetProperty != POSITION_INDEX)  // for no pair
                    {
                        target = asPermTemp[position][rule.targetProperty] == rule.targetValue;
                    }
                    result = asPermTemp[position][rule.sourceProperty] == rule.sourceValue && target;
                    break;
                }

                case NEXT_TO: // -1/+1
                {
                    boolean target = false;
                    if (position < (asPermTemp.length - 1)) {
                        target = asPermTemp[position + 1][rule.targetProperty] == rule.targetValue;
                    }
                    if (!target && position > 0) {
                        target = asPermTemp[position - 1][rule.targetProperty] == rule.targetValue;
                    }
                    result = target && asPermTemp[position][rule.sourceProperty] == rule.sourceValue;
                    break;
                }
                case TO_THE_LEFT_OF: // -1
                {
                    boolean target = false;
                    if (position > 0) {
                        target = asPermTemp[position - 1][rule.targetProperty] == rule.targetValue;
                    }
                    result = target && asPermTemp[position][rule.sourceProperty] == rule.sourceValue;
                    break;
                }
            }
        }
        return result;
    }

    private void initArray(int iPositions, int iKeyMax) {
        asPermTemp = new int[iPositions][iKeyMax - 1]; // todo keyMax - position name because position now index
    }

    public ArrayList<Map<String, String>> getTableResult() {
        return tableResult;
    }

    public void setNumberOfHouses(int iNumberOfHouses) {
        this.iNumberOfHouses = iNumberOfHouses;
    }

    public void setTablePropertyNames(String[] tableProperty) {
        this.tableProperty = tableProperty;
    }

    private int getIndexByName(String sValue, String[] asSource) {
        for (int position = 0; position < asSource.length; position++) {

            if (tableProperty[position].equals(sValue)) {
                return position;
            }
        }
        return POSITION_INDEX;
    }


    public void setSource(List<CRule> rules) {
        this.rules = rules;
    }
}
