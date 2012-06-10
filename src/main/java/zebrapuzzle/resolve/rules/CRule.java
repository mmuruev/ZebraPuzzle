package zebrapuzzle.resolve.rules;

/**
 * Class contain one rule
 * User: mf
 * Date: 10.06.12
 * Time: 12:59
 */

public class CRule {
    public enum Relation {
        SAME, TO_THE_LEFT_OF, NEXT_TO
    }

    public Relation relation;
    public int sourceProperty;
    public int sourceValue;
    public int targetProperty;
    public int targetValue;
}

