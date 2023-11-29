import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetStatsTest {

    @Test
    public void testGetStatsWithValidData() {
        // Create sample data for the test (make sure it's sorted)
        List<Account> data = new ArrayList<>();
        Account account1 = new Account("1066158,1000,10000,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        Account account2 = new Account("1066158,1000,20000,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        data.add(account1);
        data.add(account2);
        // Add more accounts as needed to create a sorted list

        // Perform the statistics calculation
        GetStats getStats = new GetStats(data);

        // Validate the results
        assertEquals("n = " + data.size() +
                "\nmin = $" + data.get(0).getValue() +
                "\nmax = $" + data.get(data.size() - 1).getValue() +
                "\nrange = $" + (data.get(data.size() - 1).getValue() - data.get(0).getValue()) +
                "\nmean = $" + calculateMean(data) +
                "\nmedian = $" + calculateMedian(data) + "\n", getStats.toString());
    }

    @Test
    public void testGetStatsWithEmptyData() {
        // Create an empty list for the test
        List<Account> data = new ArrayList<>();

        // Perform the statistics calculation with empty data
        GetStats getStats = new GetStats(data);

        // Validate the results
        assertEquals("", getStats.toString());
    }

    // helper to calculate mean for test
    private long calculateMean(List<Account> data) {
        if (data.isEmpty()) {
            return 0;
        }

        long sum = 0;
        for (Account account : data) {
            sum += account.getValue();
        }

        return sum / data.size();
    }

    // helper to calculate median for test
    private int calculateMedian(List<Account> data) {
        if (data.isEmpty()) {
            return 0;
        }

        int mid = data.size() / 2;
        if (data.size() % 2 == 0) {
            // If even, average of middle two values
            return (data.get(mid).getValue() + data.get(mid - 1).getValue()) / 2;
        } else {
            // If odd, middle value
            return data.get(mid).getValue();
        }
    }
}