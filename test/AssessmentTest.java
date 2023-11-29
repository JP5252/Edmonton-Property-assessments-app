import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {

    private Account account;
    private Assessment assessment;
    private List<String> assessmentList = new ArrayList<>(List.of("RESIDENTIAL 50.0", "COMMERCIAL 48.0", "OTHER RESIDENTIAL 2.0"));
    private List<String> notAssessmentList = new ArrayList<>(List.of("RESIDENTIAL 70.0", "COMMERCIAL 48.0", "OTHER RESIDENTIAL 2.0"));

    @BeforeEach
    void setUp() {
        account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        assessment = account.getAssessment();
    }


    @Test
    void getPercent1() {
        assertEquals(50, assessment.getPercent1());
        assertNotEquals(60, assessment.getPercent1());
    }

    @Test
    void getPercent2() {
        assertEquals(48, assessment.getPercent2());
        assertNotEquals(8, assessment.getPercent2());
    }

    @Test
    void getPercent3() {
        assertEquals(2, assessment.getPercent3());
        assertNotEquals(5, assessment.getPercent3());
    }

    @Test
    void getClass1() {
        assertEquals("RESIDENTIAL", assessment.getClass1());
        assertNotEquals("COMMERCIAL", assessment.getClass1());
    }

    @Test
    void getClass2() {
        assertEquals("COMMERCIAL", assessment.getClass2());
        assertNotEquals("RESIDENTIAL", assessment.getClass2());
    }

    @Test
    void getClass3() {
        assertEquals("OTHER RESIDENTIAL", assessment.getClass3());
        assertNotEquals("COMMERCIAL", assessment.getClass3());
    }

    @Test
    void getAssessmentList() {
        assertEquals(assessmentList, assessment.getAssessmentList());
        assertNotEquals(notAssessmentList, assessment.getAssessmentList());
    }
}