import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CsvAccountDAOTest {

    AccountDao accountDao;
    @BeforeEach
    void setUp() {
        accountDao = new CsvAccountDAO();
    }

    @Test
    void getByAccountNumber() {

        Account account = accountDao.getByAccountNumber(1097492);
        assertNotNull(account);
        assertEquals(1097492, account.getAcctNum());
    }

    @Test
    void getByAddress() {
        List<Account> accounts = accountDao.getByAddress("92 street");
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getByNeighborhood() {
        List<Account> accounts = accountDao.getByNeighborhood("lago lindo");
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getByAssessmentClass() {
        List<Account> accounts = accountDao.getByAssessmentClass("Residential");
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getByMinValue() {
        List<Account> accounts = accountDao.getByMinValue(50000);
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getByMaxValue() {
        List<Account> accounts = accountDao.getByMaxValue(200000);
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getAll() {
        List<Account> accounts = accountDao.getAll();
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void searchByCriteria() {
        List<Account> accounts = accountDao.searchByCriteria(1097492, "92 street", "lago lindo", "Residential", 50000, 600000);
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getAssessmentClassList() {
        List<Account> accounts = accountDao.getAll();
        List<String> assessmentClasses = accountDao.getAssessmentClassList();
        assertTrue(assessmentClasses.contains("RESIDENTIAL"));
    }
}