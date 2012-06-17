package zebrapuzzle.parse;

import zebrapuzzle.resolve.rules.CRule;

import java.util.List;

public class CFakeRuleParser implements IRuleParser {
    public int numberOfHouses;
    public List<CRule> rules;
    public String[] properties;
    public String[][] values;

    @Override
    public int getNumberOfHouses() {
        return numberOfHouses;
    }

    @Override
    public List<CRule> getRules() {
        return rules;
    }

    @Override
    public String[] getProperties() {
        return properties;
    }

    @Override
    public String[][] getValues() {
        return values;
    }
}
