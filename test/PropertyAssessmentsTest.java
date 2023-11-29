import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test file for the PropertyAssessments class, tests all methods in the PropertyAssessments
 */
class PropertyAssessmentsTest {

    private ParseCSV parseData = new ParseCSV("Property_Assessment_Data_2022.csv");
    private Map<Integer, Account> accountMap = parseData.getAccountMap();
    private PropertyAssessments propertyAssessments = new PropertyAssessments(accountMap);

    @Test
    void getPropertyStats() {
        assertEquals("n = 416044\n" +
                "min = $0\n" +
                "max = $989492500\n" +
                "range = $989492500\n" +
                "mean = $453044\n" +
                "median = $322000\n", propertyAssessments.getPropertyStats());
    }

    @Test
    void getNbrHoodStats() {
        assertEquals("n = 1208\n" +
                "min = $3000\n" +
                "max = $33608000\n" +
                "range = $33605000\n" +
                "mean = $447847\n" +
                "median = $439000\n", propertyAssessments.getNbrHoodStats("granville"));
    }

    @Test
    void getClassStats() {
        assertEquals("n = 393181\n" +
                "min = $0\n" +
                "max = $231061000\n" +
                "range = $231061000\n" +
                "mean = $364139\n" +
                "median = $322000\n", propertyAssessments.getClassStats("residential"));
    }
}