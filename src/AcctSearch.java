import java.util.List;
import java.util.Map;

/**
 * this class implements search by account number
 */
public class AcctSearch {
    private int acctNum = -1;
    private String address;
    private int value;
    private List<String> assessmentClass;
    private String neighborhood;
    private String location;

    /**
     * constructs the data from the search results of the account number
     *
     * @param data    a list of data objects
     * @param acctNum the account number we are searching for
     */
    public AcctSearch(Map<Integer, Account> data, int acctNum) {

        if (data.containsKey(acctNum)) {
            Account account = data.get(acctNum);
            this.acctNum = acctNum;
            this.address = account.getAddress().toString();
            this.value = account.getValue();
            this.assessmentClass = account.getAssessment().getAssessmentList();
            this.neighborhood = account.getNbrHood().toString();
            this.location = account.getLocation().toString();
        }
    }

    /**
     * override of toString method for the acctSearch class
     *
     * @return The account information if acctNum is found, otherwise account not found
     */
    public String toString() {
        if (this.acctNum != -1) {
            return "Account Number = " + this.acctNum +
                    "\nAddress = " + this.address +
                    "\nAssessed value = $" + this.value +
                    "\nAssessment class = " + this.assessmentClass +
                    "\nNeighborhood = " + this.neighborhood +
                    "\nLocation = " + this.location + "\n";
        } else
            return "Sorry, account number not found";
    }
}
