import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    private Account account;
    private Location location;

    @BeforeEach
    void setUp() {
        account = new Account("1066158,1000,14904,167 AVENUE NW,N,804,GRANVILLE,tastawiyiniwak Ward,86000,53.63049663158542,-113.5804742693191,POINT (-113.5804742693191 53.63049663158542),50,48,2,RESIDENTIAL,COMMERCIAL,OTHER RESIDENTIAL", 1);
        location = account.getLocation();
    }

    @Test
    void getLatitude() {
        assertEquals(53.63049663158542, location.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(-113.5804742693191, location.getLongitude());
    }

    @Test
    void getPointLocation() {
        assertEquals("POINT (-113.5804742693191 53.63049663158542)", location.getPointLocation());
    }

    @Test
    void testToString() {
        assertEquals("POINT (-113.5804742693191 53.63049663158542)", location.getPointLocation());
    }
}