package com.example.sirenalert24.Controller;
import com.example.sirenalert24.Model.Siren;
import com.example.sirenalert24.Repository.SirenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sirens")
@CrossOrigin(origins = "*")

public class SirenController {

    @Autowired
    private SirenRepository sirenRepo;

    @GetMapping
    public List<Siren> getAllSirens() {
        return sirenRepo.findAll();
    }

    @PostMapping
    public Siren createSiren(@RequestBody Siren siren) {
        return sirenRepo.save(siren);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Siren> updateSiren(@PathVariable Long id, @RequestBody Siren updated) {
        return sirenRepo.findById(id)
                .map(siren -> {
                    updated.setId(id);
                    return ResponseEntity.ok(sirenRepo.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSiren(@PathVariable Long id) {
        if (!sirenRepo.existsById(id)) return ResponseEntity.notFound().build();
        sirenRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
