package com.example.sirenalert24.Service;

import com.example.sirenalert24.Model.Fire;
import com.example.sirenalert24.Model.Siren;
import com.example.sirenalert24.Repository.FireRepository;
import com.example.sirenalert24.Repository.SirenRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireService {

    private final FireRepository fireRepo;
    private final SirenRepository sirenRepo;
    private final DistanceBeregning distanceBeregning;


    public FireService(FireRepository fireRepo, SirenRepository sirenRepo, DistanceBeregning distanceBeregning) {
        this.fireRepo = fireRepo;
        this.sirenRepo = sirenRepo;
        this.distanceBeregning = distanceBeregning;
    }

    public Fire createFire(Fire fire) {
        fire.setStatus("FARE");
        List<Siren> sirens = sirenRepo.findAll();

        List<Siren> nearbySirens = new ArrayList<>();
        for (Siren siren : sirens) {
            if (siren.isDisabled()) continue; // Spring over deaktiverede sirener

            double distance = distanceBeregning.calculate(
                    fire.getLatitude(), fire.getLongitude(),
                    siren.getLatitude(), siren.getLongitude()
            );

            if (distance <= 10) {
                siren.setStatus("FARE");
                sirenRepo.save(siren);
                nearbySirens.add(siren);
            }
        }
        fire.setTimestamp(java.time.LocalDateTime.now());
        fire.setSirens(nearbySirens);
        return fireRepo.save(fire);
    }

    public List<Fire> getFiresByStatus(String status) {
        if ("active".equalsIgnoreCase(status)) {
            return fireRepo.findByStatus("FARE");
        }
        return fireRepo.findByStatus(status);
    }

    public Fire closeFire(Long id) {
        Fire fire = fireRepo.findById(id).orElseThrow();
        fire.setStatus("FRED");

        for (Siren siren : fire.getSirens()) {
            siren.setStatus("FRED");
            sirenRepo.save(siren);
        }

        return fireRepo.save(fire);
    }
}
