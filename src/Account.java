import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this class contains the acctNum and the objects address, nbrHood, assessment
 * and location.
 */
public class Account {
    private int acctNum;
    private int value;
    private String formattedValue;
    private final Address address;
    private final NbrHood nbrHood;
    private Assessment assessment;
    private Location location;
    private String garage;

    /**
     * this constructs the data class out of the given csv line.
     *
     * @param line the line from the csv file
     * @param inputType the type of file that the data came from csv = 1, json = 2
     */
    public Account(String line, int inputType) {
        List<String> lineData = lineToList(line);

        // convert acctNum from string to int
        try {
            this.acctNum = Integer.parseInt(lineData.get(0));

        } catch (NumberFormatException ex) {
            acctNum = 0;
        }
        try {
            this.value = Integer.parseInt(lineData.get(8));

        } catch (NumberFormatException ex) {
            value = 0;
        }
        //now store value in its formatted string
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        currencyFormat.setMaximumFractionDigits(0);
        this.formattedValue = currencyFormat.format(value);

        // send suite, houseNum, streetName and garage to address
        this.address = new Address(lineData.get(1), lineData.get(2), lineData.get(3), lineData.get(4));
        this.garage = lineData.get(4).strip();

        // send nbrhoodID, nbrhood and ward to NbrHood
        this.nbrHood = new NbrHood(lineData.get(5), lineData.get(6), lineData.get(7));

        // send percentages and classes to assessment, change order based on inputType
        // if input is csv
        if (inputType == 1) {
            this.assessment = new Assessment(lineData.get(12), lineData.get(13), lineData.get(14),
                    lineData.get(15), lineData.get(16), lineData.get(17));
            // send location data to Location
            this.location = new Location(lineData.get(9), lineData.get(10), lineData.get(11));
        }else if (inputType == 2) {
            //if input is API
            //if there is 3 assessment classes
            if (!lineData.get(17).isEmpty()) {
                this.assessment = new Assessment(lineData.get(13), lineData.get(14), lineData.get(15),
                        lineData.get(16), lineData.get(17), lineData.get(18));
            }else if (!lineData.get(15).isEmpty()) {
                //of there is 2 assessment classes
                this.assessment = new Assessment(lineData.get(13), lineData.get(14), lineData.get(17),
                        lineData.get(15), lineData.get(16), lineData.get(18));
            } else {
                // if there is only 1 assessment classs
                this.assessment = new Assessment(lineData.get(13), lineData.get(15), lineData.get(17),
                        lineData.get(14), lineData.get(16), lineData.get(18));
            }
            StringBuilder sb = new StringBuilder();
            sb.append(lineData.get(11)).append(" ").append(lineData.get(12));
            // send location data to Location
            this.location = new Location(lineData.get(9), lineData.get(10), sb.toString());
        }




    }

    /**
     * this takes in a string and converts it to a list delimited by commas
     *
     * @param line line from csv file as a string
     * @return the data from the line as a list
     */
    private static List<String> lineToList(String line) {
        try (Scanner parser = new Scanner(line).useDelimiter(",")) {
            List<String> lineData = new ArrayList<>();
            while (parser.hasNext()) {
                lineData.add(parser.next());
            }
            /*
             * hasNext() detect newline if the last field is empty and skips it
             * so this adds an empty last field if the size is less than 18
             */
            while (lineData.size() < 19) {
                lineData.add("");
            }
            return lineData;
        }
    }

    /**
     * gets the account number from the data.
     *
     * @return the account number
     */
    public int getAcctNum() {
        return this.acctNum;
    }

    /**
     * gets the garage from the data
     * @return Yes the property has a garage; no otherwise
     */
    public String getGarage() {
        if (garage.equals("Y")) return "Yes";
        if (garage.equals("N")) return "No";
        return garage;
    }

    /**
     * gets the value of the assessment
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
    /**
     * gets the value of the assessment as a formatted string.
     *
     * @return the formatted value
     */
    public String getFormattedValue() {
        return this.formattedValue;
    }
    /**
     * gets the address object from the data.
     *
     * @return the address object
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * gets the nbrHood object from the data.
     *
     * @return the address object
     */
    public NbrHood getNbrHood() {
        return this.nbrHood;
    }

    /**
     * gets the assessment object from the data.
     *
     * @return the assessment object
     */
    public Assessment getAssessment() {
        return this.assessment;
    }

    /**
     * gets the location object from the data.
     *
     * @return the location object
     */
    public Location getLocation() {
        return this.location;
    }
}
