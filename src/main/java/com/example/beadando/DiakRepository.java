package com.example.beadando;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiakRepository extends JpaRepository<Diak, Integer> {
    Diak findByNev(String nev); // Diák keresése név alapján
}


