package com.example.beadando;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MunkaRepository extends JpaRepository<Munka, Integer> {
    List<Munka> findByDiak(Diak diak); // Munkák keresése adott diák alapján
}


