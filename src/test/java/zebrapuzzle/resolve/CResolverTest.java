package zebrapuzzle.resolve;

import org.junit.Test;
import zebrapuzzle.parse.CFakeRuleParser;
import zebrapuzzle.resolve.rules.CRule;
import zebrapuzzle.save.CFakeResultSaver;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static junit.framework.Assert.assertTrue;

public class CResolverTest {
    @Test
    public void oneHouse() {
        CFakeRuleParser parser = new CFakeRuleParser();
        parser.numberOfHouses = 1;
        parser.rules = emptyList();
        parser.properties = new String[]{"nationality"};
        parser.values = new String[][]{{"English"}};
        CResolver resolver = new CResolver(parser);
        CFakeResultSaver saver = new CFakeResultSaver();
        resolver.saveResult(saver);

        assertTrue(Arrays.deepEquals(new String[]{"nationality"}, saver.properties));
        assertTrue(Arrays.deepEquals(new String[][]{{"English"}}, saver.result));
    }

    @Test
    public void twoHousesWithSameRules() {
        CFakeRuleParser parser = new CFakeRuleParser();
        parser.numberOfHouses = 2;
        parser.rules = asList(
                rule(CRule.Type.SAME, 0, 0, 1, 1),
                rule(CRule.Type.SAME, 1, 0, -1, 0)
        );
        parser.properties = new String[]{"nationality", "color"};
        parser.values = new String[][]{
                {"English", "Spaniard"},
                {"Red", "Green"}
        };
        CResolver resolver = new CResolver(parser);
        CFakeResultSaver saver = new CFakeResultSaver();
        resolver.saveResult(saver);

        assertTrue(Arrays.deepEquals(new String[]{"nationality", "color"}, saver.properties));
        assertTrue(Arrays.deepEquals(new String[][]{
                {"Spaniard", "Red"},
                {"English", "Green"}
        }, saver.result));
    }

    @Test
    public void twoHousesWithNextRules() {
        CFakeRuleParser parser = new CFakeRuleParser();
        parser.numberOfHouses = 2;
        parser.rules = asList(
                rule(CRule.Type.NEXT_TO, 0, 0, 1, 0),
                rule(CRule.Type.NEXT_TO, 1, 0, -1, 1)
        );
        parser.properties = new String[]{"nationality", "color"};
        parser.values = new String[][]{
                {"English", "Spaniard"},
                {"Red", "Green"}
        };
        CResolver resolver = new CResolver(parser);
        CFakeResultSaver saver = new CFakeResultSaver();
        resolver.saveResult(saver);

        assertTrue(Arrays.deepEquals(new String[]{"nationality", "color"}, saver.properties));
        assertTrue(Arrays.deepEquals(new String[][]{
                {"Spaniard", "Red"},
                {"English", "Green"}
        }, saver.result));
    }

    @Test
    public void twoHousesWithLeftRules() {
        CFakeRuleParser parser = new CFakeRuleParser();
        parser.numberOfHouses = 2;
        parser.rules = asList(
                rule(CRule.Type.TO_THE_LEFT_OF, 0, 1, 1, 1),
                rule(CRule.Type.TO_THE_LEFT_OF, 1, 0, -1, 1)
        );
        parser.properties = new String[]{"nationality", "color"};
        parser.values = new String[][]{
                {"English", "Spaniard"},
                {"Red", "Green"}
        };
        CResolver resolver = new CResolver(parser);
        CFakeResultSaver saver = new CFakeResultSaver();
        resolver.saveResult(saver);

        assertTrue(Arrays.deepEquals(new String[]{"nationality", "color"}, saver.properties));
        assertTrue(Arrays.deepEquals(new String[][]{
                {"Spaniard", "Red"},
                {"English", "Green"}
        }, saver.result));
    }

    @Test
    public void twoHousesWithRightRules() {
        CFakeRuleParser parser = new CFakeRuleParser();
        parser.numberOfHouses = 2;
        parser.rules = asList(
                rule(CRule.Type.TO_THE_RIGHT_OF, 0, 0, 1, 0),
                rule(CRule.Type.TO_THE_RIGHT_OF, 1, 1, -1, 0)
        );
        parser.properties = new String[]{"nationality", "color"};
        parser.values = new String[][]{
                {"English", "Spaniard"},
                {"Red", "Green"}
        };
        CResolver resolver = new CResolver(parser);
        CFakeResultSaver saver = new CFakeResultSaver();
        resolver.saveResult(saver);

        assertTrue(Arrays.deepEquals(new String[]{"nationality", "color"}, saver.properties));
        assertTrue(Arrays.deepEquals(new String[][]{
                {"Spaniard", "Red"},
                {"English", "Green"}
        }, saver.result));
    }

    private CRule rule(CRule.Type type, int sourceProperty, int sourceValue, int targetProperty, int targetValue) {
        CRule newRule = new CRule();
        newRule.type = type;
        newRule.sourceProperty = sourceProperty;
        newRule.sourceValue = sourceValue;
        newRule.targetProperty = targetProperty;
        newRule.targetValue = targetValue;
        return newRule;
    }
}
