/**
 * This class holds information about the neighborhood and ward of a property
 */
public class NbrHood {
    private int ID;
    private final String name;
    private final String ward;

    /**
     * constructs the nbrHood object with the given ID, name and ward.
     *
     * @param ID   the nbrhoodID as an int.
     * @param name the nbrhood as a String.
     * @param ward the ward as a String.
     */
    public NbrHood(String ID, String name, String ward) {
        // convert ID from string to int
        try {
            this.ID = Integer.parseInt(ID);
        } catch (NumberFormatException ex) {
            this.ID = 0;
        }
        this.name = name;
        this.ward = ward;
    }

    /**
     * gets the ID from nbrHood object.
     *
     * @return the nieghborhood ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * gets the name from nbrHood object.
     *
     * @return the name of the neighborhood
     */
    public String getName() {
        return this.name;
    }

    /**
     * gets the ward form the nbrHood object.
     *
     * @return the ward
     */
    public String getWard() {
        return this.ward;
    }

    /**
     * override of toString method for nbrHood
     *
     * @return the neighborhood and ward as a string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // check if suite value is empty
        if (!this.name.isEmpty())
            sb.append(this.name);
        // check if ward num is empty
        if (!this.ward.isEmpty())
            sb.append(" (").append(this.ward).append(")");
        return sb.toString();
    }
}
