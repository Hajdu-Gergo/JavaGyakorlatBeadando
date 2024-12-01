package com.example.beadando;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "diakok")
public class Diak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diakaz;

    @Column(name = "nev")
    private String nev;

    @Column(name = "szulido")
    private LocalDate szulido;

    // Getters and Setters
    public Integer getDiakaz() {
        return diakaz;
    }

    public void setDiakaz(Integer diakaz) {
        this.diakaz = diakaz;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public LocalDate getSzulido() {
        return szulido;
    }

    public void setSzulido(LocalDate szulido) {
        this.szulido = szulido;
    }
}

