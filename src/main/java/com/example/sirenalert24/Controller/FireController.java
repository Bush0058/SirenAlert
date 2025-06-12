package com.example.sirenalert24.Controller;

import com.example.sirenalert24.Model.Fire;
import com.example.sirenalert24.Service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fires")
@CrossOrigin(origins = "*")

public class FireController {

    @Autowired
    private FireService fireService;

    @PostMapping
    public Fire createFire(@RequestBody Fire fire) {
        return fireService.createFire(fire);
    }

    @GetMapping
    public List<Fire> getFires(@RequestParam(required = false) String status) {
        return fireService.getFiresByStatus(status != null ? status : "FARE");
    }

    @PutMapping("/{id}/closure")
    public Fire closeFire(@PathVariable Long id) {
        return fireService.closeFire(id);
    }
}
