import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NbrHoodTest {

    private Account account;
    private NbrHood nbrHood;

    @BeforeEach
    void setUp() {
        account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        nbrHood = account.getNbrHood();
    }

    @Test
    void getID() {
        assertEquals(804, nbrHood.getID());
    }

    @Test
    void getName() {
        assertEquals("GRANVILLE", nbrHood.getName());
    }

    @Test
    void getWard() {
        assertEquals("tastawiyiniwak Ward", nbrHood.getWard());
    }

    @Test
    void testToString() {
        assertEquals("GRANVILLE (tastawiyiniwak Ward)", nbrHood.toString());
    }
}