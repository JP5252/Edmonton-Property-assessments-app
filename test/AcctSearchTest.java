import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class AcctSearchTest {

    @Test
    public void testAcctSearchWithValidAccountNumber() {
        // Create sample data for the test
        Map<Integer, Account> data = new HashMap<>();
        Account account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        data.put(account.getAcctNum(), account);

        // Perform the search
        AcctSearch acctSearch = new AcctSearch(data, account.getAcctNum());

        // Validate the results
        assertEquals("Account Number = " + account.getAcctNum() +
                "\nAddress = " + account.getAddress().toString() +
                "\nAssessed value = $" + account.getValue() +
                "\nAssessment class = " + account.getAssessment().getAssessmentList() +
                "\nNeighborhood = " + account.getNbrHood().toString() +
                "\nLocation = " + account.getLocation().toString() + "\n", acctSearch.toString());
    }

    @Test
    public void testAcctSearchWithInvalidAccountNumber() {
        // Create sample data for the test
        Map<Integer, Account> data = new HashMap<>();
        Account account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        data.put(account.getAcctNum(), account);

        // Perform the search with an invalid account number
        AcctSearch acctSearch = new AcctSearch(data, 123456);

        // Validate the results
        assertEquals("Sorry, account number not found", acctSearch.toString());
    }
}