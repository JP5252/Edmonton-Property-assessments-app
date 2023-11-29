import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
    }

    @Test
    void getAcctNum() {
        assertEquals(1066158, account.getAcctNum());
    }
    @Test
    void getValue() {
        assertEquals(86000, account.getValue());
        assertNotEquals(10, account.getValue());
    }

    @Test
    void getAddress() {
        assertTrue(account.getAddress() instanceof Address);
    }

    @Test
    void getNbrHood() {
        assertTrue(account.getNbrHood() instanceof NbrHood);
    }

    @Test
    void getAssessment() {
        assertTrue(account.getAssessment() instanceof Assessment);
    }

    @Test
    void getLocation() {
        assertTrue(account.getLocation() instanceof Location);
    }
}