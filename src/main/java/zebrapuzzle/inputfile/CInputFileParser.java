package zebrapuzzle.inputfile;

import zebrapuzzle.resolve.rules.CRule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static zebrapuzzle.inputfile.constant.CCommandConstant.*;

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
    private final int PROPERTY = 1;
    private final int VALUE = 2;

    /**
     * File which will be parse
     */
    private String sFileName;

    private int iNumberOfHouses;

    /**
     * Contain all rules from file
     */
    private List<CRule> rules;
    /**
     * Set all field names
     */
    private Set<String> fieldNames;

    /**
     * Values  a[x][y]="Cat"
     */
    private List<Set<String>> values;


    /**
     * Default constructor
     */
    public CInputFileParser() {
        fieldNames = new HashSet<>();
        rules = new LinkedList<>();
        values = new LinkedList<>();
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
                CRule rule = new CRule();
                switch (asLineWorlds[0]) {
                    case SAME:
                        rule.relation = CRule.Relation.SAME;
                        break;
                    case NEXT_TO:
                        rule.relation = CRule.Relation.NEXT_TO;
                        break;
                    case TO_THE_LEFT_OF:
                        rule.relation = CRule.Relation.TO_THE_LEFT_OF;
                        break;
                }
                if (asLineWorlds.length > 2) {
                    rule.sourceValue = getValueOrderNumber(asLineWorlds[VALUE], asLineWorlds[PROPERTY]);
                }
                if (asLineWorlds.length > 4) {
                    rule.targetValue = getValueOrderNumber(asLineWorlds[VALUE], asLineWorlds[PROPERTY]);
                }
                rules.add(rule);
                fieldNames.remove(POSITION_KEY); // remove position from set
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

    private int getPropertiesOrderNumber(String property) {
        int counter = 0;
        fieldNames.add(property);
        for (String fieldName : fieldNames) {
            if (fieldName.equals(property)) {
                return counter;
            }
            counter++;
        }
        return NA;
    }

    private int getValueOrderNumber(String value, String property) {
        int indexOfProperty = getPropertiesOrderNumber(property);
        if (indexOfProperty == NA) {
            return NA;
        }

        Set<String> valueSet;
        //  if ((valueSet = values.get(indexOfProperty)) == null) {
        valueSet = new HashSet<>();
        values.add(indexOfProperty, valueSet);
        //  }
        valueSet.add(value);
        return indexOfProperty;
    }

    @Override
    public void setFileName(String sFileName) {
        this.sFileName = sFileName;
    }

    /**
     * Set all field names.
     */
    @Override
    public Set<String> getFieldNames() {
        return fieldNames;
    }

    @Override
    public int getNumberOfHouses() {
        return iNumberOfHouses;
    }

    public List<CRule> getRules() {
        return rules;
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