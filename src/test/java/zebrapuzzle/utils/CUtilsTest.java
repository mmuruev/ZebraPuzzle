package zebrapuzzle.utils;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CUtilsTest {
    @Test
    public void defaultValueIfNoArgumentSpecified() {
        String defaultValue = "default";
        assertEquals(CUtils.getArg(new String[0], 0, defaultValue), defaultValue);
    }

    @Test
    public void argumentIfSpecified() {
        String argument = "argument";
        assertEquals(CUtils.getArg(new String[]{argument}, 0, "default"), argument);
    }
}
