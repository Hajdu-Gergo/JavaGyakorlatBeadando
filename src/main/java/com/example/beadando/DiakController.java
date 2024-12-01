package com.example.beadando;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diakok")
public class DiakController {

    @Autowired
    private DiakRepository diakRepository;

    // Végpont a diákok számának lekérdezésére
    @GetMapping("/szam")
    public Long getDiakokSzama() {
        return diakRepository.count();
    }

    @GetMapping("/osszes")
    public Iterable<Diak> getDiakok() {
        return diakRepository.findAll();
    }
}
