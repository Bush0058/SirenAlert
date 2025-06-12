package com.example.sirenalert24.Config;

import com.example.sirenalert24.Model.Siren;
import com.example.sirenalert24.Repository.SirenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private SirenRepository sirenRepo;

    @Override
    public void run(String... args) {
        if (sirenRepo.count() == 0) {
            sirenRepo.saveAll(List.of(
                    new Siren(null, 34.0100, -118.4900, "FRED", false),
                    new Siren(null, 34.0150, -118.4950, "FRED", false),
                    new Siren(null, 34.0200, -118.5000, "FRED", false),
                    new Siren(null, 34.0250, -118.5050, "FRED", false),
                    new Siren(null, 34.0300, -118.5100, "FRED", false),
                    new Siren(null, 34.0350, -118.5150, "FRED", false),
                    new Siren(null, 34.0400, -118.5200, "FRED", false),
                    new Siren(null, 34.0450, -118.5250, "FRED", false),
                    new Siren(null, 34.0500, -118.5300, "FRED", false),
                    new Siren(null, 34.0550, -118.5350, "FRED", false)
            ));
        }
    }
}
