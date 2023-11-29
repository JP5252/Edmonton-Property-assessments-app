import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * this class holds the methods for acquiring the statistics of the property assessments
 */
public class PropertyAssessments {
    private final List<Account> accountList;

    /**
     * constructor for PropertyAssessments, takes in an account map and creates a list of all the Account objects
     * from values then sorts it by assessment value for stat gathering
     *
     * @param accountMap the map of Account objects.
     */
    public PropertyAssessments(Map<Integer, Account> accountMap) {
        //create a list of all the account sorted by assessment values for stat gathering
        accountList = new ArrayList<>(List.copyOf(accountMap.values()));
        // Sort data based on assessment values
        Collections.sort(accountList,
                (a, b) -> Integer.compare(a.getValue(), b.getValue()));

    }

    /**
     * this gets the property stats of all the given property assessments
     *
     * @return string of property assessment stats.
     */
    public String getPropertyStats(){
        String stats = "";
        stats = new GetStats(accountList).toString();
        return stats;
    }

    /**
     * this gets all the property assessments for every property in a given neighborhood
     *
     * @param nbrHood the neighborhood to search for
     * @return the string of property assessment stats for the given neighborhood
     */
    public String getNbrHoodStats(String nbrHood) {
        List<Account> inNbrHood = new ArrayList<>();
        String stats = "";
        for (Account d : accountList) {
            if ((d.getNbrHood().getName()).equals(nbrHood.toUpperCase())) {
                inNbrHood.add(d);
            }
        }

        if (!inNbrHood.isEmpty()) {
            stats = new GetStats(inNbrHood).toString();
        }
        return stats;
    }

    /**
     * this gets the property assessment stats from a given property assessment class
     *
     * @param aClass the property assessment class we are looking at
     * @return A string of the property assessment class statistics
     */
    public String getClassStats(String aClass) {
        List<Account> inAClass = new ArrayList<>();
        String stats = "";
        for (Account d : accountList) {
            if (    (d.getAssessment().getClass1()).contains(aClass.toUpperCase()) ||
                    (d.getAssessment().getClass2()).contains(aClass.toUpperCase()) ||
                    (d.getAssessment().getClass3()).contains(aClass.toUpperCase())
            ) {
                inAClass.add(d);
            }
        }
        if (!inAClass.isEmpty()) {
            stats = new GetStats(inAClass).toString();
        }
        return stats;
    }
}
