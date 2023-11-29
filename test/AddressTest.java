import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Account account;
    private Address address;

    @BeforeEach
    void setUp() {
        account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        address = account.getAddress();
    }

    @Test
    void getSuite() {
        assertEquals(1000, address.getSuite());
    }

    @Test
    void getHouseNum() {
        assertEquals(14904, address.getHouseNum());
    }

    @Test
    void getStreetName() {
        assertEquals("167 AVENUE NW", address.getStreetName());
    }

    @Test
    void getGarage() {
        assertEquals("N", address.getGarage());
    }

    @Test
    void testToString() {
        assertEquals("1000 14904 167 AVENUE NW", address.toString());
    }
}