package zebrapuzzle.parse;

import org.junit.Test;
import zebrapuzzle.resolve.rules.CRule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: mf
 * Date: 06.06.12
 * Time: 17:20
 */
public class CCVSRuleParserTest {
    private static final Charset ENCODING = Charset.forName("UTF-8");

    @Test
    public void zeroHouses() {
        IRuleParser parser = new CCVSRuleParser(inputStream("0"));
        assertEquals(0, parser.getNumberOfHouses());
    }

    @Test
    public void fiveHouses() {
        IRuleParser parser = new CCVSRuleParser(inputStream("5"));
        assertEquals(5, parser.getNumberOfHouses());
    }

    @Test(expected = NumberFormatException.class)
    public void wrongHouseNumber() {
        new CCVSRuleParser(inputStream("ZERO"));
    }

    @Test
    public void sameType() {
        String text = "0\n" +
                "SAME;nationality;English;color;Red";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(CRule.Type.SAME, parser.getRules().get(0).type);
    }

    @Test
    public void nextType() {
        String text = "0\n" +
                "NEXT_TO;smoke;Chesterfields;pet;Fox";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(CRule.Type.NEXT_TO, parser.getRules().get(0).type);
    }

    @Test
    public void leftType() {
        String text = "0\n" +
                "TO_THE_LEFT_OF;color;Ivory;color;Green";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(CRule.Type.TO_THE_LEFT_OF, parser.getRules().get(0).type);
    }

    @Test
    public void rightType() {
        String text = "0\n" +
                "TO_THE_RIGHT_OF;color;Ivory;color;Green";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(CRule.Type.TO_THE_RIGHT_OF, parser.getRules().get(0).type);
    }

    @Test
    public void differentProperties() {
        String text = "0\n" +
                "SAME;nationality;English;color;Red";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(2, parser.getProperties().length);
        assertEquals("nationality", parser.getProperties()[0]);
        assertEquals("color", parser.getProperties()[1]);
        assertEquals(0, parser.getRules().get(0).sourceProperty);
        assertEquals(1, parser.getRules().get(0).targetProperty);
    }

    @Test
    public void sameProperty() {
        String text = "0\n" +
                "TO_THE_LEFT_OF;color;Ivory;color;Green";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(1, parser.getProperties().length);
        assertEquals("color", parser.getProperties()[0]);
        assertEquals(0, parser.getRules().get(0).sourceProperty);
        assertEquals(0, parser.getRules().get(0).targetProperty);
    }

    @Test
    public void position() {
        String text = "5\n" +
                "SAME;drink;Milk;position;3";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(1, parser.getProperties().length);
        assertEquals(-1, parser.getRules().get(0).targetProperty);
    }

    @Test
    public void differentValuesOfDifferentProperties() {
        String text = "0\n" +
                "SAME;nationality;English;color;Red";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(2, parser.getValues().length);
        assertEquals(1, parser.getValues()[0].length);
        assertEquals("English", parser.getValues()[0][0]);
        assertEquals(1, parser.getValues()[1].length);
        assertEquals("Red", parser.getValues()[1][0]);
        assertEquals(0, parser.getRules().get(0).sourceValue);
        assertEquals(0, parser.getRules().get(0).targetValue);
    }

    @Test
    public void differentValuesOfSameProperty() {
        String text = "0\n" +
                "TO_THE_LEFT_OF;color;Ivory;color;Green";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(1, parser.getValues().length);
        assertEquals(2, parser.getValues()[0].length);
        assertEquals("Ivory", parser.getValues()[0][0]);
        assertEquals("Green", parser.getValues()[0][1]);
        assertEquals(0, parser.getRules().get(0).sourceValue);
        assertEquals(1, parser.getRules().get(0).targetValue);
    }

    @Test
    public void sameValueOfSameProperty() {
        String text = "0\n" +
                "TO_THE_LEFT_OF;color;Ivory;color;Ivory";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(1, parser.getValues().length);
        assertEquals(1, parser.getValues()[0].length);
        assertEquals("Ivory", parser.getValues()[0][0]);
        assertEquals(0, parser.getRules().get(0).sourceValue);
        assertEquals(0, parser.getRules().get(0).targetValue);
    }

    @Test
    public void positionNumber() {
        String text = "5\n" +
                "SAME;drink;Milk;position;3";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(1, parser.getValues().length);
        assertEquals(1, parser.getValues()[0].length);
        assertEquals("Milk", parser.getValues()[0][0]);
        assertEquals(2, parser.getRules().get(0).targetValue);
    }

    @Test
    public void minimalPositionNumber() {
        String text = "5\n" +
                "SAME;drink;Milk;position;1";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(0, parser.getRules().get(0).targetValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooSmallPositionNumber() {
        String text = "5\n" +
                "SAME;drink;Milk;position;0";
        new CCVSRuleParser(inputStream(text));
    }

    @Test
    public void maximalPositionNumber() {
        String text = "5\n" +
                "SAME;drink;Milk;position;5";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(4, parser.getRules().get(0).targetValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void tooBigPositionNumber() {
        String text = "5\n" +
                "SAME;drink;Milk;position;6";
        new CCVSRuleParser(inputStream(text));
    }

    @Test(expected = NumberFormatException.class)
    public void wrongPositionNumber() {
        String text = "5\n" +
                "SAME;drink;Milk;position;THREE";
        new CCVSRuleParser(inputStream(text));
    }

    @Test
    public void noRules() {
        String text = "0";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(0, parser.getRules().size());
    }

    @Test
    public void threeRules() {
        String text = "0\n" +
                "SAME;nationality;English;color;Red\n" +
                "SAME;nationality;Spaniard;pet;Dog\n" +
                "SAME;drink;Coffee;color;Green\n";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(3, parser.getRules().size());
    }

    @Test
    public void incompleteRule() {
        String text = "0\n" +
                "SAME;drink;Water";
        IRuleParser parser = new CCVSRuleParser(inputStream(text));
        assertEquals(1, parser.getProperties().length);
        assertEquals("drink", parser.getProperties()[0]);
        assertEquals(1, parser.getValues().length);
        assertEquals(1, parser.getValues()[0].length);
        assertEquals("Water", parser.getValues()[0][0]);
        assertEquals(0, parser.getRules().size());
    }

    private InputStream inputStream(String text) {
        return new ByteArrayInputStream(text.getBytes(ENCODING));
    }
}
