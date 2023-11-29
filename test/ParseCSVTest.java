import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParseCSVTest {

    private ParseCSV parseCSV;
    @BeforeEach
    void setUp() {
        parseCSV = new ParseCSV("Property_Assessment_Data_2022.csv");
    }

    @Test
    void getAccountMap() {
        Map<Integer, Account> accountMap = parseCSV.getAccountMap();

        assertTrue(accountMap.containsKey(1103530));

    }
}