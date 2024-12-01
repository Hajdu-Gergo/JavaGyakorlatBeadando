package com.example.beadando;
import jakarta.persistence.*;

@Entity
@Table(name = "munkahelyek_neve")
public class Munkahely {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mhelyid;

    private String nev;

    private String telepules;

    // Getters and Setters
    public Integer getMhelyid() {
        return mhelyid;
    }

    public void setMhelyid(Integer mhelyid) {
        this.mhelyid = mhelyid;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }
}

