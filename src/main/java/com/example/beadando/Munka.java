package com.example.beadando;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "munkahelyek")
public class Munka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mhelyid;

    @ManyToOne
    @JoinColumn(name = "diakaz")
    private Diak diak;

    @OneToOne
    @JoinColumn(name = "mhelyid")
    private Munkahely mhely;

    private String allas;
    private LocalDateTime datum;
    private Integer oradij;
    private Integer oraszam;
    private Boolean kozepiskolas;

    // Getters and Setters
    public Integer getId() {
        return mhelyid;
    }

    public void setId(Integer id) {
        this.mhelyid = id;
    }

    public Integer getMhelyid() {
        return mhelyid;
    }

    public void setMhelyid(Integer mhelyid) {
        this.mhelyid = mhelyid;
    }

    public Diak getDiak() {
        if(diak != null){
            return diak;
        }
        return new  Diak();
    }

    public void setDiak(Diak diak) {
        this.diak = diak;
    }

    public Munkahely getMhely() {
        return mhely;
    }


    public String getAllas() {
        return allas;
    }

    public void setAllas(String allas) {
        this.allas = allas;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public Integer getOradij() {
        return oradij;
    }

    public void setOradij(Integer oradij) {
        this.oradij = oradij;
    }

    public Integer getOraszam() {
        return oraszam;
    }

    public void setOraszam(Integer oraszam) {
        this.oraszam = oraszam;
    }

    public Boolean getKozepiskolas() {
        return kozepiskolas;
    }

    public void setKozepiskolas(Boolean kozepiskolas) {
        this.kozepiskolas = kozepiskolas;
    }
}

