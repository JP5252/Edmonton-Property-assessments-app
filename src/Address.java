/**
 * This class holds variables with information about the address of a property
 */
public class Address {
    private int suite;
    private int houseNum;
    private final String streetName;
    private final String garage;

    /**
     * constructs an address object with provided suite, house number, street
     * name and garage information.
     *
     * @param suite      the suite number as a string (gets converted to int).
     * @param houseNum   the house number as a string (gets converted to int).
     * @param streetName the street name as a string.
     * @param garage     the garage information.
     */
    public Address(String suite, String houseNum, String streetName, String garage) {
        // convert suite from string to int
        try {
            this.suite = Integer.parseInt(suite);
        } catch (NumberFormatException ex) {
            this.suite = 0;
        }
        // convert houseNum from string to int
        try {
            this.houseNum = Integer.parseInt(houseNum);
        } catch (NumberFormatException ex) {
            this.houseNum = 0;
        }
        this.streetName = streetName;
        this.garage = garage;
    }

    /**
     * Gets the suite number of the address.
     *
     * @return the suite number.
     */
    public int getSuite() {
        return this.suite;
    }

    /**
     * get the house number of the address.
     *
     * @return the house number.
     */
    public int getHouseNum() {
        return this.houseNum;
    }

    /**
     * gets the street name of the address
     *
     * @return the street name
     */
    public String getStreetName() {
        return this.streetName;
    }

    /**
     * gets the garage information of the address
     *
     * @return the garage information
     */
    public String getGarage() {
        return this.garage;
    }

    /**
     * override of toString method for Address
     *
     * @return the address as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // check if suite value is empty
        if (this.suite != 0)
            sb.append(this.suite).append(" ");
        // check if house num is empty
        if (this.houseNum != 0)
            sb.append(this.houseNum).append(" ");
        // check if street name is empty
        if (!this.streetName.isEmpty())
            sb.append(this.streetName);
        return sb.toString();
    }
}
