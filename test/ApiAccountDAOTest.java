import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ApiAccountDAOTest {

    AccountDao accountDao;
    @BeforeEach
    void setUp() {
        accountDao = new ApiAccountDAO();
    }

    @Test
    void getByAccountNumber() {

        Account account = accountDao.getByAccountNumber(1017870);
        assertNotNull(account);
        assertEquals(1017870, account.getAcctNum());
    }

    @Test
    void getByAddress() {
        List<Account> accounts = accountDao.getByAddress("44 willow");
        assertNotNull(accounts);
        assertFalse(accounts.isEmpty());
    }

    @Test
    void getByNeighborhood() {
        List<Account> accounts = accountDao.getByNeighborhood("westridge");
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
        List<Account> accounts = accountDao.searchByCriteria(1017870, "44 willow", "westridge", "Residential", 50000, 600000, "Y");
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