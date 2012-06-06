package zebrapuzzle.inputfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that implement file parser  for puzzle rules
 * For use necessary set input file name
 * invoke parse() for handle file
 * after ger result through getters
 * <p/>
 * User: mf
 * Date: 06.06.12
 * Time: 12:42
 */
public class CInputFileParser implements IInputFileParser {
    /**
     * File which will be parse
     */
    private String sFileName;

    private int iNumberOfHouses;

    /**
     * Contain all parsed column pieces
     * Order in ArrayList equal order lines in file
     * Map<Command<FieldName,Value>>
     */
    private ArrayList<Map<String, Map<String, String>>> columnPieces;

    /**
     * Default constructor
     */
    public CInputFileParser() {
        columnPieces = new ArrayList<>();
    }

    /**
     * Constructor receive file name for handle
     *
     * @param sFileName file for parsing
     */
    public CInputFileParser(String sFileName) {
        this();
        setFileName(sFileName);
    }

    /**
     * Perform parsing on the file
     */
    @Override
    public void parse() {
        String sFileLine = "";
        try (BufferedReader fileBuffer = getFileContext()) {
            iNumberOfHouses = Integer.parseInt(fileBuffer.readLine());// get the first line from file
            while ((sFileLine = fileBuffer.readLine()) != null) {
                String[] asLineWorlds = sFileLine.split(";");
                Map<String, Map<String, String>> rule = new HashMap<>(1);
                Map<String, String> values = new HashMap<>((asLineWorlds.length - 1) / 2);
                rule.put(asLineWorlds[0], values);
                for (int index = 1; index < asLineWorlds.length; index = index + 2) {
                    values.put(asLineWorlds[index], asLineWorlds[index + 1]);
                }
                columnPieces.add(rule);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Input file can not be read");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("First line of the file not integer as expected");
            e.printStackTrace();
        }


    }

    @Override
    public void setFileName(String sFileName) {
        this.sFileName = sFileName;
    }

    public int getNumberOfHouses() {
        return iNumberOfHouses;
    }

    @Override
    public ArrayList<Map<String, Map<String, String>>> getColumnPieces() {
        return columnPieces;
    }

    /**
     * Get the file context
     *
     * @return fileBuffer
     * @throws FileNotFoundException
     */
    private BufferedReader getFileContext() throws FileNotFoundException {
        return new BufferedReader(new FileReader(sFileName));
    }
}
