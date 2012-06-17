package zebrapuzzle.save;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class CXmlResultSaverTest {
    @Test
    public void dataAreInOutput() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        CXmlResultSaver saver = new CXmlResultSaver(buffer);
        saver.save(new String[]{"nationality"}, new String[][]{{"English"}});
        saver.close();

        String text = getText(buffer);
        assertTrue(text.contains("nationality=\"English\""));
    }

    @Test
    public void saveFailsAfterClose() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        CXmlResultSaver saver = new CXmlResultSaver(buffer);
        saver.close();

        assertFalse(saver.save(new String[0], new String[0][0]));
    }

    @Test
    public void closeOkAfterClose() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        CXmlResultSaver saver = new CXmlResultSaver(buffer);
        saver.close();
        saver.close();
    }

    private String getText(ByteArrayOutputStream buffer) {
        try {
            return buffer.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
