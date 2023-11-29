import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

/**
 * this class implements the reading of the csv file and then writing the data
 * objects into a list
 */
public class ParseCSV {
    private final Map<Integer, Account> accountMap = new HashMap<>();
    private final List<String> assessmentClassList = new ArrayList<>();

    /**
     * constructor for parseData that takes in the csv filename and parses the
     * data line by line and returns the parsed data as a list of Data objects
     *
     * @param file the csv file to parse
     */
    public ParseCSV(String file) {
        try {

            Scanner inFile = new Scanner(Paths.get(file));

            Account data;

            inFile.nextLine(); // get rid of the first line

            int counter = 0;
            while (counter <= 100) {
                String line = inFile.nextLine(); // reads line from file
                data = new Account(line, 1);
                // populate the assessment class list
                if (!assessmentClassList.contains(data.getAssessment().getClass1())
                        && !data.getAssessment().getClass1().isEmpty()) {
                    assessmentClassList.add(data.getAssessment().getClass1());
                }
                if (!assessmentClassList.contains(data.getAssessment().getClass2())
                        && !data.getAssessment().getClass2().isEmpty()) {
                    assessmentClassList.add(data.getAssessment().getClass2());
                }
                if (!assessmentClassList.contains(data.getAssessment().getClass3())
                        && !data.getAssessment().getClass3().isEmpty()) {
                    assessmentClassList.add(data.getAssessment().getClass3());
                }
                accountMap.put(data.getAcctNum(), data);
                counter += 1;
            }
            /* this is for running entire file, we just want the first 100

            // parse through file until the end
            while (inFile.hasNextLine()) {
                String line = inFile.nextLine(); // reads line from file
                data = new Account(line);
                accountMap.put(data.getAcctNum(), data);
            }
            */

        } catch (IOException ex) {
            System.err.println("Error: can't open file " + file + ".");
        }
    }

    /**
     * gets the list of Data objects and returns it.
     *
     * @return list of Data objects
     */
    public Map<Integer, Account> getAccountMap() {
        return this.accountMap;
    }

    /**
     * gets the list of assessment classes for use in the combo box in the gui
     *
     * @return A list of all the assessment classes used
     */
    public List<String> getAssessmentClassList() {
        return this.assessmentClassList;
    }
}
