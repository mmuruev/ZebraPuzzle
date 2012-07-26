package zebrapuzzle.save;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class CTextResultSaver implements IResultSaver {
    private static final Charset ENCODING = Charset.forName("UTF-8");

    private PrintWriter resultStream;

    public CTextResultSaver(OutputStream resultStream) {
        this.resultStream = new PrintWriter(new OutputStreamWriter(resultStream, ENCODING));
    }

    @Override
    public boolean save(String[] properties, String[][] result) {
        if (resultStream == null) {
            return false;
        }

        for (String property : properties) {
            resultStream.print("   " + property);
        }
        resultStream.println();

        for (int house = 0; house < result[0].length; house++) {
            resultStream.print((house + 1) + ":");
            for (String value : result[house]) {
                resultStream.print(" " + value + "  ");
            }
            resultStream.println();
        }
        return true;
    }

    @Override
    public void close() {
        if (resultStream == null) {
            return;
        }

        resultStream.close();
        resultStream = null;
    }
}
