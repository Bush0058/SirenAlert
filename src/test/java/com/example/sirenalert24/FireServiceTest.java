package com.example.sirenalert24.Service;

import com.example.sirenalert24.Model.Fire;
import com.example.sirenalert24.Model.Siren;
import com.example.sirenalert24.Repository.FireRepository;
import com.example.sirenalert24.Repository.SirenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FireServiceTest {

    private FireRepository fireRepo;
    private SirenRepository sirenRepo;
    private DistanceBeregning distanceBeregning;
    private FireService fireService;

    @BeforeEach
    void setUp() {
        fireRepo = mock(FireRepository.class);
        sirenRepo = mock(SirenRepository.class);
        distanceBeregning = mock(DistanceBeregning.class);
        fireService = new FireService(fireRepo, sirenRepo, distanceBeregning); // constructor med dependencies
    }

    @Test
    void testCreateFireActivatesNearbySirens() {
        Fire fire = new Fire();
        fire.setLatitude(34.0);
        fire.setLongitude(-118.0);

        Siren nearby = new Siren();
        nearby.setLatitude(34.01);
        nearby.setLongitude(-118.01);

        Siren far = new Siren();
        far.setLatitude(35.0);
        far.setLongitude(-119.0);

        when(sirenRepo.findAll()).thenReturn(List.of(nearby, far));
        when(distanceBeregning.calculate(anyDouble(), anyDouble(), anyDouble(), anyDouble()))
                .thenAnswer(invocation -> {
                    double lat1 = invocation.getArgument(0);
                    double lon1 = invocation.getArgument(1);
                    double lat2 = invocation.getArgument(2);
                    double lon2 = invocation.getArgument(3);
                    // fake calculation: if points are close, return 5 km
                    return (Math.abs(lat1 - lat2) < 0.02 && Math.abs(lon1 - lon2) < 0.02) ? 5.0 : 50.0;
                });

        when(fireRepo.save(any(Fire.class))).thenAnswer(i -> i.getArgument(0));

        Fire saved = fireService.createFire(fire);

        assertEquals("FARE", saved.getStatus());
        assertEquals(1, saved.getSirens().size(), "Kun 1 sirene bør være tilknyttet");
    }
}

