package practice2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    static OktmoData d;
    @BeforeAll
    static void read() {
        d=new OktmoData();
        d.readFile("oktmo.csv");
    }
    @Test void test() {
//        assertEquals(195766, d.getPlaces().size()); // List without status (~~(.))
        assertEquals(195488, d.getPlaces().size()); // List with status

        Place glazaniha = d.findPlace("Глазаниха");
        assertArrayEquals(new int[]{11, 646, 403, 106}, new int[]{
                glazaniha.code1,
                glazaniha.code2,
                glazaniha.code3,
                glazaniha.code4 });
    }
}
