package zebrapuzzle.save;

public class CFakeResultSaver implements IResultSaver {
    public String[] properties;
    public String[][] result;

    @Override
    public boolean save(String[] properties, String[][] result) {
        this.properties = properties;
        this.result = result;
        return true;
    }

    @Override
    public void close() {
    }
}
