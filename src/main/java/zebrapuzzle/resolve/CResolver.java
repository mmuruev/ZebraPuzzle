package zebrapuzzle.resolve;

import zebrapuzzle.parse.IRuleParser;
import zebrapuzzle.resolve.generator.CPermutator;
import zebrapuzzle.resolve.rules.CRule;
import zebrapuzzle.save.IResultSaver;

import java.util.ArrayList;
import java.util.List;

import static zebrapuzzle.parse.IRuleParser.POSITION_INDEX;

/**
 * Class for felling tableResult
 * User: mf
 * Date: 06.06.12
 * Time: 19:51
 */
public class CResolver {
    /**
     * Contain all parsed column pieces
     * Order in List equal order lines in file
     * Map<Command<FieldName,Value>>
     */
    private List<CRule> rules;
    private List<int[]> result = new ArrayList<>();
    private String[] properties;
    private String[][] values;
    private int numberOfHouses;
    private List<int[]> permutations;

    public CResolver(IRuleParser parser) {
        numberOfHouses = parser.getNumberOfHouses();
        rules = parser.getRules();
        properties = parser.getProperties();
        values = parser.getValues();
        permutations = CPermutator.generate(numberOfHouses);
        find();
    }

    public void saveResult(IResultSaver saver) {
        saver.save(properties, getResult());
        saver.close();
    }

    private boolean find() {
        for (int[] permutation : permutations) {
            result.add(permutation);
            if (!checkRules()) {
                result.remove(result.size() - 1);
                continue;
            }
            if (result.size() == properties.length || find()) {
                return true;
            }
            result.remove(result.size() - 1);
        }
        return false;
    }

    private boolean checkRules() {
        for (CRule rule : rules) {
            if (rule.sourceProperty >= result.size() || rule.targetProperty >= result.size()) {
                continue;
            }

            int sourcePosition = getPosition(rule.sourceProperty, rule.sourceValue);
            int targetPosition = getPosition(rule.targetProperty, rule.targetValue);
            switch (rule.type) {
                case SAME:
                    if (sourcePosition != targetPosition) {
                        return false;
                    }
                    break;
                case NEXT_TO:
                    if (sourcePosition != targetPosition - 1 && sourcePosition != targetPosition + 1) {
                        return false;
                    }
                    break;
                case TO_THE_LEFT_OF:
                    if (sourcePosition != targetPosition - 1) {
                        return false;
                    }
                    break;
                case TO_THE_RIGHT_OF:
                    if (sourcePosition != targetPosition + 1) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private int getPosition(int property, int value) {
        if (property == POSITION_INDEX) {
            return value;
        }

        int[] values = result.get(property);
        for (int index = 0; index < values.length; index++) {
            if (values[index] == value) {
                return index;
            }
        }
        return -2;
    }

    private String[][] getResult() {
        if (result == null || result.isEmpty()) {
            return null;
        }

        String[][] houses = new String[numberOfHouses][];
        for (int houseIndex = 0; houseIndex < numberOfHouses; houseIndex++) {
            houses[houseIndex] = new String[result.size()];
            for (int propertyIndex = 0; propertyIndex < result.size(); propertyIndex++) {
                houses[houseIndex][propertyIndex] = values[propertyIndex][result.get(propertyIndex)[houseIndex]];
            }
        }
        return houses;
    }
}
