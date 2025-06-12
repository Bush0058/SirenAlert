package com.example.sirenalert24;


import com.example.sirenalert24.Service.DistanceBeregning;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DistanceBeregningTest {

    @Test
    void testDistanceBetweenTwoPoints() {
        DistanceBeregning db = new DistanceBeregning();

        double lat1 = 34.0522;
        double lon1 = -118.2437;
        double lat2 = 34.0195;
        double lon2 = -118.4912;

        double afstand = db.calculate(lat1, lon1, lat2, lon2);

        assertEquals(23.0, afstand, 2.0);
    }
}
