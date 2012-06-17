package zebrapuzzle.save;

public interface IResultSaver {
    public boolean save(String[] properties, String[][] result);

    public void close();
}
